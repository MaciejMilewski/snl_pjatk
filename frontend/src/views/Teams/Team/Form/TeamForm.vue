<template>
  <form @submit.prevent="submitForm" class="" style="padding-right: 0;">

    <!--    Informacje podstawowe-->
    <div class="uk-form-controls">
      <ImageInputView id="zespol-zdjecie" v-model:image-object="reactiveForm.imageObjectValue"/>
    </div>

    <div class="uk-form-controls">
      <label for="informacje-podstawowe" class="uk-label">Nazwa drużyny</label>
      <div class="uk-grid" id="informacje-podstawowe">
        <div class="uk-width-1-1" id="nazwa">
          <TeamNameView :id="viewIds.nameId" v-model:name="reactiveForm.nameValue" />
        </div>
      </div>
    </div>

    <div class="uk-form-controls">
      <label for="informacje-podstawowe-liga" class="uk-label">Liga</label>
      <div class="uk-grid" id="informacje-podstawowe-liga">
        <div class="uk-width-1-1" id="liga">
          <select v-model="reactiveForm.league" class="uk-select">
            <option v-for="league in leagues" :key="league.id" :value="league.id">{{league.name}}</option>
          </select>
        </div>
      </div>
    </div>

    <div v-if="editContext === false">
      <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Dodaj zespół</button>
    </div>
    <div v-else>
      <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Aktualizuj zespół</button>
    </div>
  </form>
</template>

<script>
import ImageInputView from "@/views/Inputs/ImageInput";
import {computed, reactive} from "vue";
import TeamNameView from "@/views/Inputs/NameView";
import axios from "axios";
export default {
  name: "TeamFormView",
  components: {TeamNameView, ImageInputView},
  props: ['team','editContext'],
  data(){
    return {
      viewIds: {
        nameId: 'zespol-nazwa'
      },
      leagues: [],
    }
  },
  created() {
    axios.get("http://localhost:8080/api/leagues")
        .then(response => {
          this.leagues = response.data;
        })
  },
  setup(props, context){
    const state = reactive({
      form: {
        imageObjectValue: null,
        nameValue: '',
        league: '',

      },
      formTeam: {
        imageObjectValue: {
          previewImage: props.team.image
        },
        league: props.league,
        nameValue: props.team.name
      }
    })
    const reactiveForm = computed(function (){
      if(props.team.name){
        return state.formTeam
      }else
      return state.form
    })
    // Submmit method
    function submitForm(){
      if(props.team.name){
        context.emit('submitTeamForm', reactiveForm.value,'update',props.team.id)
      }else context.emit('submitTeamForm',reactiveForm.value,'post')
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