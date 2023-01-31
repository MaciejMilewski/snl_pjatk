<template>
  <tr>
    <td><img :src="image" style="height: 100px; width: auto"></td>
    <td>{{ id }}</td>
    <td>{{ author }}</td>
    <td>{{ title }}</td>
    <td>
      <router-link :to="`/edytuj-artykul/${id}`"><span uk-icon="icon:  file-edit"/></router-link>
    </td>
    <td><a @click="removeArticle(id)"><span uk-icon="icon:  minus"/></a></td>
  </tr>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "NewsAdminView",
  props: ['image', 'id', 'author', 'title'],
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    removeArticle(id) {
      axios.delete('http://localhost:8080/api/news/' + id, {
        headers: {
          'Authorization': 'Bearer ' + this.User.userInfo.token,
        }
      })
          .then(response => {
            if (response.status === 204) {
              UIkit.notification({
                message: 'Pomyślnie usunięto artykuł o id = ' + id,
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              })
            }
          })
      this.$emit('incrementKey')

    }
  },

}
</script>

<style scoped>

</style>