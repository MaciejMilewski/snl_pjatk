<template>
  <div class="uk-align-center uk-text-center">
    <img :src="teamImage"/>
    <h2>{{ teamName }}</h2>

    <div class="uk-flex-inline">
      <button @click="toggleGoal()" id="przycisk-gol-goscie" class="uk-button uk-button-default uk-margin-left ">Gol
      </button>
      <button @click="togglePenalty()" id="przycisk-kara-goscie" class="uk-button uk-button-default  uk-margin-left ">
        Kara
      </button>
    </div>

    <div class="uk-flex">
      <button @click="toggleChange()" class="przycisk-dodatkowy uk-button uk-button-default">Zmiana bramkarza</button>
      <button @click="toggleTeamTime()" class="przycisk-dodatkowy uk-button uk-button-default">Czas dla drużyny</button>
      <button @click="togglePenaltyShot()" class="przycisk-dodatkowy uk-button uk-button-default">Rzut karny
        nietrafiony
      </button>
    </div>

    <!--    Gol  -->
    <div id="gol-goscie-modal" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title uk-text-center">{{ teamName }}</h2>
        <button class="uk-modal-close-default" type="button" uk-close></button>

        <!--   Minuty i sekundy     -->
        <label for="minuty-i-sekundy-goscie" class="uk-label">Czas (minuty:sekundy)</label>
        <div class="uk-grid" id="minuty-i-sekundy-goscie">
          <div class="uk-width-1-3" id="minuty-goscie">
            <input
                type="number"
                min="0"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="minutesGoal"
            />

          </div>
          <div>
            <h2>:</h2>
          </div>
          <div class="uk-width-1-3" id="sekudny-goscie">
            <input
                type="number"
                min="0"
                max="59"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="secondsGoal"

            />

          </div>
        </div>

        <!--   Sytuacja     -->
        <label for="sytuacja-goscie" class="uk-label" style="margin-top: 10px">Sytuacja</label>
        <div class="uk-grid" id="sytuacja-goscie">
          <div class="uk-width-1-1">
            <select class="uk-select" v-model="situation">
              <option value="PP2">PP2</option>
              <option value="PP1">PP1</option>
              <option value="EQ">EQ</option>
              <option value="SH1">SH1</option>
              <option value="SH2">SH2</option>
              <option value="EN">EN</option>
              <option value="PS">PS</option>
              <option value="PSSS">PSS - decydujący karny</option>
            </select>
          </div>
        </div>

        <!--Strzelec-->
        <label for="strzelec-goscie" class="uk-label" style="margin-top: 10px">Strzelec</label>
        <div class="uk-grid" id="strzelec-goscie">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="strzelec-goscie"
                id="strzelec-goscie"
                :team-id="teamId"
                v-model="shooter"
                :provide-players="true"
                :players="players"
            />
          </div>
        </div>

        <!--Asystent 1-->
        <label for="asystent-1-goscie" class="uk-label" style="margin-top: 10px">Asystent 1</label>
        <div class="uk-grid" id="asystent-1-goscie">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="asystent-1-goscie"
                id="asystent-1-goscie"
                :team-id="teamId"
                v-model="assistantFirst"
                :provide-players="true"
                :players="players"
            />
          </div>
        </div>

        <!--Asystent 2-->
        <label for="asystent-2-goscie" class="uk-label" style="margin-top: 10px">Asystent 2</label>
        <div class="uk-grid" id="asystent-2-goscie">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="asystent-2-goscie"
                id="asystent-2-goscie"
                :team-id="teamId"
                v-model="assistantSecond"
                :provide-players="true"
                :players="players"
            />
          </div>
        </div>

        <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-modal-close" @click="saveGoal()">Zapisz
        </button>
      </div>
    </div>

    <!--  Kara -->
    <div id="kara-goscie-modal" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title uk-text-center"> {{ teamName }}</h2>
        <button class="uk-modal-close-default" type="button" uk-close></button>

        <!--   Minuty i sekundy     -->
        <label for="minuty-i-sekundy-goscie-kara" class="uk-label">Czas (minuty:sekundy)</label>
        <div class="uk-grid" id="minuty-i-sekundy-goscie-kara">
          <div class="uk-width-1-3" id="minuty-goscie-kara">
            <input
                type="number"
                min="0"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="minutesPenalty"
            />

          </div>
          <div>
            <h2>:</h2>
          </div>
          <div class="uk-width-1-3" id="sekudny-goscie-kara">
            <input
                type="number"
                min="0"
                max="59"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="secondsPenalty"
            />

          </div>
        </div>

        <!--Ukarany-->
        <label for="ukarany-goscie-grid" class="uk-label" style="margin-top: 10px;">Ukarany</label>
        <div class="uk-grid" id="ukarany-goscie-grid">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="ukarany-goscie"
                id="ukarany-goscie"
                :team-id="teamId"
                v-model="penaltyPlayer"
                :provide-players="true"
                :players="players"
            />
          </div>
        </div>

        <!--   Długość kary     -->
        <label for="dlugosc-kary-goscie" class="uk-label" style="margin-top: 10px">Długość</label>
        <div class="uk-grid" id="dlugosc-kary-goscie">
          <div class="uk-width-1-1">
            <select class="uk-select" v-model="penaltyDuration">
              <option value="TWO">2 minuty</option>
              <option value="FOUR">4 minuty</option>
              <option value="TEN">10 minut</option>
              <option value="TWENTY">20 minut</option>
              <option value="TWENTY_FIVE">25 minut</option>
              <option value="PENALTY_SHOT">Rzut karny</option>
            </select>
          </div>
        </div>

        <!--   Penalty shot     -->
        <div v-if="penaltyDuration === 'PENALTY_SHOT'">
          <label for="rzut-karny-kara-gospodarze" class="uk-label" style="margin-top: 10px">Strzelec</label>
          <div class="uk-grid" id="rzut-karny-kara-gospodarze">
            <div class="uk-width-1-1">
              <SquadMultiSelect
                  ref="penalty-shot-player"
                  id="penalty-shot-player"
                  :team-id="teamId"
                  v-model="penaltyShotPlayer"
                  :provide-players="true"
                  :players="players"
              />
            </div>
          </div>

        </div>

        <!--   Rodzaj kary     -->
        <label for="rodzaj-kary-goscie" class="uk-label" style="margin-top: 10px">Rodzaj</label>
        <div class="uk-grid" id="rodzaj-kary-goscie">
          <div class="uk-width-1-1">
            <select class="uk-select" v-model="penaltyType">
              <option value="ABUSE">ABUSE - Obraza sędziego</option>
              <option value="BENCH">BENCH - Kara mniejsza techniczna</option>
              <option value="BITE">BITE - Gryzienie</option>
              <option value="BOARD">BOARD - Rzucenie na bande</option>
              <option value="BRST">BRST - Gra złamanym kijem</option>
              <option value="BUTE">BUTE - Uderzenie trzonkiem kija</option>
              <option value="CHARG">CHARG - Natarcie</option>
              <option value="CHEB">CHE-B - Atak z tyłu</option>
              <option value="CHEH">CHE-H - Atak w okolicę głowy lub szyji</option>
              <option value="CLIPP">CLIPP - Podcięcie</option>
              <option value="CLOS">CLOS - Zamknięcie krążka w rękawicy</option>
              <option value="CROSS">CROSS - Atak kijem trzymanym oburącz</option>
              <option value="DANG">DANG - Niebezpieczne wyposażenie</option>
              <option value="DELAY">DELAY - Opóźnianie gry</option>
              <option value="ELBOW">ELBOW - Atak łokciem</option>
              <option value="EMBEL">EMBEL - Wymuszanie kary</option>
              <option value="ENGS">ENG-S - Zwada z widzami</option>
              <option value="FIGHT">FIGHT - Bójka</option>
              <option value="GAMI">GA-MI - Kara meczu za niesportowe zachowanie</option>
              <option value="GKINT">GK-INT - Przeszkadzanie bramkarzowi</option>
              <option value="GKPEN">GK-PEN - Kara dla bramkarza</option>
              <option value="HBUT">H-BUT - Uderzenie głową</option>
              <option value="HELM">HELM - Gra bez kasku</option>
              <option value="HIST">HI-ST - Wysoko niesiony kij</option>
              <option value="HOST">HO-ST - Trzymanie kija zawodnika</option>
              <option value="HOLD">HOLD - Trzymanie przeciwnika</option>
              <option value="HOOK">HOOK - Zahaczenie</option>
              <option value="ILLH">ILL-H - Atak ciałem u kobiet</option>
              <option value="ILLST">ILL-ST - Niedozwolony kij</option>
              <option value="INCAC">INCAC - Nieprawidłowe opuszczenie ławki kar</option>
              <option value="INJUR">INJUR - Odmowa opuszczenia lodowiska</option>
              <option value="INTRF">INTRF - Przeszkadzanie</option>
              <option value="KICK">KICK - Kopnięcie</option>
              <option value="KNEE">KNEE - Atak kolanem</option>
              <option value="LBCH">L-BCH - Przedwczesne opuszczenie ławki kar</option>
              <option value="MISC">MISC - Kara za niesportowe zachowanie 10 min</option>
              <option value="OTHER">OTHER - Inne</option>
              <option value="PULL">PULL - Ciągnięcie za włosy, kask, kratę</option>
              <option value="REFUSE">REFUSE - Odmowa rozpoczęcia gry</option>
              <option value="ROUGH">ROUGH - Ostrość</option>
              <option value="SLASH">SLASH - Uderzenie kijem</option>
              <option value="SLEW">SLEW - Podcięcie z tyłu</option>
              <option value="SPEAR">SPEAR - Kłucie końcem kija</option>
              <option value="SPIT">SPIT - Plucie</option>
              <option value="TBCH">T-BCH - Opuszczenie ławek przez oficjeli</option>
              <option value="TAUNT">TAUNT - Szydzenie</option>
              <option value="THRST">THR-ST - Rzucenie kija lub innych przedmiotów</option>
              <option value="TOOM">TOOM - Nadmierna ilość zawodników na lodzie</option>
              <option value="TRIP">TRIP - Spowodowanie upadku przeciwnika</option>
              <option value="UNSP">UN-SP - Kara mniejsza za niespodrotwe zachowanie</option>
            </select>
          </div>
        </div>

        <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-modal-close" @click="savePenalty()">
          Zapisz
        </button>
      </div>
    </div>


    <!--    Zmiana bramkarza -->
    <div id="zmiana-bramkarza-goscie-modal" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title uk-text-center"> {{ teamName }}</h2>
        <button class="uk-modal-close-default" type="button" uk-close></button>

        <!--   Minuty i sekundy     -->
        <label for="minuty-i-sekundy-goscie-zmiana-bramkarza" class="uk-label">Czas (minuty:sekundy)</label>
        <div class="uk-grid" id="minuty-i-sekundy-goscie-zmiana-bramkarza">
          <div class="uk-width-1-3" id="minuty-goscie-zmiana-bramkarza">
            <input
                type="number"
                min="0"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="minutesChange"
            />

          </div>
          <div>
            <h2>:</h2>
          </div>
          <div class="uk-width-1-3" id="sekudny-goscie-zmiana-bramkarza">
            <input
                type="number"
                min="0"
                max="59"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="secondsChange"
            />

          </div>
        </div>

        <!--Wchodzi-->
        <label for="wchodzi-goscie-grid" class="uk-label" style="margin-top: 10px; color: green !important;">
          <span uk-icon="icon: arrow-up"/>Wchodzi (IN)
        </label>
        <div class="uk-grid" id="wchodzi-goscie-grid">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="wchodzi-goscie"
                id="wchodzi-goscie"
                :team-id="teamId"
                v-model="inPlayer"
                :provide-players="true"
                :players="keepers"
            />
          </div>
        </div>

        <!--Schodzi-->
        <label for="schodzi-goscie-grid" class="uk-label" style="margin-top: 10px; color: darkred !important;">
          <span uk-icon="icon: arrow-down"/>Schodzi (OUT)
        </label>
        <div class="uk-grid" id="schodzi-goscie-grid">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="schodzi-goscie"
                id="schodzi-goscie"
                :team-id="teamId"
                v-model="outPlayer"
                :provide-players="true"
                :players="keepers"
            />
          </div>
        </div>
        <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-modal-close" @click="saveChange()">
          Zapisz
        </button>
      </div>
    </div>

    <!--    Czas dla drużyny -->
    <div id="czas-goscie-modal" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title uk-text-center"> {{ teamName }}</h2>
        <button class="uk-modal-close-default" type="button" uk-close></button>

        <!--   Minuty i sekundy     -->
        <label for="minuty-i-sekundy-goscie-czas" class="uk-label">Czas (minuty:sekundy)</label>
        <div class="uk-grid" id="minuty-i-sekundy-goscie-czas">
          <div class="uk-width-1-3" id="minuty-goscie-czas">
            <input
                type="number"
                min="0"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="minutesTimeout"
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
                v-model="secondsTimeout"
            />

          </div>
        </div>
        <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-modal-close" @click="saveTimeout()">
          Zapisz
        </button>
      </div>
    </div>


    <!--    Rzut karny nietrafiony -->
    <div id="rzut-karny-goscie-modal" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title uk-text-center"> {{ teamName }}</h2>
        <button class="uk-modal-close-default" type="button" uk-close></button>

        <!--   Minuty i sekundy     -->
        <label for="minuty-i-sekundy-goscie-rzut-karny" class="uk-label">Czas (minuty:sekundy)</label>
        <div class="uk-grid" id="minuty-i-sekundy-goscie-rzut-karny">
          <div class="uk-width-1-3" id="minuty-goscie-rzut-karny">
            <input
                type="number"
                min="0"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="minutesPenaltyShot"
            />

          </div>
          <div>
            <h2>:</h2>
          </div>
          <div class="uk-width-1-3" id="sekudny-goscie-rzut-karny">
            <input
                type="number"
                min="0"
                max="59"
                placeholder="00"
                class="uk-input"
                style="margin-top: 10px;"
                v-model="secondsPenaltyShot"
            />

          </div>
        </div>

        <!--Strzelec-->
        <label for="strzelec-goscie-rzut-karny" class="uk-label" style="margin-top: 10px">Strzelec</label>
        <div class="uk-grid" id="strzelec-goscie-rzut-karny">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="strzelec-goscie-rzut-karny"
                id="strzelec-goscie-rzut-karny"
                :team-id="teamId"
                v-model="shooterPenaltyShot"
                :provide-players="true"
                :players="players"
            />
          </div>
        </div>

        <!--Bramkarz-->
        <label for="bramkarz-goscie-rzut-karny" class="uk-label" style="margin-top: 10px">Bramkarz</label>
        <div class="uk-grid" id="bramkarz-goscie-rzut-karny">
          <div class="uk-width-1-1">
            <SquadMultiSelect
                ref="bramkarz-goscie-rzut-karny"
                id="bramkarz-goscie-rzut-karny"
                :team-id="enemyTeamId"
                v-model="keeperPenaltyShot"
                :provide-players="true"
                :players="keepersEnemy"
            />
          </div>
        </div>

        <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-modal-close"
                @click="savePenaltyShot()">Zapisz
        </button>
      </div>
    </div>

  </div>
