<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-right-medium; delay: none;">
    <div class="uk-container">
      <a href="#" id="przycisk-powrot" class="uk-button-default"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
      <div class="uk-grid-margin uk-grid" uk-grid="">
        <div class="uk-width-1-2@m uk-first-column">
          <div class="uk-margin uk-scrollspy-inview" uk-scrollspy-class style="">
            <img class="tlh-obrazek" id="obrazek-duzy" :src="article.image" alt="Duży obrazek w widoku single article"
                 loading="lazy">
          </div>
        </div>
        <div class="uk-width-1-2@m">
          <h1 uk-scrollspy-class="" class="uk-scrollspy-inview" style="">{{ article.title }}</h1>
          <div class="uk-panel uk-margin uk-scrollspy-inview" uk-scrollspy-class style="">
            <p class="uk-text-meta">Data utworzenia: {{ article.date }}</p>
          </div>
          <div class="uk-panel uk-margin uk-scrollspy-inview" uk-scrollspy-class style="">
            <p class="uk-text-meta">Autor: {{ article.author }}</p></div>
          <div class="uk-panel uk-margin uk-scrollspy-inview " uk-scrollspy-class style="" v-html="article.description">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {useRoute} from 'vue-router'
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {reactive, toRefs} from "vue";

export default {
  name: "SingleArticleView",
  setup() {
    const router = useRoute();
    const state = reactive({
      id: router.params.id
    })

    return {
      ...toRefs(state),
      router,
    }

  },
  data() {
    return {
      article: {}
    }
  },
  mounted() {
    axios.get("http://localhost:8080/api/news/" + this.id)
        .then((response) => {
          response.data.image = blobToDataURL(response.data.image)
          this.article = response.data
        })
  },

}
</script>

<style scoped>
#przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
}

.tlh-obrazek {
  height: 500px;
}
</style>