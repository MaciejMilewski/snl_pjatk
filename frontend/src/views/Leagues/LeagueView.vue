<template>
  <div class="uk-first-column uk-card uk-card-default uk-card-body">
    <div class="tlh-item uk-panel uk-margin-remove-first-child">

      <h2 class="tlh-tytul uk-margin-top uk-margin-remove-bottom"> {{name }} </h2>
      <div class="uk-margin-top">
          <a @click="removeLeague(id)" href="#czytaj_wiecej"
             uk-scroll=""
             class="tlh-link uk-button uk-button-default"><span uk-icon="icon: minus"/>&nbsp;Usuń ligę</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";
export default {
  name: "LeagueView",
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  props: {
    id: Number,
    name: String,
  },
  methods: {
    removeLeague(id){
      axios.delete("http://localhost:8080/api/leagues/"+id, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
        }
      }).then(response => {
        if(response.status === 204){
          UIkit.notification({
            message: 'Pomyślnie usunięto ligę o id = '+id,
            status: 'success',
            pos: 'top-center',
            timeout: 5000
          })
        }else {
          UIkit.notification({
            message: 'Wystąpił błąd',
            status: 'error',
            pos: 'top-center',
            timeout: 5000
          })
        }
      })
      setTimeout(()=>{
        this.$emit('incrementKey')
      },500)
    }
  }
}
</script>

<style scoped>

</style>