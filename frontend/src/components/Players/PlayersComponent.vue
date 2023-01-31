<template>
  <div class="uk-section-large">
    <div class="uk-container">
      <h1>Zawodnicy:</h1>
      <PlayerListView :players="players"/>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import PlayerListView from "@/views/Player/PlayerList";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
export default {
  name: "PlayersComponent",
  components: {PlayerListView},
  data() {
    return {
      players: []
    }
  },
  created(){
    axios.get("http://localhost:8080/api/players")
        .then(response => {
          const data = response.data.data;
          if(data !== undefined)
          data.forEach((d) => {
            d.image = blobToDataURL(d.image)
          })
          this.players = data;

        })
  },
}

</script>

