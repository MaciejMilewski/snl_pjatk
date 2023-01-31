<template>
  <div class="uk-first-column uk-card uk-card-default uk-card-body">
    <div class="tlh-item uk-panel uk-margin-remove-first-child">
      <div class="tlh-obrazek-maly">
        <img class="tlh-obrazek" alt="" :src="image" loading="lazy">
      </div>
      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom"> {{title }} </h2>
      <div class="tlh-data uk-text-meta uk-margin-top">Data utworzenia: {{ date }}</div>
      <div class="tlh-autor uk-text-meta uk-margin-top">Autor: {{ author }}</div>
      <div class="tlh-zawartosc uk-panel uk-margin-top"><div v-html="description"></div></div>
      <div class="uk-margin-top">
        <router-link :to="`/artykul/${id}`">
          <a href="#czytaj_wiecej"
             uk-scroll=""
             class="tlh-link uk-button uk-button-default">Czytaj więcej</a>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ArticleView",
  props: {
      id: Number,
      image: String,
      title: String,
      date: String,
      author: String,
      description: String,
  },


}
</script>

<style scoped>
.tlh-zawartosc {
  height: 100px;
  overflow: hidden;
}
.tlh-tytul {
  display: block;
  height:  100px;
  overflow: hidden;

}
.tlh-obrazek-maly {
  height: 250px;
}
.tlh-obrazek-maly > img {
  height: 250px;
}
</style>