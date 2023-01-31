<template>
  <div v-if="match">
    <div class="uk-first-column uk-text-center uk-margin-top" >
      <div class="tlh-item uk-panel uk-margin-remove-first-child">
        <div class="tlh-obrazek-maly-nadchodzacy-mecz uk-flex-inline">
          <img class="tlh-obrazek-nadchodzacy-mecz"  :src="this.match.homeImage" />
          <h2 class="nadchodzacy-mecz-zespol-host uk-margin-top uk-margin-remove-bottom">{{ this.match.homeTeam.name }} vs
            {{ this.match.visitingTeam.name }}</h2>
          <img class="tlh-obrazek-nadchodzacy-mecz"  :src="this.match.visitingImage" />
        </div>
        <h3 class="tlh-licznik uk-margin-top uk-margin-remove-bottom">Odbędzie się za: </h3>
        <div v-html="this.htmlCountdown" id="countdown-html-element"/>
      </div>
    </div>
  </div>
  <div v-else>
   <PageLoader/>
  </div>

</template>

<script>

import PageLoader from "@/components/Loader/PageLoader";
export default {
  name: "SingleUpcomingMatch",
  components: {PageLoader},
  props: ['match','htmlCountdown'],
}
</script>

<style scoped>
.uk-countdown-number {
  font-size: 2.5rem;
}
.tlh-obrazek-nadchodzacy-mecz {
  height: 100px;
  border-radius: 100%;
  display: inline-block;
}
</style>