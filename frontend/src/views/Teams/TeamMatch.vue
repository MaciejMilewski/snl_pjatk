<template>
  <div class="uk-first-column uk-card uk-card-default uk-card-body">
    <div class="tlh-item uk-panel uk-margin-remove-first-child">
      <div class="uk-flex uk-flex-center">
        <div class="tlh-obrazek-sredni">
          <img class="tlh-obrazek" alt="" :src="match.homeImage" loading="lazy">
        </div>
        <h2 class=" match-info" >{{match.homeTeam.name}} </h2>
        <h2 class=" match-info" style="font-weight: bold">{{ score }}</h2>
        <h2 class="match-info" >{{match.visitingTeam.name}}</h2>
        <div class="tlh-obrazek-sredni">
          <img class="tlh-obrazek" alt="" :src="match.visitingImage" loading="lazy">
        </div>
      </div>

      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom uk-text-center "> {{match.place }} </h2>
      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom uk-text-center "> {{ match.startDate }} </h2>
      <div class="uk-margin-top">
        <router-link :to="`/mecz-widok/${match.id}`">
          <a
              uk-scroll=""
              class="tlh-link uk-button uk-button-default uk-width-1-1 uk-margin">Przejdź do meczu</a>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TeamMatch",
  props: ['match'],
  data(){
    return {
      score: null,
    }
  },
  created() {
    axios.get("http://localhost:8080/api/matches/"+this.match.id+"/score")
        .then(response => {
          this.score = response.data.regular.finalScore;
        })
  }
}
</script>

<style scoped>
.tlh-obrazek-sredni > img {
  height: 100px;
}
.match-info {
  margin-left: 15px;
  height: 40px;
  line-height: 40px;
  vertical-align: middle;
}
</style>