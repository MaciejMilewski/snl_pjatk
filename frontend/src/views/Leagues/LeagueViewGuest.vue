<template>
  <router-link :to="`single-league/${this.id}`">
    <div class="uk-first-column uk-card uk-card-default uk-card-body">
      <div class="tlh-item uk-panel uk-margin-remove-first-child">

        <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom"> {{ name }} </h2>

      </div>
    </div>
  </router-link>

</template>

<script>
export default {
  name: "LeagueViewGuest",
  props: {
    id: Number,
    name: String,
  },

}
</script>

<style scoped>

</style>