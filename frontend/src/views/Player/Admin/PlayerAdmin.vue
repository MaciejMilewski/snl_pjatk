<template>
  <tr>
    <td><img :src="image" style="width: 100px;"/></td>
    <td>{{ id }}</td>
    <td>{{ name }}</td>
    <td>{{ surname }}</td>
    <td>{{ playerNumber }}</td>
    <td>{{ position }}</td>
    <td>{{ birthDate }}</td>
    <td>{{ birthplace }}</td>
    <td>{{ weight }}</td>
    <td>{{ stick }}</td>
    <td><img :src="team" style="width: 100px;"/></td>
    <td>
      <router-link :to="`/edytuj-zawodnika/${id}`"><span uk-icon="icon:  file-edit"/></router-link>
    </td>
    <td><a @click="removePlayer(id)"><span uk-icon="icon:  minus"/></a></td>
  </tr>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "PlayerAdminView",
  props: ['image', 'id', 'name', 'surname', 'playerNumber', 'position', 'birthDate', 'birthplace', 'weight', 'stick', 'team'],
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    removePlayer(id) {
      axios.delete('http://localhost:8080/api/players/' + id, {
        headers: {
          'Authorization': 'Bearer ' + this.User.userInfo.token,
        }
      })
          .then(response => {
            if (response.status === 204) {
              UIkit.notification({
                message: 'Pomyślnie usunięto zawodnika o id = ' + id,
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              })
            }
          })
      setTimeout(() => {
        this.$emit('incrementKey')
      }, 1000)

    },
  }


}
</script>

<style scoped>
#edycja-zawodnika {
  background: #006394;
  border-radius: 25px;
  z-index: 999;
  border: 5px solid #009fb6;
}

.uk-modal-title {
  background: #a6efff;
  padding: 10px;
  border-top-right-radius: 25px;
  border-bottom-left-radius: 25px;
  color: #009fb6;
}

#edycja-zawodnika-dialog {
  background: #006394;
}

#wyszukiwarka-ikona {
  margin-left: 20px;
}

#przycisk-zamkniecie {
  padding: 0;
}
</style>