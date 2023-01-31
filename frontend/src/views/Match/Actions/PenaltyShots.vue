<template>
  <!--   Minuty i sekundy     -->
  <label for="minuty-i-sekundy-czas" class="uk-label">Czas (minuty:sekundy)</label>
  <div class="uk-grid" id="minuty-i-sekundy-czas">
    <div class="uk-width-1-3" id="minuty-goscie-czas">
      <input
          type="number"
          min="0"
          placeholder="00"
          class="uk-input"
          style="margin-top: 10px;"
          v-model="gameMinute"
      />

    </div>
    <div>
      <h2>:</h2>
    </div>
    <div class="uk-width-1-3" id="sekudny-goscie-czas">
      <input
          type="number"
          min="0"
          max="59"
          placeholder="00"
          class="uk-input"
          style="margin-top: 10px;"
          v-model="gameSecond"
      />

    </div>
  </div>
  <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">

    <div class="uk-width-1-1 uk-first-column">

      <div class="uk-width-1-1 uk-first-column">

        <div class="uk-margin">

          <div class="uk-child-width-1-1 uk-child-width-1-2@m uk-grid-match uk-grid uk-grid-stack"
               uk-grid="">

            <!--     Gospodarze       -->
            <div class="uk-first-column uk-card uk-card-default uk-card-body">
              <div class="tlh-item uk-panel uk-margin-remove-first-child">
                <div class="uk-margin-top">
                  <div class="uk-align-center uk-text-center">
                    <img :src="teamHomeImage"/>
                    <h2>{{ teamHomeName }}</h2>
                  </div>

                  <label for="strzelec-gospodarze" class="uk-label" style="margin-top: 10px">Strzelec</label>
                  <div class="uk-grid" id="strzelec-gospodarze">
                    <div class="uk-width-1-1">
                      <SquadMultiSelect
                          ref="strzelec-gospodarze"
                          id="strzelec-gospodarze-select"
                          v-model="shooterHome"
                          :provide-players="true"
                          :players="homePlayers"
                      />
                    </div>
                  </div>

                  <label for="bramkarz-goscie" class="uk-label" style="margin-top: 10px">Bramkarz</label>
                  <div class="uk-grid" id="bramkarz-goscie">
                    <div class="uk-width-1-1">
                      <SquadMultiSelect
                          ref="bramkarz-goscie"
                          id="bramkarz-goscie-select"
                          v-model="visitingKeeper"
                          :provide-players="true"
                          :players="visitingKeepers"
                      />
                    </div>
                  </div>

                  <div class="uk-margin  uk-child-width-auto uk-grid">
                    <label><input type="checkbox" class="uk-checkbox uk-margin" v-model="shootedHome"> Trafiony </label>
                  </div>
                  <button @click="submitPenaltyShotHome()" class="uk-button uk-button-default uk-width-1-1">Dodaj</button>
                </div>
              </div>
            </div>

            <!--     Goście       -->
            <div class="uk-first-column uk-card uk-card-default uk-card-body">
              <div class="tlh-item uk-panel uk-margin-remove-first-child">
                <div class="uk-margin-top">
                  <div class="uk-align-center uk-text-center">
                    <img :src="teamVisitingImage"/>
                    <h2>{{ teamVisitingName }}</h2>
                  </div>

                  <label for="strzelec-goscie" class="uk-label" style="margin-top: 10px">Strzelec</label>
                  <div class="uk-grid" id="strzelec-goscie">
                    <div class="uk-width-1-1">
                      <SquadMultiSelect
                          ref="strzelec-goscie"
                          id="strzelec-goscie-select"
                          v-model="shooterVisiting"
                          :provide-players="true"
                          :players="visitingPlayers"
                      />
                    </div>
                  </div>

                  <label for="bramkarz-gospodarze" class="uk-label" style="margin-top: 10px">Bramkarz</label>
                  <div class="uk-grid" id="bramkarz-gospodarze">
                    <div class="uk-width-1-1">
                      <SquadMultiSelect
                          ref="bramkarz-gospodarze"
                          id="bramkarz-gospodarze-select"
                          v-model="homeKeeper"
                          :provide-players="true"
                          :players="homeKeepers"
                      />
                    </div>
                  </div>
                </div>

                  <div class="uk-margin  uk-child-width-auto uk-grid">
                    <label><input type="checkbox" class="uk-checkbox uk-margin" v-model="shootedVisiting"> Trafiony </label>
                  </div>
                <button @click="submitPenaltyShotVisiting()" class="uk-button uk-button-default uk-width-1-1">Dodaj</button>

              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SquadMultiSelect from "@/views/Squad/SquadMultiSelect";
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";
export default {
  name: "PenaltyShots",
  components: {SquadMultiSelect},
  computed: {...mapGetters({User: "StateUser"})},
  data(){
    return{
      shooterHome: null,
      shooterVisiting: null,
      homeKeeper: null,
      visitingKeeper: null,
      shootedVisiting: false,
      shootedHome: false,
      gameMinute: null,
      gameSecond: null,
    }
  },
  props: [
    'teamHomeImage',
    'teamVisitingImage',
    'teamHomeName',
    'teamVisitingName',
    'homeKeepers',
    'visitingKeepers',
    'homePlayers',
    'visitingPlayers',
    'matchId',
    'homeTeamId',
    'visitingTeamId'
  ],
  methods : {
    submitPenaltyShotVisiting(){
      const json = JSON.stringify({
        gameMinute: this.gameMinute,
        gameSecond: this.gameSecond,
        schooter: {
          id: this.shooterVisiting.id
        },
        goalkiper: {
          id: this.homeKeeper.id
        },
        success: this.shootedVisiting
      })
      axios.post("http://localhost:8080/api/matches/"+this.matchId+"/"+this.visitingTeamId+'/penaltyShot', json, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `application/json`,
        }
      })
          .then(response => {
            if(response.status === 200){
              UIkit.notification({
                message: 'Pomyślnie dodano rzut karny',
                status: 'success',
                pos: 'top-center',
                timeout: 2000
              });
            }
          })
      this.$emit('incrementKey','rzuty-karne')

    },
    submitPenaltyShotHome(){
      const json = JSON.stringify({
        gameMinute: this.gameMinute,
        gameSecond: this.gameSecond,
        schooter: {
          id: this.shooterHome.id
        },
        goalkiper: {
          id: this.visitingKeeper.id
        },
        success: this.shootedHome
      })
      axios.post("http://localhost:8080/api/matches/"+this.matchId+"/"+this.homeTeamId+'/penaltyShot', json, {
        headers: {
          'Content-Type': `application/json`,
        }
      })
          .then(response => {
            if(response.status === 200){
              UIkit.notification({
                message: 'Pomyślnie dodano rzut karny',
                status: 'success',
                pos: 'top-center',
                timeout: 2000
              });
            }
          })
      this.$emit('incrementKey','rzuty-karne')
    },
  }
}
</script>

<style scoped>

</style>