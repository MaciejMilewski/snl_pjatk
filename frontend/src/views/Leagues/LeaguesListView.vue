<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-left-medium; delay: false;"

  >

    <div class="uk-container" >


      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-ligi-naglowek" class="uk-h1 uk-text-center" uk-scrollspy-class> Ligi</div>
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-2@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                <div id="ligi" v-for="league in leagues" v-bind:key="league.id"
                     uk-scrollspy="target: [uk-scrollspy-class2]; cls: uk-animation-slide-right-medium; delay: 500;">
                  <div v-if="viewType === 'admin'">
                    <LeagueView uk-scrollspy-class2
                                :id="league.id"
                                :name="league.name"
                                @incrementKey="incrementKey"
                    />
                  </div>
                  <div v-else>
                    <LeagueViewGuest uk-scrollspy-class2
                                     :id="league.id"
                                     :name="league.name"
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
</template>

<script>
import LeagueView from "@/views/Leagues/LeagueView";
import LeagueViewGuest from "@/views/Leagues/LeagueViewGuest";
import axios from "axios";
export default {
  name: "LeaguesListView",
  props: ['viewType'],
  components: {LeagueView,LeagueViewGuest},
  data(){
    return {
      leagues: [],
    }
  },
  methods: {
    incrementKey(){
      this.$emit('incrementKey')
    }
  },
  created() {
    axios.get("http://localhost:8080/api/leagues")
        .then(response => {
          this.leagues = response.data;
        })
  }
}
</script>

<style scoped>
#tlh-ligi-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>