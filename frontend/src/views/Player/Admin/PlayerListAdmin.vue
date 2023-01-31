<template>
    <table class="uk-table uk-table-striped uk-table-hover" id="tlh-tabelka-zawodnicy">
      <thead>
      <tr>
        <th>Zdjęcie</th>
        <th>Id</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Numer</th>
        <th>Pozycja</th>
        <th>Data urodzenia</th>
        <th>Miejsce urodzenia</th>
        <th>Waga (kg)</th>
        <th>Wygięcie kija</th>
        <th>Zespół</th>
        <th>Edytuj</th>
        <th>Usuń</th>
      </tr>
      </thead>
      <tbody v-for="player in players" :key="player.id" >
      <PlayerAdminView :key="player.id"
                       :image="player.image"
                       :id="player.id"
                       :name="player.name"
                       :surname="player.surname"
                       :player-number="player.playerNumber"
                       :position="player.position"
                       :birth-date="player.birthDate"
                       :birthplace="player.birthPlace"
                       :weight="player.weight"
                       :stick="player.stick"
                       :team="player.imageTeam"
                       @incrementKey="incrementKey"
      />
      </tbody>
    </table>
</template>

<script>
import PlayerAdminView from "@/views/Player/Admin/PlayerAdmin";
export default {
  name: "PlayerListAdminView",
  components: {PlayerAdminView},
  props: ['players'],
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

#tlh-tbody-zawodnicy > *:hover {
  background: #a6efff;
}

#tlh-tabelka-zawodnicy {
  border-right-width: 10px;
  border-right-color: red;
}

</style>