<template>

  <a @click="refreshSquad">
          <span id="sklad-refresh"
                uk-icon="icon: refresh; ratio: 2"
                style="background-color: #a6efff; padding: 10px"
                class="sklad-refresh"
          />
  </a>
  <h2>Pierwsza piątka</h2>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(0)">
      <div v-if="players[0].name">
        <p>{{ players[0].name }}</p>
        <img :src="players[0].img" style="height: 60px">
        <p>{{ players[0].surname }}</p>
        <div class="player-position">{{ players[0].position }}</div>
        <div class="player-position">{{ players[0].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>

    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(1)">
      <div v-if="players[1]">
        <p>{{ players[1].name }}</p>
        <img :src="players[1].img" style="height: 60px">
        <p>{{ players[1].surname }}</p>
        <div class="player-position">{{ players[1].position }}</div>
        <div class="player-position">{{ players[1].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(2)">
      <div v-if="players[2]">
        <p>{{ players[2].name }}</p>
        <img :src="players[2].img" style="height: 60px">
        <p>{{ players[2].surname }}</p>
        <div class="player-position">{{ players[2].position }}</div>
        <div class="player-position">{{ players[2].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(3)">
      <div v-if="players[3]">
        <p>{{ players[3].name }}</p>
        <img :src="players[3].img" style="height: 60px">
        <p>{{ players[3].surname }}</p>
        <div class="player-position">{{ players[3].position }}</div>
        <div class="player-position">{{ players[3].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(4)">
      <div v-if="players[4]">
        <p>{{ players[4].name }}</p>
        <img :src="players[4].img" style="height: 60px">
        <p>{{ players[4].surname }}</p>
        <div class="player-position">{{ players[4].position }}</div>
        <div class="player-position">{{ players[4].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="uk-margin">
      <select class="uk-select uk-display-inline-block" aria-label="Select" @change="selectFive"
              @click="setFiveIndex(1)">
        <option value="0">Wybierz piątkę</option>
        <option v-for="five in fives" :value="five.id" v-bind:key="five.id">{{ five.name }}</option>
      </select>
    </div>
  </div>

  <h2>Druga piątka</h2>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(5)">
      <div v-if="players[5]">
        <p>{{ players[5].name }}</p>
        <img :src="players[5].img" style="height: 60px">
        <p>{{ players[5].surname }}</p>
        <div class="player-position">{{ players[5].position }}</div>
        <div class="player-position">{{ players[5].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(6)">
      <div v-if="players[6]">
        <p>{{ players[6].name }}</p>
        <img :src="players[6].img" style="height: 60px">
        <p>{{ players[6].surname }}</p>
        <div class="player-position">{{ players[6].position }}</div>
        <div class="player-position">{{ players[6].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(7)">
      <div v-if="players[7]">
        <p>{{ players[7].name }}</p>
        <img :src="players[7].img" style="height: 60px">
        <p>{{ players[7].surname }}</p>
        <div class="player-position">{{ players[7].position }}</div>
        <div class="player-position">{{ players[7].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(8)">
      <div v-if="players[8]">
        <p>{{ players[8].name }}</p>
        <img :src="players[8].img" style="height: 60px">
        <p>{{ players[8].surname }}</p>
        <div class="player-position">{{ players[8].position }}</div>
        <div class="player-position">{{ players[8].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(9)">
      <div v-if="players[9]">
        <p>{{ players[9].name }}</p>
        <img :src="players[9].img" style="height: 60px">
        <p>{{ players[9].surname }}</p>
        <div class="player-position">{{ players[9].position }}</div>
        <div class="player-position">{{ players[9].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="uk-margin">
      <select class="uk-select uk-display-inline-block" aria-label="Select" @change="selectFive"
              @click="setFiveIndex(2)">
        <option value="0">Wybierz piątkę</option>
        <option v-for="five in fives" :value="five.id" v-bind:key="five.id">{{ five.name }}</option>
      </select>
    </div>
  </div>

  <h2>Trzecia piątka</h2>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(10)">
      <div v-if="players[10]">
        <p>{{ players[10].name }}</p>
        <img :src="players[10].img" style="height: 60px">
        <p>{{ players[10].surname }}</p>
        <div class="player-position">{{ players[10].position }}</div>
        <div class="player-position">{{ players[10].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(11)">
      <div v-if="players[11]">
        <p>{{ players[11].name }}</p>
        <img :src="players[11].img" style="height: 60px">
        <p>{{ players[11].surname }}</p>
        <div class="player-position">{{ players[11].position }}</div>
        <div class="player-position">{{ players[11].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(12)">
      <div v-if="players[12]">
        <p>{{ players[12].name }}</p>
        <img :src="players[12].img" style="height: 60px">
        <p>{{ players[12].surname }}</p>
        <div class="player-position">{{ players[12].position }}</div>
        <div class="player-position">{{ players[12].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(13)">
      <div v-if="players[13]">
        <p>{{ players[13].name }}</p>
        <img :src="players[13].img" style="height: 60px">
        <p>{{ players[13].surname }}</p>
        <div class="player-position">{{ players[13].position }}</div>
        <div class="player-position">{{ players[13].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(14)">
      <div v-if="players[14]">
        <p>{{ players[14].name }}</p>
        <img :src="players[14].img" style="height: 60px">
        <p>{{ players[14].surname }}</p>
        <div class="player-position">{{ players[14].position }}</div>
        <div class="player-position">{{ players[14].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="uk-margin">
      <select class="uk-select uk-display-inline-block" aria-label="Select" @change="selectFive"
              @click="setFiveIndex(3)">
        <option value="0">Wybierz piątkę</option>
        <option v-for="five in fives" :value="five.id" v-bind:key="five.id">{{ five.name }}</option>
      </select>
    </div>
  </div>

  <h2>Czwarta piątka</h2>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(15)">
      <div v-if="players[15]">
        <p>{{ players[15].name }}</p>
        <img :src="players[15].img" style="height: 60px">
        <p>{{ players[15].surname }}</p>
        <div class="player-position">{{ players[15].position }}</div>
        <div class="player-position">{{ players[15].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(16)">
      <div v-if="players[16]">
        <p>{{ players[16].name }}</p>
        <img :src="players[16].img" style="height: 60px">
        <p>{{ players[16].surname }}</p>
        <div class="player-position">{{ players[16].position }}</div>
        <div class="player-position">{{ players[16].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(17)">
      <div v-if="players[17]">
        <p>{{ players[17].name }}</p>
        <img :src="players[17].img" style="height: 60px">
        <p>{{ players[17].surname }}</p>
        <div class="player-position">{{ players[17].position }}</div>
        <div class="player-position">{{ players[17].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(18)">
      <div v-if="players[18]">
        <p>{{ players[18].name }}</p>
        <img :src="players[18].img" style="height: 60px">
        <p>{{ players[18].surname }}</p>
        <div class="player-position">{{ players[18].position }}</div>
        <div class="player-position">{{ players[18].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(19)">
      <div v-if="players[19]">
        <p>{{ players[19].name }}</p>
        <img :src="players[19].img" style="height: 60px">
        <p>{{ players[19].surname }}</p>
        <div class="player-position">{{ players[19].position }}</div>
        <div class="player-position">{{ players[19].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="uk-margin">
      <select class="uk-select uk-display-inline-block" aria-label="Select" @change="selectFive"
              @click="setFiveIndex(4)">
        <option value="0">Wybierz piątkę</option>
        <option v-for="five in fives" :value="five.id" v-bind:key="five.id">{{ five.name }}</option>
      </select>
    </div>
  </div>

  <h2>Bramkarze</h2>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(20)">
      <div v-if="players[20]">
        <p>{{ players[20].name }}</p>
        <img :src="players[20].img" style="height: 60px">
        <p>{{ players[20].surname }}</p>
        <div class="player-position">{{ players[20].position }}</div>
        <div class="player-position">{{ players[20].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(21)">
      <div v-if="players[21]">
        <p>{{ players[21].name }}</p>
        <img :src="players[21].img" style="height: 60px">
        <p>{{ players[21].surname }}</p>
        <div class="player-position">{{ players[21].position }}</div>
        <div class="player-position">{{ players[21].playerNumber }}</div>
      </div>
      <div v-else>
        <span uk-icon="icon:  plus; ratio: 2"/>
      </div>
    </div>
  </div>

  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class-heading]; cls: uk-animation-slide-left-medium; delay: false;">
    <div class="uk-container">
      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-nadchodzacy-mecz-sklad-naglowek" class="uk-h1" uk-scrollspy-class-heading>Skład
            <select class="uk-select" id="select-mecz" v-model="match">
              <option v-for="match in matches" :key="match.id" :value="match.id+'-'+match.type">vs
                {{ match.enemyTeamName }} - {{ match.startTime}}
              </option>
            </select>
          </div>
        </div>
      </div>
    </div>
  </div>

  <button @click="saveSquad()" id="przycisk-zapisz-sklad" class="uk-button uk-button-default uk-width-1-1 uk-margin">
    Zapisz skład
  </button>


  <div id="sklad" uk-modal>
    <div class="uk-modal-dialog uk-modal-body">
      <h2 class="uk-modal-title">Wybierz zawodnika</h2>
      <button class="uk-modal-close-default" type="button" uk-close></button>
      <SquadMultiSelect
          ref="squad-multi-select"
          id="sklad-multi-select"
          :team-id="this.User.userInfo.teamId"
          :provide-players="false"
          :key="keyValue"
      />
      <PlayerPositionView ref="position-multi-select" id="pozycja-multi-select"/>
      <label for="player-number" class="uk-label" style="margin-top: 10px;">Numer zawodnika</label>
      <PlayerNumberView name="player-number" v-model:player-number.number="playerNumber" style="margin-top: 10px;"/>
      <button  class="uk-button uk-button-default uk-width-1-1 uk-modal-close" @click="savePlayer()">Zapisz</button>
    </div>
  </div>



</template>

<script>
import UIkit from "uikit";
import SquadMultiSelect from "@/views/Squad/SquadMultiSelect";
import PlayerPositionView from "@/views/Inputs/Position";
import {mapActions, mapGetters} from "vuex";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import PlayerNumberView from "@/views/Inputs/PlayerNumber";

export default {
  name: "UpcomingMatchSquadView",
  components: {PlayerNumberView, PlayerPositionView, SquadMultiSelect},
  data() {
    return {
      playersData: [],
      playerPosTemp: '',
      keyValue: 0,
      fives: {},
      playerNumber: 0,
      matches: [],
      match: null,
    }
  },
  created() {

    axios.get("http://localhost:8080/api/teams/" + this.User.userInfo.teamId + "/fives").then((response) => {
      this.fives = response.data;
      this.fives.forEach((five) => {
        five.players.forEach((p) => {
          p.img = blobToDataURL(p.image)
        })
      })
      // Pobierz listę meczy i przypisz przeciwną drużynę
      axios.get("http://localhost:8080/api/matches/team/" + this.User.userInfo.teamId).then((response) => {
        let matches = response.data;
        let matchesCreated = [];
        matches.forEach((m) => {
          if (m.homeTeam.id === this.User.userInfo.teamId) {
            m.enemyTeamName = m.visitingTeam.name
            m.enemyTeamId = m.visitingTeam.id;
            m.type = 'home';
          } else if (m.visitingTeam.id === this.User.userInfo.teamId) {
            m.enemyTeamName = m.homeTeam.name
            m.enemyTeamId = m.homeTeam.id;
            m.type = 'visiting';
          }
          if(m.status === 'CREATED') {
            matchesCreated.push(m);
          }

        })
        // Tylko mecze ze statusem 'CREATED'
        this.matches = matchesCreated;

      })


    })
  },
  computed: {
    ...mapGetters({
      playerIndex: "StatePlayerIndex",
      playerTemp: "StatePlayerTemp",
      players: "StatePlayers",
      fiveIndex: "StateFiveIndex",
      User: "StateUser"
    }),
  },
  methods: {
    ...mapActions(['SetPlayerIndex', 'SetPlayerTemp', "SetPlayer", "SetPlayers", "SetFiveIndex"]),
    saveSquad() {
      if (this.match !== null) {
        let arrayMatch = this.match.split("-")
        let id = arrayMatch[0] //Match id
        let type = arrayMatch[1] //Visiting or home
        if (type === 'visiting') {
          let playersArray = [];
          this.players.forEach((p)=>{
            let playerObj = {};
            playerObj.number = p.playerNumber;
            playerObj.position = p.position;
            playerObj.playerId = p.id
            playersArray.push(playerObj)
          })
          const json = JSON.stringify(playersArray);


          axios.post('http://localhost:8080/api/matches/'+id+'/'+this.User.userInfo.teamId+'/visiting_team_players',
          json, {
            headers: {
              'Authorization': 'Bearer '+this.User.userInfo.token,
              'Content-Type': `application/json`,
            }
              }
          )
              .then(response =>{
                if(response.status === 200){
                  UIkit.notification({
                    message: "Pomyślnie dodano skład",
                    status: "success",
                    pos: "top-center",
                    timeout: 5000
                  })
                }
              })
        } else if (type === 'home') {
          let playersArray = [];
          this.players.forEach((p)=>{
            let playerObj = {};
            playerObj.number = p.playerNumber;
            playerObj.position = p.position;
            playerObj.playerId = p.id
            playersArray.push(playerObj)
          })
          const json = JSON.stringify(playersArray);


          axios.post('http://localhost:8080/api/matches/'+id+'/'+this.User.userInfo.teamId+'/home_team_players',
              json, {
                headers: {
                  'Authorization': 'Bearer '+this.User.userInfo.token,
                  'Content-Type': `application/json`,
                }
              }
          )
              .then(response =>{
                if(response.status === 200){
                  UIkit.notification({
                    message: "Pomyślnie dodano skład.",
                    status: "success",
                    pos: "top-center",
                    timeout: 5000
                  })
                }
              })
        }
      }
      setTimeout(()=>{
        this.SetPlayers([{}])
        this.$emit('incrementKey')
      },1500)

    },
    setFiveIndex(index) {
      this.SetFiveIndex(index)
    },
    refreshSquad() {
      this.SetPlayers([{}]);
      this.$emit('incrementKey')
    },
    async selectFive(event) {

      let playersSelected = [];
      this.fives.forEach((five) => {
        if (five.id == event.target.value) {
          playersSelected = five.players;
        }
      })
      let playersRemaining = [];
      let canAddThisFive = true;
      playersSelected.forEach((p) => {
        p.playerNumber = p.number;
        this.$refs["squad-multi-select"].$data.options.forEach((option) => {
          if (option.id === p.id) {
            playersRemaining.push(option)
          }
        })
        this.players.forEach((p2) => {
          if (p2 !== undefined)
            if (p2.id === p.id) {
              p.canAdd = false;
              canAddThisFive = false;
            }
        })
      })
      let playersToSelect = this.$refs['squad-multi-select'].$data.options.filter(e => {
        return !playersRemaining.some(item => item.id === e.id);
      });

      this.$refs["squad-multi-select"].setOptions(playersToSelect)

      if (canAddThisFive) {
        switch (this.fiveIndex) {
          case 1:
            // Przypisanie zawodników od indeksu 0 do 4
            await this.SetPlayerIndex(0)
            await this.SetPlayerTemp(playersSelected[0])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(1)
            await this.SetPlayerTemp(playersSelected[1])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(2)
            await this.SetPlayerTemp(playersSelected[2])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(3)
            await this.SetPlayerTemp(playersSelected[3])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(4)
            await this.SetPlayerTemp(playersSelected[4])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            break;
          case 2:
            // Przypisanie zawodników od indeksu 5 do 9
            await this.SetPlayerIndex(5)
            await this.SetPlayerTemp(playersSelected[0])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(6)
            await this.SetPlayerTemp(playersSelected[1])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(7)
            await this.SetPlayerTemp(playersSelected[2])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(8)
            await this.SetPlayerTemp(playersSelected[3])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(9)
            await this.SetPlayerTemp(playersSelected[4])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            break;
          case 3:
            // Przypisanie zawodników od indeksu 10 do 14
            await this.SetPlayerIndex(10)
            await this.SetPlayerTemp(playersSelected[0])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(11)
            await this.SetPlayerTemp(playersSelected[1])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(12)
            await this.SetPlayerTemp(playersSelected[2])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(13)
            await this.SetPlayerTemp(playersSelected[3])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(14)
            await this.SetPlayerTemp(playersSelected[4])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            break;
          case 4:
            // Przypisanie zawodników od indeksu 15 do 19 - ostatnia piątka
            await this.SetPlayerIndex(15)
            await this.SetPlayerTemp(playersSelected[0])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(16)
            await this.SetPlayerTemp(playersSelected[1])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(17)
            await this.SetPlayerTemp(playersSelected[2])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(18)
            await this.SetPlayerTemp(playersSelected[3])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            await this.SetPlayerIndex(19)
            await this.SetPlayerTemp(playersSelected[4])
            await this.SetPlayer({
              playerTemp: this.playerTemp,
              index: this.playerIndex,
              playerNumber: this.playerNumber
            })
            break;
        }
      } else {
        UIkit.notification({
          message: "Nie można dodać piątki, ponieważ zawodnik istnieje już w składzie",
          status: "warning",
          pos: "top-center",
          timeout: 5000
        })
      }

    },
    async changeIndex(index) {
      // this.keyValue++;
      await this.SetPlayerIndex(index)
    },
    togglePlayer() {
      const modal = UIkit.modal("#sklad")
      modal.toggle();
    },
     savePlayer() {
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
      if (this.$refs["squad-multi-select"].value == null) {
        canAddPlayer = false;
        UIkit.notification({
          message: 'Nie wybrano zawodnika!',
          status: 'warning',
          pos: 'top-center',
          timeout: 10000
        });
      }
      if(this.playerNumber === 0){
        canAddPlayer = false;
        UIkit.notification({
          message: 'Nie wybrano numeru zawodnika!',
          status: 'warning',
          pos: 'top-center',
          timeout: 10000
        });
      }
      if (canAddPlayer) {
        let player = this.$refs["squad-multi-select"].value;
        let position = this.$refs["position-multi-select"].value;
        let playerArray = [];
        player.position = position.position;
        playerArray.push(player)
        let playersToSelect = this.$refs['squad-multi-select'].$data.options.filter(e => {
          return !playerArray.some(item => item.id === e.id);
        });

        // Sprawdzanie czy zawodnik już istnieje
        this.players.forEach((p) => {
          if (p !== undefined)
            if (p.id === player.id) {
              canAddPlayer = false;
              UIkit.notification({
                message: "Ten zawodnik już istnieje w składzie",
                status: "warning",
                pos: "top-center"
              })
            }
        })
        // Przypisanie zawodnika
        if (canAddPlayer) {
          player.playerNumber = this.playerNumber;
          this.SetPlayerTemp(player)
          this.SetPlayer({playerTemp: this.playerTemp, index: this.playerIndex,playerNumber: this.playerNumber})
        }



        this.$refs["squad-multi-select"].setOptions(playersToSelect);
        setTimeout(()=> {

          this.$refs["squad-multi-select"].$forceUpdate();
          this.$refs["position-multi-select"].value = null;
          this.$emit('incrementKey')
        },500)

      }

    }
  },
}
</script>

<style scoped lang="less">
.dodawanie-skladu {
  background-color: #a6efff;
  padding: 10px;
  display: inline-block;
  margin-left: 10px;
  margin-top: 10px;

  &:hover {
    color: #a6efff;
    background-color: #0e6dcd;
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

.player-position {
  color: #0e6dcd;
  font-size: x-large;
  font-weight: bold;

  &:hover {
    color: #a6efff;
  }
}

#tlh-nadchodzacy-mecz-sklad-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
  border-top-width: 10px;
  border-color: #006394;

}

#select-mecz {
  height: 60px;
  width: 80%;
  color: #006394;
  background-color: #a6efff;
  border-color: #006394;
  border-top-width: 0px;
  border-bottom-width: 10px;
  border-left-width: 0;
  border-right-width: 0;

  margin-bottom: 5px;
}
</style>