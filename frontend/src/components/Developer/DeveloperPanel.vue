<template>
  <div id="menu-glowne-srodek"
       uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">
    <div id="menu-developer" uk-scrollspy-menu>
      <ul id="switcher-tlh-administrator" class="uk-flex-center"
          uk-tab="connect: #component-tab-left; animation: uk-animation-slide-right">
        <li><a href="#"><span uk-icon="icon:  users"/>Zawodnicy </a></li>
        <li><a href="#"><span uk-icon="icon:  file-text"/>Artykuły</a></li>
        <li @mouseenter="hoverTeam" @mouseleave="disableHoverTeam"><a href="#"><span>
          <span v-if="isHoverTeam">
            <img :src="require(`@/assets/icons/team-hover.svg`)" style="width: 20px">
          </span>
          <span v-else>
            <img :src="require(`@/assets/icons/team.svg`)" style="width: 20px">
          </span>

        </span>Zespoły</a></li>
        <li >
          <a href="#">
            <span uk-icon="icon:  image"/>
            Sponsorzy
          </a>
        </li>
        <li>
          <a href="#">
            <span uk-icon="icon:  user"/>
            Użytkownicy
          </a>
        </li>
        <li>
          <a href="#">
            <span uk-icon="icon:  thumbnails"/>
            Ligi
          </a>
        </li>
        <li>
          <a href="#">
            <span uk-icon="icon:  calendar"/>
            Mecze
          </a>
        </li>
      </ul>
    </div>
  </div>
  <div class=" uk-container "
       uk-scrollspy="target: [uk-scrollspy-switch]; cls: uk-animation-slide-right; delay: 100;">
    <ul id="component-tab-left" class="uk-switcher ">
      <li>
        <PlayersTabView @incrementKey="incrementKey" :key="keyValue"/>

      </li>

      <li>
        <NewsTabView @incrementKey="incrementKey" :key="keyValue"/>

      </li>

      <li>
        <TeamsTab @incrementKey="incrementKey" :key="keyValue"/>
      </li>
      <li>
        <SponsorsTabView @incrementKey="incrementKey" :key="keyValue"/>
      </li>
      <li>
        <UsersView  @incrementKey="incrementKey" :key="keyValue"/>
      </li>
      <li>
        <LeaguesTab  @incrementKey="incrementKey" :key="keyValue"/>
      </li>
      <li>
        <MatchesTabView @incrementKey="incrementKey" :key="keyValue"/>
      </li>
    </ul>
  </div>
  <div>

  </div>

</template>

<script>
import useKey from "@/composables/useKey";
import UsersView from "@/views/Users/UsersView";
import PlayersTabView from "@/views/Player/PlayersTabView";
import NewsTabView from "@/views/News/NewsTabView";
import TeamsTab from "@/views/Teams/TeamsTab";
import SponsorsTabView from "@/views/Sponsors/SponsorsTabView";
import LeaguesTab from "@/views/Leagues/LeaguesTab";
import MatchesTabView from "@/views/Match/MatchesTabView";
export default {
  name: "DeveloperPanelComponent",
  emits: ['incrementKey'],
  components: {
    LeaguesTab,
    SponsorsTabView,
    TeamsTab,
    NewsTabView,
    PlayersTabView,
    UsersView,
    MatchesTabView
  },
  setup(props, context) {
    const {keyValue, incrementKey} = useKey(0, 1)

    function incrementKeyInside(value) {
      context.emit('incrementKey', value)
    }

    return {
      keyValue,
      incrementKey,
      incrementKeyInside
    }
  },
  data() {
    return {
      isHoverAddTeam: false,
      isHoverTeam: false,
      isHoverSponsor: false,
    }
  },
  methods: {
    hover() {
      this.isHoverAddTeam = true
    },
    disableHover() {
      this.isHoverAddTeam = false
    },
    hoverTeam() {
      this.isHoverTeam = true
    },
    disableHoverTeam() {
      this.isHoverTeam = false
    },
  },


}
</script>

<style scoped>


#menu-developer > ul > li > a {

  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-developer > ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}

</style>