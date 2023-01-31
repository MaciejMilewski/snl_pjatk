<template>
  <div id="submenu-druzyna"
       uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">

    <div id="menu-start" uk-scrollspy-menu>
      <ul class="uk-flex-center" uk-tab="connect: #submenu-druzyna-component; animation: uk-animation-slide-right">

        <li><a>Terminarz</a></li>
        <li><a href="#">Wyniki</a></li>
        <li><a href="#">Zawodnicy</a></li>
        <li><a href="#">Plan Treningowy</a></li>
      </ul>
    </div>
  </div>
  <div class="uk-container">
    <ul id="submenu-druzyna-component" class="uk-switcher">
      <li>
        <div>
          <div v-if="league > 0">
            <router-link :to="`/single-league/${league}`" class="przycisk-powrot">
              <span uk-icon="icon: arrow-left; ratio: 2"/>Wróć
            </router-link>
          </div>
          <div v-else>
            <a href="#zespoly"  class="uk-button-default przycisk-powrot"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
          </div>
          <img :src="team.image" class="tlh-obrazek uk-align-center ">
          <h2 class="tlh-nazwa uk-margin-top uk-margin-remove-bottom">

            {{ team.name }} - Terminarz

          </h2>
          <MatchByTeamView :team-id="this.id" :status="'CREATED'" />
        </div>

      </li>
      <li>
        <div>
          <div v-if="league > 0">
            <router-link :to="`/single-league/${league}`" class="przycisk-powrot">
              <span uk-icon="icon: arrow-left; ratio: 2"/>Wróć
            </router-link>
          </div>
          <div v-else>
            <a href="#zespoly"  class="uk-button-default przycisk-powrot"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
          </div>
          <img :src="team.image" class="tlh-obrazek uk-align-center ">
          <h2 class="tlh-nazwa uk-margin-top uk-margin-remove-bottom">
            {{ team.name }} - Wyniki
          </h2>
          <TeamMatches :id="this.id" />
        </div>
      </li>
      <li>
        <div>
          <div v-if="league > 0">
            <router-link :to="`/single-league/${league}`" class="przycisk-powrot">
              <span uk-icon="icon: arrow-left; ratio: 2"/>Wróć
            </router-link>
          </div>
          <div v-else>
            <a href="#zespoly"  class="uk-button-default przycisk-powrot"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
          </div>
          <img :src="team.image" class="tlh-obrazek uk-align-center ">
          <h2 class="tlh-nazwa uk-margin-top uk-margin-remove-bottom">

            {{ team.name }} - Zawodnicy

          </h2>
          <PlayerListView :players="players" :view-type="'SingleTeamView'"/>
        </div>
      </li>
      <li>
        <div>
          <div v-if="league > 0">
            <router-link :to="`/single-league/${league}`" class="przycisk-powrot">
              <span uk-icon="icon: arrow-left; ratio: 2"/>Wróć
            </router-link>
          </div>
          <div v-else>
            <a href="#zespoly"  class="uk-button-default przycisk-powrot"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
          </div>

          <img :src="team.image" class="tlh-obrazek uk-align-center ">
          <h2 class="tlh-nazwa uk-margin-top uk-margin-remove-bottom">

            {{ team.name }} - Plan treningowy

            <!--            Plan treningowy -->
            <div v-if="imageSchedule !== null">
              <h3 id="tlh-plan-nazwa-naglowek">{{ description }}</h3>

              <img :src="imageSchedule" style="width: 1000px"/>
            </div>

          </h2>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";
import {useRoute} from 'vue-router'
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import axios from "axios";
import PlayerListView from "@/views/Player/PlayerList";
import MatchByTeamView from "@/views/Match/MatchByTeamView";
import TeamMatches from "@/views/Teams/TeamMatches";

export default {
  name: "SingleTeamView",
  components: {TeamMatches, MatchByTeamView, PlayerListView},
  setup() {
    const router = useRoute();
    const state = reactive({
      id: router.params.id,
      league: router.params.league
    })
    return {
      ...toRefs(state),
      router,
    }
  },
  data() {
    return {
      team: {},
      imageSchedule: null,
      description: null,
      id: null,
      players: [],
    }
  },
  created() {

    axios.get("http://localhost:8080/api/teams/" + this.id + "/schedule").then((response) => {
      this.imageSchedule = blobToDataURL(response.data.image)
      this.description = response.data.description
      this.id = response.data.id
    })
    axios.get("http://localhost:8080/api/teams/" + this.id + "/players").then((response) => {
      this.players = response.data
      this.players.forEach((p)=> {
        p.image = blobToDataURL(p.image)
      })
    })
  },
  mounted() {
    axios.get('http://localhost:8080/api/teams/' + this.id)
        .then(response => {
          let data = response.data
          data.image = blobToDataURL(data.image)
          this.team = data
        })
  }
}
</script>

<style scoped>
.tlh-nazwa {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  text-align: center;
  border-bottom-right-radius: 20px;
}

#menu-start > ul > li > a {
  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-start > ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}

.tlh-obrazek {
  height: 100px;
  margin-bottom: 0;
  margin-top: 0;
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