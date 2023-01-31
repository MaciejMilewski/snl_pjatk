<template>
  <form @submit.prevent="submitForm" style="padding-right: 0;">

    <!--    Informacje podstawowe-->
    <div class="uk-form-controls">
      <ImageInputView id="sponsor-zdjecie" v-model:image-object="reactiveForm.imageObjectValue"/>
    </div>

    <div class="uk-form-controls">
      <label for="informacje-podstawowe" class="uk-label">Nazwa sponsora</label>
      <div class="uk-grid" id="informacje-podstawowe">
        <div class="uk-width-1-1" id="nazwa">
          <TeamNameView :id="viewIds.nameId" v-model:name="reactiveForm.nameValue" />
        </div>
      </div>
    </div>
      <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Dodaj sponsora</button>
  </form>
</template>

<script>
import ImageInputView from "@/views/Inputs/ImageInput";
import {computed, reactive} from "vue";
import TeamNameView from "@/views/Inputs/NameView";
export default {
  name: "SponsorFormView",
  components: {TeamNameView, ImageInputView},
  data(){
    return {
      viewIds: {
        nameId: 'sponsor-nazwa'
      }
    }
  },
  setup(props, context){
    const state = reactive({
      form: {
        imageObjectValue: null,
        nameValue: '',
      },
    })
    const reactiveForm = computed(function (){
        return state.form
    })
    // Submmit method
    function submitForm(){
      context.emit('submitSponsorForm',reactiveForm.value)
    }
    return {
      reactiveForm,
      submitForm
    }
  },

}
</script>

<style scoped>

</style>