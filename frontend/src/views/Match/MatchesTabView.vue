<template>
  <div>
    <div uk-grid id="tlh-mecze-grid">
      <div class="uk-width-auto@m" id="tlh-mecze-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-mecze; animation: uk-animation-fade">
          <li @click="incrementKey">
            <a class="matches-tab-left" href="#">
            <span uk-icon="icon: calendar"/>
            Mecze {{typMeczow}}
            </a>
          </li>
          <li v-if="User !== null && (User.group === 'deweloper' )">
            <a class="matches-tab-left" href="#">
              <span uk-icon="icon: plus" />Dodaj nowy mecz
            </a>
          </li>
          <li @click="incrementKey" v-if="User.group === 'deweloper'">
            <a class="matches-tab-left" href="#">
              <span uk-icon="icon: calendar"/>
              Mecze - konflikt
            </a>
          </li>
          <li @click="incrementKey" v-if="User.group === 'deweloper'">
            <a class="matches-tab-left" href="#">
              <span uk-icon="icon: calendar"/>
              Mecze - odrzucone
            </a>
          </li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-mecze" class="uk-switcher">
          <li>
            <MatchesListView :key="keyValue" :status="this.status"/>
          </li>
          <li v-if="User !== null && (User.group === 'deweloper' )">
            <CreateNewMatch :match="{}" :edit-context="false"/>
          </li>
          <li v-if="User.group === 'deweloper'">
            <MatchesListView :key="keyValue" :status="'CONFLICT'"/>
          </li>
          <li v-if="User.group === 'deweloper'">
            <MatchesListView :key="keyValue" :status="'DISAPPROVAL'"/>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import MatchesListView from "@/views/Match/MatchesListView";
import CreateNewMatch from "@/views/Match/CreateNewMatch";
import {mapGetters} from "vuex";
export default {
  name: "MatchesTabView",
  props: ['typMeczow', 'status'],
  components: {CreateNewMatch, MatchesListView},
  data() {
    return {
      keyValue: 0,
    }
  },
  methods: {
    incrementKey() {
      this.keyValue++;
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
}
</script>

<style scoped>
#tlh-mecze-grid {
  border-top-left-radius: 10px;
}
#tlh-mecze-uk-width-auto {
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