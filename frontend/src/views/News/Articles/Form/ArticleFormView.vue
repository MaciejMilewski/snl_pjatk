<template>

  <form  @submit.prevent="submitForm" class="uk-form-large" style="padding-right: 0">

    <!--    Informacje podstawowe-->
    <div class="uk-form-controls">
      <ImageInputLargeView id="artykul-zdjecie" v-model:image-object="reactiveForm.imageObjectValue"/>
    </div>

    <div class="uk-form-controls">
      <label for="informacje-podstawowe" class="uk-label">Informacje podstawowe</label>
      <div class="uk-grid" id="informacje-podstawowe">
        <div class="uk-width-1-1" id="tytul">
          <TitleView :id="viewIds.titleId" v-model:title="reactiveForm.titleValue"/>
        </div>
        <div class="uk-width-1-3">
          <AuthorView :id="viewIds.authorId" v-model:author="reactiveForm.authorValue"/>
        </div>
      </div>
    </div>
    <div class="uk-form-controls">
      <label for="opis-artykulu" class="uk-label">Opis artykułu</label>
      <div class="uk-grid" id="opis-artykulu">
        <div class="uk-width uk-margin">
          <VueEditor v-model="reactiveForm.content"/>
        </div>
      </div>
    </div>

    <div v-if="editContext === false">
      <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Dodaj artykuł</button>
    </div>
    <div v-else>
      <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Aktualizuj artykuł</button>
    </div>
  </form>
</template>

<script>
import TitleView from "@/views/Inputs/Title";
import AuthorView from "@/views/Inputs/Author";
import {VueEditor} from "vue3-editor"
import {computed, reactive} from "vue";
import ImageInputLargeView from "@/views/Inputs/ImageInputLarge";

export default {
  name: "ArticleFormView",
  emits:['submitArticleForm'],
  props: ['article','editContext'],
  components: {ImageInputLargeView, VueEditor, TitleView, AuthorView},
  data() {
    return {

      viewIds: {
        titleId: "artykul-tytul",
        authorId: "artykul-autor"
      }
    }
  },
  setup(props, context) {
    const state = reactive({
      form: {
        imageObjectValue: null,
        titleValue: '',
        authorValue: '',
        content: '',
      },
      formArticle: {
        imageObjectValue: {
          previewImage: props.article.image
        },
        titleValue: props.article.title,
        authorValue: props.article.author,
        content: props.article.description

      }
    })
    const reactiveForm = computed(function () {
      if(props.article.title)
        return state.formArticle
      else
      return state.form
    })
    function submitForm(){
      if(props.article.title)
      context.emit('submitArticleForm', reactiveForm.value, 'update', props.article.id)
      else context.emit('submitArticleForm', reactiveForm.value, 'post')
    }
    return {
      reactiveForm,
      submitForm
    }

  }
}
</script>

<style scoped>

</style>