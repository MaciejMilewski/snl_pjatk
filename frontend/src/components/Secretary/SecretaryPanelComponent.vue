<template>
  <div id="menu-glowne-srodek"
       uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">
    <div id="menu-sekretarz" uk-scrollspy-menu>
      <ul class="uk-flex-center" uk-tab="connect: #component-tab-center-secretary; animation: uk-animation-slide-right">
        <li ><a href="#"> <span uk-icon="icon:  calendar"/>
          Mecze - w trakcie</a></li>
        <li ><a href="#"> <span uk-icon="icon:  calendar"/>
          Mecze - archiwalne</a></li>
        <li ><a href="#"> <span uk-icon="icon:  calendar"/>
          Mecze - nadchodzące</a></li>
        <li ><a href="#"> <span uk-icon="icon:  calendar"/>
          Mecze - odrzucone</a></li>
      </ul>
    </div>
  </div>
  <div class=" uk-container "
       uk-scrollspy="target: [uk-scrollspy-switch]; cls: uk-animation-slide-right; delay: 100;">
    <ul id="component-tab-center-secretary" class="uk-switcher ">
      <li>
        <MatchesTabView :typ-meczow="'- w trakcie'" :status="'IN_PROGRESS'" />
      </li>
      <li>
        <MatchesTabView :typ-meczow="'- archiwalne'" :status="'PROTOCOL_FINALIZED'" />
      </li>
      <li>
        <MatchesTabView :typ-meczow="'- nadchodzące'" :status="'CREATED'" />
      </li>
      <li>
        <MatchesTabView :typ-meczow="'- odrzucone'" :status="'DISAPPROVAL'" />
      </li>

    </ul>
  </div>
</template>

<script>
import MatchesTabView from "@/views/Match/MatchesTabView";
export default {
  name: "SecretaryPanelComponent",
  components: {MatchesTabView}
}
</script>

<style scoped>
#menu-sekretarz > ul > li > a {

  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-sekretarz> ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}
</style>