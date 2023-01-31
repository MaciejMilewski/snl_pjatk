<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-left-medium; delay: false;"


  >

    <div class="uk-container" >
      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-mecze-naglowek" class="uk-h1 uk-text-center" uk-scrollspy-class>Mecze</div>
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
</template>

<script>
import axios from "axios";
import MatchView from "@/views/Match/MatchView";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import getDatePolish from "@/assets/scripts/dateConvert";
import {mapGetters} from "vuex";
export default {
  name: "MatchesListView",
  props: ['status'],
  components: {MatchView},
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
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  created() {
    axios.get("http://localhost:8080/api/matches")
        .then(response => {
          let matches = response.data.data;
          let matchesWithTeams = []
          // Zamiana na dataurl, aby wyświetlić zdjęcia drużyn
          matches.forEach((match)=>{
            if(match.homeTeam !== null && match.visitingTeam !== null){
              if(match.homeTeam.image !== null && match.visitingTeam.image !== null){
                match.homeImage = blobToDataURL(match.homeTeam.image)
                match.visitingImage = blobToDataURL(match.visitingTeam.image)
                let data = new Date(match.startTime);
                match.startDate = getDatePolish(data);
                if(match.status === this.status || this.status === undefined){
                  if(match.status !== 'IN_PROGRESS' && this.User.group === 'deweloper'){
                    matchesWithTeams.push(match)
                  }else if(match.status === 'IN_PROGRESS' && this.User.group === 'sekretarz'){
                    matchesWithTeams.push(match);
                  }else if(this.User.group === 'sedzia_medyk' && match.status === 'FINISHED'){
                    matchesWithTeams.push(match)
                  }else if(this.User.group === 'sekretarz' && match.status === 'PROTOCOL_FINALIZED'){
                    matchesWithTeams.push(match);
                  }else if ((this.User.group === 'sekretarz' || this.User.group === 'deweloper')
                      && match.status === 'DISAPPROVAL'){
                    matchesWithTeams.push(match);
                  }else if(this.User.group === 'sedzia_medyk' && match.status === 'CORRECTED'){
                    matchesWithTeams.push(match);
                  }else if (this.User.group === 'sekretarz' && (match.status === 'CREATED' || match.status === 'READY')) {
                    matchesWithTeams.push(match);
                  }

                }else if(this.status === 'CREATED' && match.status === 'READY'){
                  matchesWithTeams.push(match);
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
#tlh-mecze-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>