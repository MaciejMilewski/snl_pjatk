<template>
  <div id="menu-glowne-srodek"
       uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">
    <div id="menu-medyk-lub-sedzia" uk-scrollspy-menu>
      <ul class="uk-flex-center" uk-tab="connect: #component-tab-center-secretary; animation: uk-animation-slide-right">
        <li ><a href="#"> <span uk-icon="icon:  calendar"/>
          Mecze do podpisu</a></li>
        <li ><a href="#"> <span uk-icon="icon:  calendar"/>
          Mecze do podpisu (poprawione)</a></li>
      </ul>
    </div>
  </div>
  <div class=" uk-container "
       uk-scrollspy="target: [uk-scrollspy-switch]; cls: uk-animation-slide-right; delay: 100;">
    <ul id="component-tab-center-secretary" class="uk-switcher ">
      <li>
        <MatchesTabView :typ-meczow="'- do podpisu'" :status="'FINISHED'" />
      </li>
      <li>
        <MatchesTabView :typ-meczow="'- do podpisu (poprawione)'" :status="'CORRECTED'" />
      </li>

    </ul>
  </div>
</template>

<script>
import MatchesTabView from "@/views/Match/MatchesTabView";
export default {
  name: "MedicOrJudglePanelComponent",
  components : {MatchesTabView}
}
</script>

<style scoped>
#menu-medyk-lub-sedzia > ul > li > a {

  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-medyk-lub-sedzia> ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}
</style>