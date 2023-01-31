<template>
  <div>
    <div uk-grid id="tlh-zawodnicy-grid">
      <div class="uk-width-auto@m" id="tlh-zawodnicy-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-zawodnicy; animation: uk-animation-fade">
          <li><a @click="incrementKey" class="players-tab-left" href="#"><span uk-icon="icon: users" />Zawodnicy</a></li>
          <li><a  @click="incrementKey" class="players-tab-left" href="#"><span uk-icon="icon: plus" />Dodaj zawodnika</a></li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-zawodnicy" class="uk-switcher">
          <li>
            <PlayerListAdmin :key="keyValue"
                             :players="players"
                             @incrementKey="incrementKeyParent"
            />
          </li>
          <li>
            <PlayerForm :key="keyValue"
                          :edit-context="false"
                          :player="{}"
                          id="dodawanie-zawodnika"
                          class="uk-width-1-1@l"
                          uk-scrollspy-switch
            />
            </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import PlayerListAdmin from "@/views/Player/Admin/PlayerListAdmin";
import PlayerForm from "@/components/Players/Player/PlayerForm";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import useKey from "@/composables/useKey";
export default {
  name: "PlayersTabView",
  emits: ['removePlayer','switchToPlayer'],
  components: {
    PlayerListAdmin, PlayerForm
  },
  setup(){
    const {keyValue, incrementKey} = useKey(0, 1)

    return {
      keyValue,
      incrementKey,
    }
  },
  data() {
    return {
      players: [],
    }
  },
  methods: {
    incrementKeyParent(){
      this.$emit('incrementKey')
    }
  },
  created() {
    axios.get("http://localhost:8080/api/players")
        .then(response => {
          const data = response.data.data;
          if (data !== undefined)
            data.forEach((d) => {
              d.image = blobToDataURL(d.image)
              d.imageTeam = blobToDataURL(d.teamImage);

            })
          this.players = data;

        })
  }
}
</script>

<style scoped>
#tlh-zawodnicy-grid {
  background-color: #8debff;
  border-top-left-radius: 10px;
}
#tlh-zawodnicy-uk-width-auto {
  background-color: #a6efff;
  border-top-left-radius: 10px;
}
.uk-tab {
  background-color: #a6efff;
  padding: 10px;

  height: max-content !important;
}
.uk-tab > * > a:hover {
  color: #006394;
  font-weight: bold;
}
.uk-tab > .uk-active > a {
  font-weight: bold;
  color: #006394 !important;
  border-color: #006394;
  border-right-width: 5px;
}

</style>