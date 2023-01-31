<template>
  <div class="uk-card uk-card-default uk-card-body">
      <router-link :to="`/zespol/${id}/${leagueId}`">
        <div class="tlh-obrazek-maly">
          <img class="tlh-obrazek" alt="" :src="image" loading="lazy">
        </div>
        <h2 class="uk-margin-top uk-margin-remove-bottom"> {{name }} </h2>

      </router-link>


  </div>
</template>

<script>
export default {
  name: "TeamView",
  props:['id','image','name','leagueId'],
}
</script>

<style scoped>
.tlh-obrazek-maly {
  height: 150px;
}
.tlh-obrazek-maly > img {
  height: 150px;
}
.uk-card:hover {
  background-color: #a6efff;
}
</style>