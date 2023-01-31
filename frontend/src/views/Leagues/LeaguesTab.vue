<template>
  <div>
    <div uk-grid id="tlh-ligi-grid">
      <div class="uk-width-auto@m" id="tlh-ligi-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-ligi; animation: uk-animation-fade">
          <li @click="incrementKey"><a class="leagues-tab-left" href="#">
            <span uk-icon="icon: thumbnails"/>
            Ligi</a></li>
          <li><a class="leagues-tab-left" href="#"><span uk-icon="icon: plus" />Dodaj ligę</a></li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-ligi" class="uk-switcher">
          <li>
            <LeaguesListView @incrementKey="incrementKey" :key="keyValue" :view-type="'admin'"
            />
          </li>
          <li>
            <AddLeagueView/>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import LeaguesListView from "@/views/Leagues/LeaguesListView";
import AddLeagueView from "@/views/Leagues/Form/AddLeagueView";
export default {
  name: "LeaguesTab",
  components: {AddLeagueView, LeaguesListView},
  data(){
    return {
      keyValue: 0,
    }
  },
  methods: {
    incrementKey(){
      this.keyValue++;
    }
  }
}
</script>

<style scoped>
#tlh-ligi-grid {
  border-top-left-radius: 10px;
}
#tlh-ligi-uk-width-auto {
  background-color: #a6efff;
  border-top-left-radius: 10px;
}
.uk-tab {
  background-color: #a6efff;
  padding: 10px;

  height: max-content !important;
}
.uk-tab > * > a:hover {
  color: #006394;
  font-weight: bold;
}
.uk-tab > .uk-active > a {
  font-weight: bold;
  color: #006394 !important;
  border-color: #006394;
  border-right-width: 5px;
}
</style>