<template>
  <VueMultiselect placeholder="-- Wybierz drużynę wyszukując --"
                  label="zawodnik-zespol"
                  v-model="value"
                  :options="options"
                  :custom-label="teamsLabel"
                  :track-by="options.name"
                  :select-label="'Kliknij enter, aby wybrać'"
                  :deselect-label="'Kliknij enter, aby odznaczyć'"
                  :option-height="25"
                  aria-required="true"
                  @select="updateOptions"
  >
    <template v-slot:noResult>
      <span>Taki zespół nie istnieje</span>
    </template>
    <template v-slot:singleLabel="options">
      <img class="option__image" :src="options.option.image">
      <span class="option__desc">
          <span class="option__title">{{ options.option.name }}</span>
        </span>
    </template>
    <template v-slot:option="options">
      <img class="option__image" :src="options.option.image">
      <div class="option__desc">
        <span class="option__title">{{ options.option.name }}</span>
      </div>
    </template>
  </VueMultiselect>
</template>

<script>

import VueMultiselect from "vue-multiselect/src/Multiselect";
import teams from '@/assets/temp_data/teams.json'
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";

export default {
  name: "TeamIdView",
  components: {VueMultiselect},
  data() {
    return {
      value: null,
      options: teams
    }
  },
  created() {
    axios.get("http://localhost:8080/api/teams").then((result)=>{
      result.data.data.forEach((r)=>{
        r.image = blobToDataURL(r.image)
      })
      this.options = result.data.data;
    })
  },
  methods: {
    updateOptions(value){
        let teamsSelected = [];
        this.options.forEach((team) => {
          if(value.id === team.id){
            teamsSelected.push(team)
          }
        })
        let teamsToSelect = this.options.filter(e => {
          return !teamsSelected.some(item => item.id === e.id)
        })

      this.$emit('teamSelected',teamsToSelect)
    },
    teamsLabel({name, id}) {
      return `${id} - [${name}]`
    }
  }
}
</script>
<style>
@import '@/assets/styles/player/multiselect/multiselect-teamid.css';
</style>