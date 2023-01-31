<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-left-medium; delay: false;" :key="page">

    <div class="uk-container" >


      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-liga-naglowek" class="uk-h1" uk-scrollspy-class>Liga {{league.name}}</div>
        </div>
      </div>
    </div>
  </div>

  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class2]; cls: uk-animation-slide-left-medium; delay: false;" :key="page">

    <div class="uk-container" >
      <router-link :to="`/ligi`" class="przycisk-powrot">
        <span uk-icon="icon: arrow-left; ratio: 2"/>Wróć
      </router-link>

      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-4@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                <div id="druzyny" v-for="team in teams" v-bind:key="team.id"
                     uk-scrollspy="target: [uk-scrollspy-class2]; cls: uk-animation-slide-right-medium; delay: 500;">
                  <TeamView uk-scrollspy-class2
                            :id="team.id"
                            :name="team.name"
                            :image="team.image"
                            :league-id="this.id"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {useRoute} from "vue-router";
import {reactive, toRefs} from "vue";
import axios from "axios";
import TeamView from "@/views/Teams/Team/TeamView";
export default {
  name: "SingleLeagueView",
  components: {TeamView},
  data(){
    return {
      league: null,
      teams: [],
    }
  },
  setup(){
    const router = useRoute();
    const state = reactive({
      id: router.params.id
    })
    return {
      ...toRefs(state),
      router,
    }
  },
  created() {
    //Liga
    axios.get("http://localhost:8080/api/leagues/"+this.id).then(response => {
      this.league = response.data
    })
    let id = this.id;
    //Zespoły ligi
    axios.get("http://localhost:8080/api/teams")
        .then(response => {
          let data = response.data.data;
          let teamsData = [];
          if(data !== undefined)
            data.forEach((d) => {
              let dataUri = "data:image/png;base64," + d.image
              d.image = dataUri
              // Dodaj tylko według ligi
              if(d.leagues.length > 0) {
                if(parseInt(d.leagues[0].id) === parseInt(id))
                {
                  teamsData.push(d)
                }
              }
            })
          console.log(teamsData);
          this.teams = teamsData
        })
  }
}
</script>

<style scoped>
#tlh-liga-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
.przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
  background: #a6efff !important;
  border-radius: 10px 10px 10px 10px !important;
  font-weight: bold !important;
  color: #009fb6 !important;
  margin-top: 10px !important;

}
</style>