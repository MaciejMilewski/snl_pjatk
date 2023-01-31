<template>
  <TeamFormView :edit-context="false" :team="{}" @submitTeamForm="submitTeamForm"/>
</template>

<script>
import TeamFormView from "@/views/Teams/Team/Form/TeamForm";
import axios from "axios";
import UIkit from "uikit";
import dataURLtoBlob from "@/assets/scripts/dataURLtoBlob";
import {mapGetters} from "vuex";

export default {
  name: "TeamFormComponent",
  components: {TeamFormView},
  computed: {...mapGetters({User: "StateUser"})},
  methods: {
    submitTeamForm(form, method, id) {
      let imageBlob = null;
      if (typeof form.imageObjectValue === 'object') {
        if (form.imageObjectValue !== null) {
          imageBlob = dataURLtoBlob(form.imageObjectValue.previewImage)
        }
      } else {
        imageBlob = dataURLtoBlob(form.imageObjectValue)
      }
      if (method === 'post') {
        axios.post('http://localhost:8080/api/leagues/' + form.league + '/teams', {
          name: form.nameValue,
          image: imageBlob
        }, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `multipart/form-data`,
          }
        })
            .then(function () {
              UIkit.notification({
                message: 'Pomyślnie dodano nowy zespół.',
                status: 'success',
                pos: 'top-center',
                timeout: 5000
              })
            })
            .catch((error) => {
              console.log(error)
              let message = "[Błąd - " + error.response.status + "] - " + error.response.data.message
              UIkit.notification({
                message: message,
                status: 'danger',
                pos: 'top-center',
                timeout: 5000
              })
              console.log(error)
            })

      } else {
        axios.put('http://localhost:8080/api/teams/' + id, {
          name: form.nameValue,
          image: imageBlob
        }, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `multipart/form-data`,
          }
        })
            .then(function () {
              UIkit.notification({
                message: 'Pomyślnie zaktualizowano zespół.',
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