<template>
  <div id="menu-glowne-srodek"
       uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">
    <div id="menu-trener" uk-scrollspy-menu>
      <ul class="uk-flex-center" uk-tab="connect: #component-tab-center; animation: uk-animation-slide-right">
        <li @click="incrementKey"><a href="#"><span uk-icon="icon:  users"/><span uk-icon="icon:  plus"/>Dodawanie składu </a></li>
        <li><a href="#"><span uk-icon="icon:  users"/><span uk-icon="icon:  plus"/>Dodawanie piątki </a></li>
        <li><a href="#"><span uk-icon="icon:  pencil"/>Narysuj ćwiczenie </a></li>
        <li @click="incrementKey"><a href="#"><span uk-icon="icon:  pencil"/>Ćwiczenia </a></li>
        <li><a href="#"><span uk-icon="icon:  image"/>Plan treningowy </a></li>
      </ul>
    </div>
  </div>
  <div class=" uk-container "
       uk-scrollspy="target: [uk-scrollspy-switch]; cls: uk-animation-slide-right; delay: 100;">
    <ul id="component-tab-center" class="uk-switcher ">
      <li>


        <UpcomingMatchSquadView :key="keyValue" @incrementKey="incrementKey" />
      </li>
      <li>
      <AddFivePlayers/>
      </li>
      <li>
        <AllStrategiesByTeam :key="keyValue" @incrementKey="incrementKey"/>
      </li>
      <li>
    <StrategyList :key="keyValue" @incrementKey="incrementKey"/>
      </li>
      <li>
     <TrainingPlan :key="keyValue" @incrementKey="incrementKey"/>
      </li>
    </ul>
  </div>
</template>

<script>
import AddFivePlayers from "@/views/Squad/AddFivePlayers";
import UpcomingMatchSquadView from "@/views/UpcomingMatch/UpcomingMatchSquadView";
import TrainingPlan from "@/views/Trainer/TrainingPlan/TrainingPlan";
import StrategyList from "@/views/Trainer/Strategy/StrategyList";
import AllStrategiesByTeam from "@/views/Trainer/Strategy/AllStrategiesByTeam";
import {mapActions} from "vuex";
export default {
  name: "TrainerPanelComponent",
  emits: ['incrementKey'],
  components: {
    AllStrategiesByTeam,
    TrainingPlan,
    UpcomingMatchSquadView,
    AddFivePlayers,
    StrategyList
  },
  data() {
    return {
      keyValue: 0,
    }
  },
  methods: {
    ...mapActions(["SetPlayers"]),
    refreshSquad(){
      this.SetPlayers([{}]);
      this.$emit('incrementKey')
    },
    incrementKey(){
      this.keyValue++;
    }
  }
}
</script>

<style scoped>
#menu-trener > ul > li > a {

  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-trener > ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}


</style>