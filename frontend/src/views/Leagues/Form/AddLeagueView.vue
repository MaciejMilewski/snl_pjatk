<template>
  <form @submit.prevent="submitForm" style="padding-right: 0;">

    <div class="uk-form-controls">
      <label for="informacje-podstawowe" class="uk-label">Nazwa ligi</label>
      <div class="uk-grid" id="informacje-podstawowe">
        <div class="uk-width-1-1" id="nazwa-ligi">
          <NameView :id="viewIds.nameId" v-model:name="reactiveForm.nameValue"/>
        </div>
      </div>
    </div>
    <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Dodaj ligę</button>
  </form>
</template>

<script>
import NameView from "@/views/Inputs/NameView";
import {computed, reactive} from "vue";
import UIkit from "uikit";
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "AddLeagueView",
  components: {NameView},
  computed: {...mapGetters({User: "StateUser"})},
  setup() {
    const state = reactive({
      form: {
        nameValue: '',
      }
    })
    const reactiveForm = computed(function () {
      return state.form
    })
    return {
      reactiveForm
    }
  },
  methods: {
    submitForm() {
      if (this.reactiveForm.nameValue === '') {
        UIkit.notification({
          message: 'Podaj nazwę ligi',
          status: 'warning',
          pos: 'top-center',
          timeout: 5000
        })
      } else {
        axios.post("http://localhost:8080/api/leagues", {
          name: this.reactiveForm.nameValue
        }, {
          headers: {
             'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `multipart/form-data`,
          }
        })
            .then(function (){
              UIkit.notification({
                message: 'Pomyślnie dodano nową ligę.',
                status: 'success',
                pos: 'top-center',
                timeout: 5000
              })
            })
      }
    }
  },
  data() {
    return {
      viewIds: {
        nameId: 'liga-nazwa'
      }
    }
  }
}
</script>

<style scoped>

</style>