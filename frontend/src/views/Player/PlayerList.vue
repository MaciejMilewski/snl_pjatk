<template>
  <table class="uk-table uk-table-striped uk-table-hover">
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
    </tr>
    </thead>
    <tbody v-for="player in players" :key="player.id" id="tlh-tabelka-zawodnicy">
        <PlayerView :key="player.id"
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
        :team-id="player.teamId"
                    :viewType="viewType"
        />
    </tbody>
  </table>
</template>

<script>
import PlayerView from '@/views/Player/Player'
export default {
  name: "PlayerListView",
  components: {PlayerView},
  props: ['players','viewType']
}
</script>

<style scoped>
.uk-table-striped > tr:nth-of-type(odd) {
  background: #8debff;
}
.uk-table-hover > *  {
  background: #8debff;
}
.uk-table-hover > tr > tr {
  background: red;
}
#tlh-tabelka-zawodnicy > *:hover {
  background: #a6efff;
}
#tlh-tabelka-zawodnicy > tr > td > img {
  height: 100px;
}
</style>