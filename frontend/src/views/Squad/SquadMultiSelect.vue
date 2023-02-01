<template>
  <VueMultiselect
      ref="vue-multi-select"
      track-by="id"
      placeholder="-- Wyszukaj --"
      label="wyszukiwarka"
      v-model="value"
      :options="options"
      :custom-label="searchLabel"
      @select="playerSelection"
      :select-label="'Kliknij enter, aby wybrać'"
      :deselect-label="'Kliknij enter, aby odznaczyć'"
      :option-height="55"
      aria-required="true"
  >
    <template v-slot:noResult>
      <span>Nie znaleziono.</span>
    </template>
    <template v-slot:singleLabel="options">
      <img class="option__image" :src="options.option.img">
      <span class="option__desc">
          <span class="option__title">{{ options.option.name}} {{ options.option.surname }} - ({{options.option.searchValue}})</span>
        </span>
    </template>
    <template v-slot:option="options">
      <img class="option__image" :src="options.option.img">
      <div class="option__desc">
        <span class="option__title">{{ options.option.name }}  {{options.option.surname}} - ({{options.option.searchValue}}) </span>
      </div>
    </template>
  </VueMultiselect>
</template>

<script>
import VueMultiselect from "vue-multiselect/src/Multiselect";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
export default {
  name: "SquadMultiSelect",
  components: {VueMultiselect},
  props: ['teamId','providePlayers','players'],
  data(){
    return {
      value: null,
      options: [],
    }
  },
  setup(props,context){
    function choosePlayer(value){
      context.emit('playerSelection',value)
    }
    return {
      choosePlayer
    }
  },

  mounted() {
    let newOptions = [];
    if(this.providePlayers === false){
      axios.get("http://localhost:8080/api/teams/"+this.teamId+"/players")
          .then((response) => {
            if (response.data !== undefined)
              response.data.forEach((player) => {
                player.value = player.id
                player.fullname = player.name + " " + player.surname
                player.searchValue = 'Zawodnik'
                player.img = blobToDataURL(player.image)
                newOptions.push(player)
              })
            this.options = newOptions
          })
    }else  {
      this.options = this.players;
      this.options.forEach((o) => {
        o.value = o.id
        o.fullname = o.name + " " + o.surname
        o.searchValue = 'Zawodnik'
        o.img = blobToDataURL(o.image)
      })
    }


  },
  methods: {
    playerSelection(){
      setTimeout(()=>{
        this.choosePlayer(this.value);
      },500)
    },
    setOptions(options){
      if(options.length === 1){
        this.options = [];
        this.options.push(options[0])
        this.$forceUpdate();
      }else {
        this.options = options
      }
    setTimeout(()=> {
      this.$forceUpdate();
    }, 500)
    },

    searchLabel({name,surname,searchValue}){
      return `${name} ${surname} - (${searchValue})`
    },
  }
}
</script>
