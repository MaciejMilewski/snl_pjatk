<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-left-medium; delay: false;" :key="page">

    <div class="uk-container" >


      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-zespoly-naglowek" class="uk-h1" uk-scrollspy-class>Zespoły</div>
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-4@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                <div id="druzyny" v-for="team in teams" v-bind:key="team.id"
                     uk-scrollspy="target: [uk-scrollspy-class2]; cls: uk-animation-slide-right-medium; delay: 500;">
                  <TeamView uk-scrollspy-class2
                               :id="team.id"
                               :name="team.name"
                               :image="team.image"
                            :league-id="0"
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
import axios from "axios";
import TeamView from "@/views/Teams/Team/TeamView";
export default {
  name: "TeamsListComponent",
  components: {TeamView},
  data(){
    return {
      teams: []
    }
  },
  created() {
    axios.get("http://localhost:8080/api/teams")
        .then(response => {
          let data = response.data.data;
          let teamsData = [];
          if(data !== undefined)
            data.forEach((d) => {
              let dataUri = "data:image/png;base64," + d.image
              d.image = dataUri
              teamsData.push(d)
            })
          this.teams = teamsData
        })
  }
}
</script>

<style scoped>
#tlh-zespoly-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>