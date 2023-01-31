<template>
  <div class="uk-first-column uk-card uk-card-default uk-card-body">
    <div class="tlh-item uk-panel uk-margin-remove-first-child">
      <div class="uk-flex uk-flex-center">
        <div class="tlh-obrazek-sredni">
          <img class="tlh-obrazek" alt="" :src="homeImage" loading="lazy">
        </div>
        <div>
          <h2>{{homeName}} vs {{visitingName}} </h2>
        </div>
        <div class="tlh-obrazek-sredni">
          <img class="tlh-obrazek" alt="" :src="visitingImage" loading="lazy">
        </div>
      </div>

      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom uk-text-center "> {{place }} </h2>
      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom uk-text-center "> {{ startDate }} </h2>
      <div class="uk-margin-top">
        <router-link :to="`/mecz-widok/${id}`">
          <a
             uk-scroll=""
             class="tlh-link uk-button uk-button-default uk-width-1-1 uk-margin">Przejdź do meczu</a>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MatchView",
  props: ['id','place','visitingImage','homeImage','homeName','visitingName','startDate']
}
</script>

<style scoped>

.tlh-obrazek-sredni > img {
  height: 100px;
}
</style>