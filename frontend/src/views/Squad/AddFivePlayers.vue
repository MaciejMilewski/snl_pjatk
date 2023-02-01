<template>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(0)">
      <div v-if="playersFive[0].name">
        <p>{{ playersFive[0].name }}</p>
        <img :src="playersFive[0].img" style="height: 60px">
        <p>{{ playersFive[0].surname }}</p>
        <div class="player-position">{{ playersFive[0].position }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>

    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(1)">
      <div v-if="playersFive[1]">
        <p>{{ playersFive[1].name }}</p>
        <img :src="playersFive[1].img" style="height: 60px">
        <p>{{ playersFive[1].surname }}</p>
        <div class="player-position">{{ playersFive[1].position }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(2)">
      <div v-if="playersFive[2]">
        <p>{{ playersFive[2].name }}</p>
        <img :src="playersFive[2].img" style="height: 60px">
        <p>{{ playersFive[2].surname }}</p>
        <div class="player-position">{{ playersFive[2].position }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(3)">
      <div v-if="playersFive[3]">
        <p>{{ playersFive[3].name }}</p>
        <img :src="playersFive[3].img" style="height: 60px">
        <p>{{ playersFive[3].surname }}</p>
        <div class="player-position">{{ playersFive[3].position }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(4)">
      <div v-if="playersFive[4]">
        <p>{{ playersFive[4].name }}</p>
        <img :src="playersFive[4].img" style="height: 60px">
        <p>{{ playersFive[4].surname }}</p>
        <div class="player-position">{{ playersFive[4].position }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
  </div>

  <div id="sklad-piatka" uk-modal>
    <div class="uk-modal-dialog uk-modal-body">
      <h2 class="uk-modal-title">Wybierz zawodnika</h2>
      <button class="uk-modal-close-default" type="button" uk-close></button>
      <SquadMultiSelect ref="squad-multi-select" id="sklad-multi-select"  :team-id="1" :key="keyValue" @playerSelection="playerSelection" :provide-players="false"/>
      <PlayerPositionView ref="position-multi-select" id="pozycja-multi-select"/>
      <label for="player-number" class="uk-label" style="margin-top: 10px;">Numer zawodnika</label>
      <PlayerNumberView name="player-number" v-model:player-number.number="playerNumber" style="margin-top: 10px;"/>
      <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-modal-close" @click="savePlayer()">Zapisz</button>
    </div>
  </div>
  <TeamNameView v-model:name="reactiveForm.nameValue"/>
  <button id="przycisk-zapisz-sklad" class="uk-button uk-button-default uk-width-1-1 uk-margin" @click='saveFive()'>Dodaj piątkę</button>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import SquadMultiSelect from "@/views/Squad/SquadMultiSelect";
import PlayerPositionView from "@/views/Inputs/Position"
import PlayerNumberView from "@/views/Inputs/PlayerNumber";
import UIkit from "uikit";
import axios from "axios";
import TeamNameView from "@/views/Inputs/NameView";
import {computed, reactive} from "vue";
export default {
  name: "AddFivePlayers",
  components: {TeamNameView, SquadMultiSelect, PlayerPositionView, PlayerNumberView},
  data(){
    return {
      playerNumber: 0,
    }
  },
  setup(){
    const state = reactive({
      form: {
        nameValue: '',
      }
    })
    const reactiveForm = computed(function(){
      return state.form
    })
    return {
      reactiveForm
    }
  },
  computed: {
    ...mapGetters({User: "StateUser",playerIndex: "StatePlayerIndex", playerTemp: "StatePlayerFiveTemp", playersFive:"StatePlayersFive"})
  },
  methods: {
    ...mapActions(['SetPlayerIndex','SetPlayerFiveTemp','SetPlayerFive']),
    async changeIndex(index){
      await this.SetPlayerIndex(index)
    },
    saveFive(){
      let nameValue = this.reactiveForm.nameValue;
      console.log(this.playersFive)
      if(this.playersFive.length !== 5){
        UIkit.notification({
          message:"Piątka nie jest pełna!",
          status: "warning",
          pos: "top-center",
          timeout: 5000
        })
      } else {
        // Zapisanie piątki
        axios.post('http://localhost:8080/api/teams/1/fives', {
          name: nameValue,
          players: this.playersFive
        }, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
          }
        }).then(function(){
          UIkit.notification({
            message: "Poprawnie dodano nową piątkę: '"+nameValue+"'",
            status: "success",
            pos: "top-center",
            timeout: 5000
          })
            })


      }

    },
    togglePlayer(){
      const modal = UIkit.modal("#sklad-piatka")
      modal.toggle()
    },
    async playerSelection(player){
      await this.SetPlayerFiveTemp(player)
    },
    async savePlayer(){
      let canAddPlayer = true;
      if (this.$refs["position-multi-select"].value === null) {
        canAddPlayer = false;
        UIkit.notification({
          message: 'Nie wybrano pozycji!',
          status: 'warning',
          pos: 'top-center',
          timeout: 10000
        });
      }
      if (this.playerTemp == null) {
        canAddPlayer = false;
        UIkit.notification({
          message: 'Nie wybrano zawodnika!',
          status: 'warning',
          pos: 'top-center',
          timeout: 10000
        });
      }
      if(canAddPlayer){
        let player = this.playerTemp;
        let position = this.$refs["position-multi-select"].value;
        player.position = position.position;
        player.number = this.playerNumber;

        // Sprawdzanie czy zawodnik już istnieje
        this.playersFive.forEach((p) => {
          if (p.id === this.playerTemp.id){
            canAddPlayer = false;
            UIkit.notification({
              message: "Ten zawodnik już istnieje w składzie",
              status: "warning",
              pos: "top-center"
            })
          }
        })
        // Przypisanie zawodnika
        if(canAddPlayer)
          await this.SetPlayerFive({playerTemp: this.playerTemp, index: this.playerIndex})

        this.$refs["position-multi-select"].value = null;

      }
    }
  }
}
</script>

<style scoped lang="less">
.dodawanie-skladu {
  background-color: #a6efff;
  padding: 10px;
  display: inline-block;
  margin-left: 10px;
  margin-top: 10px;
  margin-bottom: 10px;

  &:hover {
    color: #a6efff;
    background-color: #0e6dcd;
  }
}

.player-position {
  color: #0e6dcd;
  font-size: x-large;
  font-weight: bold;

  &:hover {
    color: #a6efff;
  }
}

#przycisk-zapisz-sklad {
  margin-bottom: 50px;
}

.uk-select {
  display: inline-block !important;
}


.uk-modal-title {
  background: #a6efff;
  padding: 10px;
  border-top-right-radius: 25px;
  border-bottom-left-radius: 25px;
  color: #009fb6;
}

#sklad-dialog {
  background: #006394;
}

#sklad-ikona {
  margin-left: 20px;
}

#przycisk-zamkniecie {
  padding: 0;
  margin-top: 15px;
}

.uk-button {
  margin-left: 10px
}
</style>