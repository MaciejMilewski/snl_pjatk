<template>
  <table class="uk-table uk-table-striped uk-table-hover">
    <thead>
    <tr>
      <th>Id</th>
      <th>Adres email</th>
      <th>Nazwa użytkownika</th>
    </tr>
    </thead>
    <tbody v-for="user in users" :key="user.id" id="tlh-tabelka-zawodnicy">
    <SingleUserView
        :email="user.email"
        :username="user.username"
        :id="user.id"
        @switchToUser="switchToUser"
    />
    </tbody>
  </table>
</template>

<script>
import SingleUserView from "@/views/Users/SingleUserView";
export default {
  name: "UsersListView",
  components: {SingleUserView},
  props: ['users'],
  emits: ['switchToUser'],
  setup(props,context){
    function switchToUser(id){
      context.emit('switchToUser',id)
    }
    return { switchToUser }
  }
}
</script>

<style scoped>
.uk-table-striped > tr:nth-of-type(odd) {
  background: #8debff;
}
.uk-table-hover > *  {
  background: #8debff;
}
#tlh-tabelka-zawodnicy > *:hover {
  background: #a6efff;
}
#tlh-tabelka-zawodnicy > tr > td > img {
  height: 100px;
}
</style>