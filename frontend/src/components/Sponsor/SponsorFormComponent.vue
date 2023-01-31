<template>
  <SponsorFormView :sponsor="{}" @submitSponsorForm="submitSponsorForm"/>
</template>

<script>
import SponsorFormView from "@/views/Sponsors/Form/SponsorForm";
import dataURLtoBlob from "@/assets/scripts/dataURLtoBlob";
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "SponsorFormComponent",
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  components: {SponsorFormView},
  methods: {
    submitSponsorForm(form) {
      let imageBlob = null;
      if (typeof form.imageObjectValue === 'object') {
        if (form.imageObjectValue !== null) {
          imageBlob = dataURLtoBlob(form.imageObjectValue.previewImage)
        }
      } else {
        imageBlob = dataURLtoBlob(form.imageObjectValue)
      }

      axios.post('http://localhost:8080/api/sponsors',  {
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
              message: 'Pomyślnie dodano nowego sponsora.',
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
    }
  },

}
</script>

<style scoped>

</style>