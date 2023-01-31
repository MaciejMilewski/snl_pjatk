<template>
<div class="uk-container uk-align-center">
  <a href="#panel" @click='changeContextView' id="przycisk-powrot" class="uk-button-default"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
  <ArticleFormComponent :edit-context="true" :article="article" :key="keyValue"/>
</div>
</template>

<script>
import useKey from "@/composables/useKey";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {useRoute} from "vue-router";
import {reactive, toRefs} from "vue";
import axios from "axios";
import UIkit from "uikit";
import ArticleFormComponent from "@/components/Article/ArticleForm";
export default {
  name: "EditArticle",
  components: {ArticleFormComponent},
  setup(){
    const {keyValue, incrementKey} = useKey(0, 1)

    const router = useRoute()
    const state = reactive({
      id: router.params.id
    })
    return {
      keyValue,
      incrementKey,
      ...toRefs(state),
      router
    }
  },
  data(){
    return {
      article: {}
    }
  },
  methods: {
    changeContextView(){
     setTimeout(()=>{
       UIkit.switcher('#component-tab-left').show(1)
     },1000)
    }
  },
  created() {
    axios.get("http://localhost:8080/api/news/"+this.id)
        .then((response) =>{
          this.article = response.data
          if(this.article.image !== null){
            this.article.image = blobToDataURL(this.article.image)
          }
          this.incrementKey()
        })

  }
}
</script>

<style scoped>
#przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
}
</style>