<template>
  <table class="uk-table uk-table-striped uk-table-hover">
    <thead>
    <tr>
      <th>Zdjęcie</th>
      <th>Id</th>
      <th>Nazwa</th>
      <th>Edytuj</th>
      <th>Usuń</th>
    </tr>

    </thead>
    <tbody v-for="team in teams" :key="team.id" id="tlh-tabelka-zawodnicy">
    <TeamAdminView @incrementKey="incrementKey"
                   :image="team.image"
                   :id="team.id"
                   :name="team.name"
    />

    </tbody>
  </table>
</template>

<script>
import TeamAdminView from "@/views/Teams/Team/Admin/TeamAdminView";
export default {
  name: "TeamListAdmin",
  components: {TeamAdminView},
  props:['teams'],
  emits: ['incrementKey'],
  setup(props,context){
    function incrementKey(){
      context.emit('incrementKey')
    }
    return {
      incrementKey,
    }
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