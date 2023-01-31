<template>
  <div class="uk-first-column">
    <div class="tlh-item uk-panel uk-margin-remove-first-child">
      <div class="tlh-obrazek-maly-sponsor">
        <img class="tlh-obrazek-sponsor" alt="" :src="image" loading="lazy" style="height: 100px">
      </div>
      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom"> {{ name }} </h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "SponsorView",
  props: ['name','image']
}
</script>

<style scoped>

</style>