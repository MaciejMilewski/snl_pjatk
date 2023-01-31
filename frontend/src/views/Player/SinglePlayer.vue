<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-right-medium; delay: none;">
    <div class="uk-container">
      <a @click="switchToTeam()" id="przycisk-powrot" class="uk-button-default"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
      <div class="uk-grid-margin uk-grid" uk-grid="">
        <div class="uk-width-1-2@m uk-first-column">
          <div  class="uk-margin uk-scrollspy-inview" uk-scrollspy-class style="">
            <img class="tlh-obrazek" id="obrazek-duzy" :src="player.image"  alt="Duży obrazek w widoku single player" loading="lazy">
          </div>
        </div>
        <div class="uk-width-1-2@m">
          <h1 uk-scrollspy-class="" class="uk-scrollspy-inview" style="">{{player.name}} {{player.surname}}</h1>
          <div class="uk-panel uk-margin uk-scrollspy-inview"  uk-scrollspy-class style="">
            <p class="uk-text-meta">Data urodzenia: {{ player.birthDate }}</p>
          </div>
          <div class="uk-panel uk-margin uk-scrollspy-inview" uk-scrollspy-class style="">
            <p class="uk-text-meta">Miejsce urodzenia: {{ player.birthplace }}</p>
            <p class="uk-text-meta">Waga: {{ player.weight }} kg</p>
          <p class="uk-text-bold">Numer zawodnika: {{ player.playerNumber }}</p>
          <p class="uk-text-bold">Pozycja: {{ player.position }}</p>
          <p class="uk-text-bold">Wygięcie kija: {{ player.stick }}</p>
          <p class="uk-text-bold">Drużyna: {{ player.teamId }}</p>
          </div>
          <div class="uk-panel uk-margin uk-scrollspy-inview " uk-scrollspy-class style="" v-html="player.description">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {useRoute} from "vue-router";
import {reactive, toRefs} from "vue";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
export default {
  name: "SinglePlayerView",
  setup(){
    const router = useRoute()
    const state = reactive({
      id: router.params.id
    })
    return {
      ...toRefs(state),
      router
    }
  },
  data(){
    return{
      player: {}
    }
  },
  methods: {
    switchToTeam(){
      this.$router.push('/zespol/'+this.player.teamId+'/0');
    }
  },
  created() {
    axios.get("http://localhost:8080/api/players/"+this.id)
        .then((response) =>{
          this.player = response.data
          console.log(this.player);
          this.player.image = blobToDataURL(this.player.image)
        })
  }
}
</script>

<style scoped>
#przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
}
.tlh-obrazek{
  height: 500px;
}
</style>