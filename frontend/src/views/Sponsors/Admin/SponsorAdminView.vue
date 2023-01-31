<template>
  <tr>
    <td><img :src="image" style="height: 100px; width: auto"></td>
    <td>{{ id }}</td>
    <td>{{ name }}</td>
    <td><a @click="removeSponsor(id)">
      <div uk-icon="icon:  minus"/>
    </a></td>
  </tr>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "SponsorAdminView",
  props: ['image', 'id', 'name'],
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    removeSponsor(id) {
      axios.delete('http://localhost:8080/api/sponsors/' + id, {
        headers: {
          'Authorization': 'Bearer ' + this.User.userInfo.token,
        }
      })
          .then(response => {
            if (response.status === 204) {
              UIkit.notification({
                message: 'Pomyślnie usunięto sponsora o id = ' + id,
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              })
            }
          })
      setTimeout(() => {
        this.$emit('incrementKey', 'sponsor')
      }, 500)

    }
  },

}
</script>

<style scoped>

</style>