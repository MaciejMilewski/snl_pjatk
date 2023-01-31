<template>
  <tr>
    <td><img :src="image" style="height: 100px; width: auto"></td>
    <td>{{ id }}</td>
    <td>{{ name }}</td>
    <td>
      <router-link :to="`/edytuj-zespol/${id}`"><span uk-icon="icon:  file-edit"/></router-link>
    </td>
    <td><a @click="removeTeam(id)">
      <div uk-icon="icon:  minus"/>
    </a></td>
  </tr>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "TeamAdminView",
  props: ['image', 'id', 'name'],
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    removeTeam(id) {
      axios.delete('http://localhost:8080/api/teams/' + id, {
        headers: {
          'Authorization': 'Bearer ' + this.User.userInfo.token,
        }
      })
          .then(response => {
            if (response.status === 204) {
              UIkit.notification({
                message: 'Pomyślnie usunięto zespół o id = ' + id,
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              })
            }
          })
      setTimeout(() => {
        axios.delete('http://localhost:8080/api/teams/' + id, {
          headers: {
            'Authorization': 'Bearer ' + this.User.userInfo.token,
          }
        })
      },1000)
      setTimeout(() => {
        this.$emit('incrementKey')
      }, 1500)


    }
  },

}
</script>

<style scoped>

</style>