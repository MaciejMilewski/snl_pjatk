<template>
  <VueMultiselect
      placeholder="-- Wyszukaj --"
      label="wyszukiwarka"
      v-model="value"
      :options="options"
      :custom-label="searchLabel"
      :select-label="'Kliknij enter, aby wybrać'"
      :deselect-label="'Kliknij enter, aby odznaczyć'"
      :option-height="55"
      @select="onSearchSelect()"
      aria-required="true"
  >
    <template v-slot:noResult>
      <span>Nie znaleziono.</span>
    </template>
    <template v-slot:singleLabel="options">
      <div class="uk-display-inline">
        <img class="option__image" :src="options.option.img">
      </div>

      <div v-if="options.option.img2" class="uk-display-inline">
        <img class="option__image" :src="options.option.img2">
      </div>

      <span class="option__desc">
          <span class="option__title">{{ options.option.name}} {{ options.option.surname }} - ({{options.option.searchValue}})</span>
        </span>
    </template>
    <template v-slot:option="options">
      <div class="uk-display-inline">
        <img class="option__image" :src="options.option.img">
      </div>
      <div v-if="options.option.img2" class="uk-display-inline">
        <img class="option__image" :src="options.option.img2">
      </div>
      <div class="option__desc">
        <span class="option__title">{{ options.option.name }}  {{options.option.surname}} - ({{options.option.searchValue}}) </span>
      </div>
    </template>
  </VueMultiselect>
</template>

<script>
import router from "@/router";
import axios from "axios";
import VueMultiselect from "vue-multiselect/src/Multiselect";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {mapGetters} from "vuex";

export default {
  name: "SearchMultiSelect",
  components: {VueMultiselect},
  data() {
    return {
      value: null,
      options: []
    }
  },
  setup(props,context){
    function closeModalWindow(){
      context.emit('closeModalWindow')
    }
    return {
      closeModalWindow
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    searchLabel({name,surname,searchValue}){
      return `${name} ${surname} - (${searchValue})`
    },
    onSearchSelect(){
      setTimeout(() => {
        const selectedValue = this.value;

        if(selectedValue.context === 'single.player'){
          this.$router.push('/zawodnik/'+selectedValue.id)
        }else if(selectedValue.context === 'single.article') {
          router.push('/artykul/'+selectedValue.id)
        }else if(selectedValue.context === 'single.team'){
          router.push('/zespol/'+selectedValue.id)
        }else if(selectedValue.context === 'single.match'){
          router.push('/mecz-widok/'+selectedValue.id)
        }
        this.closeModalWindow();

      },500)
    }
  },
  mounted() {
    let newOptions = [];
    axios.get("http://localhost:8080/api/news/")
        .then((response) => {
          if(response.data.data !== undefined)
          response.data.data.forEach((article) => {
            article.img = blobToDataURL(article.image)
            article.name = article.title
            article.surname = '';
            article.searchValue = 'Artykuł'
            article.context = 'single.article'
            newOptions.push(article)
          })
        })
    axios.get("http://localhost:8080/api/players/")
        .then((response) => {
          if(response.data.data !== undefined)
          response.data.data.forEach((player) => {
            player.searchValue = 'Zawodnik'
            player.context = 'single.player'
            player.img = blobToDataURL(player.image)
            newOptions.push(player)
          })
        })
    axios.get("http://localhost:8080/api/teams/")
        .then((response) => {
          if(response.data.data !== undefined)
            response.data.data.forEach((team) => {
              team.searchValue = 'Drużyna'
              team.context = 'single.team'
              team.img = blobToDataURL(team.image)
              newOptions.push(team)
            })
        })
    axios.get("http://localhost:8080/api/matches")
        .then((response) => {
          if(response.data.data !== undefined){
            response.data.data.forEach((match) => {
              // Gość / Sędzia/medyk lub trener
              if(this.User === null || this.User.group === 'sedzia_medyk' || this.User.group === 'trener'){
                if(match.status === 'PROTOCOL_FINALIZED'){
                  match.searchValue = 'Mecz - ' + match.startTime;
                  match.context = 'single.match'
                  match.name = match.homeTeam.name + " - ";
                  match.surname = match.visitingTeam.name;
                  match.img = blobToDataURL(match.homeTeam.image)
                  match.img2 = blobToDataURL(match.visitingTeam.image)
                  newOptions.push(match)
                }
              }else if(this.User.group === 'sekretarz' || this.User.group === 'deweloper'){
                // Sekretarz lub deweloper
                match.searchValue = 'Mecz - ' + match.startTime;
                match.context = 'single.match'
                match.img = blobToDataURL(match.homeTeam.image)
                match.img2 = blobToDataURL(match.visitingTeam.image)
                match.name = match.homeTeam.name + " - ";
                match.surname = match.visitingTeam.name;
                newOptions.push(match)
              }
            })
          }
        })
    this.options = newOptions;
  },
}
</script>

<style scoped>
.option__image {
  display: inline-block;
}
</style>