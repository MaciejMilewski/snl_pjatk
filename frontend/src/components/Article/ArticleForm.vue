<template>
<ArticleFormView :edit-context="false" :article="{}" @submitArticleForm="submitArticleForm"/>
</template>

<script>
import axios from 'axios'
import UIkit from "uikit";
import dataURLtoBlob from "@/assets/scripts/dataURLtoBlob";
import ArticleFormView from "@/views/News/Articles/Form/ArticleFormView";
import {mapGetters} from "vuex";
export default {
  name: "ArticleFormComponent",
  components: {ArticleFormView},
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    submitArticleForm(form,method, id){
      let today = new Date().toISOString().slice(0,10);
      let imageBlob = null;
      if(typeof form.imageObjectValue === 'object'){
        if(form.imageObjectValue !== null){
          imageBlob = dataURLtoBlob(form.imageObjectValue.previewImage)
        }
      }else {
        imageBlob = dataURLtoBlob(form.imageObjectValue)
      }

      if(method === 'post'){
        axios.post('http://localhost:8080/api/news', {
          description: form.content,
          author: form.authorValue,
          date: today,
          title: form.titleValue,
          image: imageBlob
        }, {
          headers: {
            'Authorization': `Bearer `+this.User.userInfo.token ,
            'Content-Type': `multipart/form-data`,
          }})
            .then(function (){
              UIkit.notification({
                message: 'Pomyślnie dodano nowy artykuł.',
                status: 'success',
                pos: 'top-center',
                timeout: 5000
              })
            })
            .catch((error) => {
              let message = "[Błąd - " + error.response.status + "] - " + error.response.data.message
              UIkit.notification({
                message: message,
                status: 'danger',
                pos: 'top-center',
                timeout: 5000
              })
              console.log(error)
            })
      }else if(method === 'update') {
        axios.put('http://localhost:8080/api/news/'+id, {
          description: form.content,
          author: form.authorValue,
          date: today,
          title: form.titleValue,
          image: imageBlob
        }, {
          headers: {
            'Authorization': `Bearer `+this.User.userInfo.token,
            'Content-Type': `multipart/form-data`,
          }})
            .then(function (){
              UIkit.notification({
                message: 'Pomyślnie zaktualizowano artykuł.',
                status: 'success',
                pos: 'top-center',
                timeout: 5000
              })
            })
            .catch((error) => {
              let message = "[Błąd - " + error.response.status + "] - " + error.response.data.message
              UIkit.notification({
                message: message,
                status: 'danger',
                pos: 'top-center',
                timeout: 5000
              })
              console.log(error)
            })
      }

    }
  }

}
</script>

<style scoped>

</style>