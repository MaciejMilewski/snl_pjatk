<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-left-medium; delay: false;"


  >

    <div class="uk-container" >
      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-1@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                <div id="mecze" v-for="match in matches" v-bind:key="match.id"
                     uk-scrollspy="target: [uk-scrollspy-class2]; cls: uk-animation-slide-right-medium; delay: 500;">
                  <TeamMatch :match="match"/>
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
import TeamMatch from "@/views/Teams/TeamMatch";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import getDatePolish from "@/assets/scripts/dateConvert";
export default {
  name: "TeamMatches",
  props: ['id'],
  data(){
    return {
      matches: [],
    }
  },
  components: {TeamMatch},
  created() {
    axios.get("http://localhost:8080/api/matches/team/"+this.id)
        .then(response => {
          let matches = [];
          response.data.forEach((m) => {
            if(m.status === 'PROTOCOL_FINALIZED'){
              if(m.homeTeam !== null && m.visitingTeam !== null){
                if(m.homeTeam.image !== null && m.visitingTeam.image !== null){
                  m.homeImage = blobToDataURL(m.homeTeam.image)
                  m.visitingImage = blobToDataURL(m.visitingTeam.image)
                  let data = new Date(m.startTime);
                  m.startDate = getDatePolish(data);
                }
              }
              matches.push(m)

            }
          })
          this.matches = matches;
        })
  }
}
</script>

<style scoped>

</style>