</template>

<script>
import UIkit from "uikit";
import SquadMultiSelect from "@/views/Squad/SquadMultiSelect";
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "MatchActionVisiting",
  components: {SquadMultiSelect},
  computed: {...mapGetters({User: "StateUser"})},
  emits: ['incrementKey'],
  props: ['teamName', 'teamImage', 'teamId', 'matchId', 'enemyTeamId','players','keepers','keepersEnemy'],
  data() {
    return {
      secondsTimeout: null,
      minutesTimeout: null,
      inPlayer: null,
      outPlayer: null,
      minutesGoal: null,
      secondsGoal: null,
      minutesChange: null,
      secondsChange: null,
      situation: null,
      shooter: null,
      shooterPenaltyShot: null,
      assistantFirst: null,
      assistantSecond: null,
      penaltyType: null,
      penaltyDuration: null,
      penaltyPlayer: null,
      minutesPenalty: null,
      secondsPenalty: null,
      minutesPenaltyShot: null,
      secondsPenaltyShot: null,
      keeperPenaltyShot: null,
      penaltyShotPlayer: null,
      penaltyShotMissed: null,

    }
  },
  methods: {
    saveGoal() {
      console.log(this.minutesGoal)
      console.log(this.secondsGoal)
      console.log(this.situation)
      console.log(this.shooter)
      console.log(this.assistantFirst)
      console.log(this.assistantSecond)
      // Obsłużenie gola bez asystentów
      if(this.assistantFirst === null){
        this.assistantFirst = {}
        this.assistantFirst.id = null;
      }
      if(this.assistantSecond === null ){
        this.assistantSecond = {}
        this.assistantSecond.id = null;
      }

      const json = JSON.stringify({
        gameMinute: this.minutesGoal,
        gameSecond: this.secondsGoal,
        situation: this.situation,
        scorer: {
          id: this.shooter.id,
        },
        assistant1: {
          id: this.assistantFirst.id,
        },
        assistant2: {
          id: this.assistantSecond.id,
        }
      })
      axios.post("http://localhost:8080/api/matches/" + this.matchId + "/" + this.teamId + "/goals", json, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `application/json`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie dodano gol',
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              });
            }
          })
      this.$emit('incrementKey', 'akcje-meczowe')
    },
    savePenalty() {
      // console.log(this.minutesPenalty)
      // console.log(this.penaltyType)
      // console.log(this.secondsPenalty)
      // console.log(this.penaltyDuration)
      // console.log(this.penaltyPlayer)
      const json = JSON.stringify({
        gameMinute: this.minutesPenalty,
        gameSecond: this.secondsPenalty,
        timeLength: this.penaltyDuration,
        typePenalty: this.penaltyType,
        penalized: {
          id: this.penaltyPlayer.id,
        },
      })
      console.log(json);
      axios.post("http://localhost:8080/api/matches/" + this.matchId + "/" + this.teamId + "/penalties", json, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `application/json`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie dodano karę',
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              });
            }
          })
      if (this.penaltyDuration === 'PENALTY_SHOT') {

        const json = JSON.stringify({
          gameMinute: this.minutesPenalty,
          gameSecond: this.secondsPenalty,
          situation: 'PS',
          scorer: {
            id: this.penaltyShotPlayer.id,
          },
          assistant1: {
            id: null,
          },
          assistant2: {
            id: null,
          }
        })
        axios.post("http://localhost:8080/api/matches/" + this.matchId + "/" + this.teamId + "/goals", json, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `application/json`,
          }
        })
            .then(response => {
              if (response.status === 200) {
                UIkit.notification({
                  message: 'Pomyślnie dodano karnego',
                  status: 'success',
                  pos: 'top-center',
                  timeout: 10000
                });
              }
            })
      }
      this.$emit('incrementKey', 'akcje-meczowe')
    },
    saveChange() {
      console.log(this.minutesChange)
      console.log(this.secondsChange)
      console.log(this.inPlayer)
      console.log(this.outPlayer)

      const json = JSON.stringify({
        gameMinute: this.minutesChange,
        gameSecond: this.secondsChange,
        golkiperIn: {
          id: this.inPlayer.id,
        },
        golkiperOut: {
          id: this.outPlayer.id,
        },
      })
      axios.post("http://localhost:8080/api/matches/" + this.matchId + "/" + this.teamId + "/changeGolkipers", json, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `application/json`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie dodano zmianę bramkarza',
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              });
            }
          })

      this.$emit('incrementKey', 'akcje-meczowe')
    },
    savePenaltyShot() {
      console.log(this.keeperPenaltyShot)
      console.log(this.shooterPenaltyShot)
      console.log(this.secondsPenaltyShot)
      console.log(this.minutesPenaltyShot)

      const json = JSON.stringify({
        gameMinute: this.minutesPenaltyShot,
        gameSecond: this.secondsPenaltyShot,
        shooter: {
          id: this.shooterPenaltyShot.id,
        },
        goalkiper: {
          id: this.keeperPenaltyShot.id,
        },
      })
      axios.post("http://localhost:8080/api/matches/" + this.matchId + "/" + this.teamId + "/penaltyShotMissed", json, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `application/json`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie zapisano rzut karny nietrafiony',
                status: 'success',
                pos: 'top-center',
                timeout: 10000
              });
            }
          })
      this.$emit('incrementKey', 'akcje-meczowe')
    },
    saveTimeout() {
      const json = JSON.stringify({
        gameMinute: this.minutesTimeout,
        gameSecond: this.secondsTimeout,
      })
      axios.post('http://localhost:8080/api/matches/'+this.matchId+'/'+this.teamId+'/breakes',json,{
        headers: {
          'Content-Type': `application/json`,
        }
      }).then(response => {
        console.log(response);
      })
      this.$emit('incrementKey', 'akcje-meczowe')
    },

    toggleTeamTime() {
      const modal = UIkit.modal("#czas-goscie-modal")
      modal.toggle();
    },
    toggleChange() {
      const modal = UIkit.modal("#zmiana-bramkarza-goscie-modal")
      modal.toggle();
    },
    toggleGoal() {
      const modal = UIkit.modal("#gol-goscie-modal")
      modal.toggle();
    },
    togglePenalty() {
      const modal = UIkit.modal("#kara-goscie-modal")
      modal.toggle();
    },
    togglePenaltyShot() {
      const modal = UIkit.modal("#rzut-karny-goscie-modal")
      modal.toggle();
    }
  }
}
</script>

<style scoped lang="less">
.przycisk-dodatkowy {
  padding-top: 10px;
  margin-top: 20px;
  background-color: #e1feff !important;
  margin-left: 10px;
  border: 1px solid #8debff !important;

  &:hover {
    background-color: white !important;
  }
}

#przycisk-gol-goscie {
  background-color: #e1feff !important;
  padding: 50px;
  border: 1px solid #8debff !important;

  &:hover {
    background-color: white !important;
  }
}

#przycisk-kara-goscie {
  background-color: #e1feff !important;
  padding: 50px;
  border: 1px solid red !important;
  color: red !important;

  &:hover {
    background-color: white !important;
  }
}
</style>