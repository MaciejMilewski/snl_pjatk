<template>
 <div v-if="matches.length">
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
                   <MatchView uk-scrollspy-class2
                              :id="match.id"
                              :startDate="match.startDate"
                              :place="match.place"
                              :home-image="match.homeImage"
                              :homeName="match.homeTeam.name"
                              :visitingName="match.visitingTeam.name"
                              :visiting-image="match.visitingImage"
                              @incrementKey="incrementKey"
                   />
                 </div>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </div>
  <div v-else>
    <h2 style="margin-top: 15px;">Brak nadchodzących meczy...</h2>
  </div>

</template>

<script>
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import getDatePolish from "@/assets/scripts/dateConvert";
import MatchView from "@/views/Match/MatchView";
export default {
  name: "MatchByTeamView",
  components: {MatchView},
  props: ['teamId','status'],
  data(){
    return {
      matches: [],
    }
  },
  methods: {
    incrementKey(){
      this.$emit('incrementKey')
    }
  },
  created() {
    axios.get("http://localhost:8080/api/matches/team/"+this.teamId)
        .then(response => {
          let matches = response.data;
          let matchesWithTeams = []
          // Zamiana na dataurl, aby wyświetlić zdjęcia drużyn
          matches.forEach((match)=>{
            if(match.homeTeam !== null && match.visitingTeam !== null){
              if(match.homeTeam.image !== null && match.visitingTeam.image !== null){
                match.homeImage = blobToDataURL(match.homeTeam.image)
                match.visitingImage = blobToDataURL(match.visitingTeam.image)
                let data = new Date(match.startTime);
                match.startDate = getDatePolish(data);
                if(this.status !== null){
                  if(match.status === this.status){
                    matchesWithTeams.push(match)
                  }
                }
              }
            }

          })
          this.matches = matchesWithTeams;
        })

  }
}
</script>

<style scoped>

</style>