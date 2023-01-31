<template>
  <table class="uk-table uk-table-striped uk-table-hover">
    <thead>
    <tr>
      <th>Zdjęcie</th>
      <th>Id</th>
      <th>Nazwa</th>
      <th>Usuń</th>
    </tr>

    </thead>
    <tbody v-for="sponsor in sponsors" :key="sponsor.id" id="tlh-tabelka-zawodnicy">
    <SponsorAdminView @incrementKey="incrementKey"
                   :image="sponsor.image"
                   :id="sponsor.id"
                   :name="sponsor.name"
    />

    </tbody>
  </table>
</template>

<script>
import SponsorAdminView from "@/views/Sponsors/Admin/SponsorAdminView";
export default {
  name: "SponsorsListAdmin",
  components: {SponsorAdminView},
  props:['sponsors'],
  setup(props,context){
    function incrementKey(){
      context.emit('incrementKey')
    }
    return {
      incrementKey
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