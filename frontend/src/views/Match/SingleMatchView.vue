<template>
  <div v-if="this.match && this.hasScores " :key="keyValue">
    <div id="navbar-tlo"></div>
    <div id="mecz-naglowek">
      <div class="uk-flex uk-flex-center">
        <div class="tlh-obrazek-sredni">
          <img class="tlh-obrazek" alt="" :src="match.homeImage" loading="lazy">
        </div>
        <div>
          <h1>{{ this.match.homeTeam.name }} vs {{ match.visitingTeam.name }} </h1>
          <h2 class="uk-text-center" style="font-weight: bold">
            <div v-if="finalScore !== null">
              {{ finalScore }}
            </div>
          </h2>
          <div class="uk-text-center">
            <div class="match-sub-score" v-if="p1Score !== null"> Pierwsza tercja {{ p1Score }}</div>
            <div class="match-sub-score" v-if="p2Score !== null"> Druga tercja {{ p2Score }}</div>
            <div class="match-sub-score" v-if="p3Score !== null"> Trzecia tercja {{ p3Score }}</div>
            <div class="match-sub-score" v-if="p4Score !== null"> Karne {{ p4Score }}</div>
          </div>
        </div>
        <div class="tlh-obrazek-sredni">
          <img class="tlh-obrazek" alt="" :src="match.visitingImage" loading="lazy">
        </div>
      </div>
    </div>
    <div class="uk-section-default uk-section"
         uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-right-medium; delay: none;"
         :key="keyValue"
    >
      <div class="uk-container">
        <router-link v-if="User !== null" to="/panel">
          <a @click="changeContext" id="przycisk-powrot" class="uk-button-default"><span
              uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
        </router-link>
        <!--  Switcher      -->
        <div id="menu-glowne-srodek"
             uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">
          <div id="menu-mecz" uk-scrollspy-menu>
            <ul id="switcher-tlh-administrator" class="uk-flex-center"
                uk-tab="connect: #component-tab-left-match; animation: uk-animation-slide-right">
              <li>
                <a href="#">Informacje </a>
              </li>

              <li v-if="User !== null && (User.group === 'sekretarz')">
                <a href="#">Składy</a>
              </li>
              <li v-if="(User !== null && ( User.group === 'sekretarz')) || (User !== null && User.group === 'deweloper' && this.match.status === 'CONFLICT')">
                <a href="#">
                  Akcje meczowe
                </a>
              </li>
              <li v-if="(User !== null && ( User.group === 'sekretarz')) || (User !== null && User.group === 'deweloper' && this.match.status === 'CONFLICT')">
                <a href="#">
                  Rzuty karne
                </a>
              </li>
              <li v-if="User !== null && (User.group === 'deweloper' || User.group === 'sekretarz' || User.group === 'sedzia_medyk')">
                <a href="#">
                  Protokół
                </a>
              </li>
              <li v-if="User !== null && ( User.group === 'sekretarz' || User.group === 'deweloper')">
                <a href="#">
                  Zakończ mecz
                </a>
              </li>
              <li v-if="User !== null && (User.group === 'sedzia_medyk')">
                <a href="#">
                  Status meczu
                </a>
              </li>
              <li v-if="User === null">
                <a href="#">
                  Akcje meczowe
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class=" uk-container "
             uk-scrollspy="target: [uk-scrollspy-switch]; cls: uk-animation-slide-right; delay: 100;">
          <ul id="component-tab-left-match" class="uk-switcher ">
            <!--    Informacje      -->
            <li>
              <CreateNewMatch :match="this.match" :edit-context="true"/>
            </li>
            <!--    Składy      -->
            <li v-if="User !== null && (User.group === 'sekretarz')">
              <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
                <div class="uk-width-1-1 uk-first-column">
                  <div class="uk-width-1-1 uk-first-column">
                    <div class="uk-margin">
                      <div class="uk-child-width-1-1 uk-child-width-1-2@m uk-grid-match uk-grid uk-grid-stack"
                           uk-grid="">

                        <div class="uk-first-column uk-card uk-card-default uk-card-body">
                          <div class="tlh-item uk-panel uk-margin-remove-first-child">
                            <div class="uk-margin-top">
                              <MatchSquadViewPanel
                                  :team-id="match.homeTeam.id"
                                  :team-type="'home'"
                                  :team-image="this.match.homeImage"
                                  :playersHomeProp="homePlayers"
                                  :match-id="this.id"
                                  ref="homeTeamSquad"
                              />
                            </div>
                          </div>
                        </div>

                        <div class="uk-first-column uk-card uk-card-default uk-card-body">
                          <div class="tlh-item uk-panel uk-margin-remove-first-child">
                            <div class="uk-margin-top">
                              <MatchSquadViewPanel
                                  :team-id="match.visitingTeam.id"
                                  :team-type="'visiting'"
                                  :team-image="this.match.visitingImage"
                                  :players-visiting-prop="visitingPlayers"
                                  :match-id="this.id"
                                  ref="visitingTeamSquad"
                              />
                            </div>
                          </div>
                        </div>

                        <button id="przycisk-zapisz-sklad" @click="saveSquads()"
                                class="uk-button uk-button-default uk-width-1-1 uk-margin uk-align-center ">Zapisz
                          składy
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </li>
            <!--    Akcje meczowe      -->
            <li v-if="(User !== null && ( User.group === 'sekretarz')) || (User !== null && User.group === 'deweloper' && this.match.status === 'CONFLICT')">
              <div v-if="this.matchStarted === false">
                <button
                    class="uk-button uk-button-default uk-width-1-1"
                    @click="startMatch()">
                  Rozpocznij mecz
                </button>
              </div>
              <div v-else>
                <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
                  <div class="uk-width-1-1 uk-first-column">
                    <div class="uk-width-1-1 uk-first-column">
                      <div class="uk-margin">
                        <div class="uk-child-width-1-1 uk-child-width-1-2@m uk-grid-match uk-grid uk-grid-stack"
                             uk-grid="">

                          <div class="uk-first-column uk-card uk-card-default uk-card-body">
                            <div class="tlh-item uk-panel uk-margin-remove-first-child">
                              <div class="uk-margin-top">
                                <MatchActionHome :team-image="this.match.homeImage"
                                                 :team-name="match.homeTeam.name"
                                                 :team-id="match.homeTeam.id"
                                                 :match-id="this.id"
                                                 :enemy-team-id="match.visitingTeam.id"
                                                 @incrementKey="incrementKey"
                                                 :key="keyValue"
                                                 :players="homePlayers"
                                                 :keepers="homeKeepers"
                                                 :keepers-enemy="visitingKeepers"
                                />
                              </div>
                            </div>
                          </div>

                          <div class="uk-first-column uk-card uk-card-default uk-card-body">
                            <div class="tlh-item uk-panel uk-margin-remove-first-child">
                              <div class="uk-margin-top">
                                <MatchActionVisiting :team-image="this.match.visitingImage"
                                                     :team-name="match.visitingTeam.name"
                                                     :team-id="match.visitingTeam.id"
                                                     :match-id="this.id"
                                                     :enemy-team-id="match.homeTeam.id"
                                                     @incrementKey="incrementKey"
                                                     :key="keyValue"
                                                     :players="visitingPlayers"
                                                     :keepers="visitingKeepers"
                                                     :keepers-enemy="homeKeepers"
                                />
                              </div>
                            </div>
                          </div>

                        </div>
                        <MatchActionsView
                            :key="keyValue"
                            :match-id="this.id"
                            @incrementKey="incrementKey"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>

            </li>
            <li v-if="User !== null && ( User.group === 'sekretarz') || (User !== null && User.group === 'deweloper' && this.match.status === 'CONFLICT')">
              <div v-if="this.matchStarted === true">
                <PenaltyShots
                    :team-home-image="this.match.homeImage"
                    :team-visiting-image="this.match.visitingImage"
                    :team-home-name="match.homeTeam.name"
                    :team-visiting-name="match.visitingTeam.name"
                    :home-keepers="this.homeKeepers"
                    :visiting-keepers="this.visitingKeepers"
                    :home-players="this.homePlayers"
                    :visiting-players="this.visitingPlayers"
                    :match-id="this.id"
                    :home-team-id="match.homeTeam.id"
                    :visiting-team-id="match.visitingTeam.id"
                    @incrementKey="incrementKey"
                />
              </div>
            </li>
            <!--   Eksport do pdf       -->
            <li v-if="User !== null && (User.group === 'deweloper' || User.group === 'sekretarz' || User.group === 'sedzia_medyk')">
              <ProtocolView
                  :match="this.match"
                  :homePlayers="this.homePlayers"
                  :visitingPlayers="this.visitingPlayers"
                  :score="scores"
              />

            </li>
            <!--    Zakończ mecz      -->
            <li v-if="User !== null && (User.group === 'sekretarz' || User.group === 'deweloper')">
              <CloseMatchView :match-id="this.match.id" :status="this.match.status"/>
            </li>
            <li v-if="User !== null && User.group === 'sedzia_medyk'">
              <MedicOrJudgeNotes :status="match.status" :match-id="match.id"/>
            </li>
            <li v-if="User === null">
              <MatchActionsView
                  :key="keyValue"
                  :match-id="this.id"
                  @incrementKey="incrementKey"
              />
            </li>
          </ul>
        </div>
        <div>

        </div>

        <!--  Koniec switchera    -->


        <div class="uk-grid-margin uk-grid" uk-grid="">
          <div class="uk-width-1-2@m uk-first-column">
            <div class="uk-margin uk-scrollspy-inview" uk-scrollspy-class style="">
            </div>
          </div>
          <div class="uk-width-1-2@m">
            <h1 uk-scrollspy-class="" class="uk-scrollspy-inview" style="">{{ }}</h1>

          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-else>
    <PageLoader/>
  </div>

</template>

<script>
import UIkit from "uikit";
import activeLi from "@/assets/scripts/setActiveLi";
import {useRoute} from "vue-router";
import {reactive, toRefs} from "vue";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import CreateNewMatch from "@/views/Match/CreateNewMatch";
import MatchSquadViewPanel from "@/views/UpcomingMatch/MatchSquadViewPanel";
import MatchActionHome from "@/views/Match/MatchActionHome";
import MatchActionVisiting from "@/views/Match/MatchActionVisiting";
import MatchActionsView from "@/views/Match/MatchActionsView";
import {mapGetters} from "vuex";
import ProtocolView from "@/views/Match/Protocol/ProtocolView";
import MedicOrJudgeNotes from "@/views/Match/MedicOrJudge/MedicOrJudgeNotes";
import CloseMatchView from "@/views/Match/CloseMatch/CloseMatchView";
import PenaltyShots from "@/views/Match/Actions/PenaltyShots";
import PageLoader from "@/components/Loader/PageLoader";

export default {
  name: "SingleMatchView",
  components: {
    PageLoader,
    PenaltyShots,
    CloseMatchView,
    MedicOrJudgeNotes,
    ProtocolView, MatchActionsView, MatchActionVisiting, MatchActionHome, MatchSquadViewPanel, CreateNewMatch
  },
  setup() {
    const router = useRoute()
    const state = reactive({
      id: router.params.id,
    })
    return {
      ...toRefs(state),
      router
    }
  },
  data() {
    return {
      scores: [],
      keyValue: 0,
      match: null,
      homePlayers: [{}],
      visitingPlayers: [{}],
      homeKeepers: [{}],
      visitingKeepers: [{}],
      finalScore: null,
      p1Score: null,
      p2Score: null,
      p3Score: null,
      p4Score: null,
      matchStarted: true,
      hasScores: false,
    }
  },
  mounted() {
    setTimeout(() => {
      //Status
      if (this.match.status === "CREATED" || this.match.status === "READY") {
        this.matchStarted = false;
      }


    }, 500)

  },
  created() {
    axios.get("http://localhost:8080/api/matches/" + this.id + "/score")
        .then(response => {
          this.finalScore = response.data.regular.finalScore;
          this.p1Score = response.data.regular.p1Score;
          this.p2Score = response.data.regular.p2Score;
          this.p3Score = response.data.regular.p3Score;
          this.p4Score = response.data.regular.p4Score;
          this.scores = response.data
          this.hasScores = true;
        })

    axios.get("http://localhost:8080/api/matches/" + this.id)
        .then((response) => {
          this.match = response.data
          this.match.homeImage = blobToDataURL(this.match.homeTeam.image)
          this.match.visitingImage = blobToDataURL(this.match.visitingTeam.image)

          // Czas rozpoczęcia i zakończenia
          let dateAndTime = this.match.startTime;
          let timeEnd = '';
          let dateAndTimeEnd = ''
          if (this.match.endTime !== null) {
            dateAndTimeEnd = this.match.endTime;
            timeEnd = dateAndTimeEnd[11] + dateAndTimeEnd[12] + ":" + dateAndTimeEnd[14] + dateAndTimeEnd[15]
            this.match.timeEnd = timeEnd;
          }


          let year = dateAndTime[0] + dateAndTime[1] + dateAndTime[2] + dateAndTime[3]
          let month = dateAndTime[5] + dateAndTime[6]
          let day = dateAndTime[8] + dateAndTime[9];
          let date = year + "-" + month + "-" + day
          let time = dateAndTime[11] + dateAndTime[12] + ":" + dateAndTime[14] + dateAndTime[15]

          this.match.date = date;
          this.match.time = time;

          // Przypisanie graczy gospodarzy do zmiennej
          let homePlayers = [];
          let homeKeepers = [];
          if (this.match.homeTeamMatchLineup !== null) {
            this.match.homeTeamMatchLineup.matchPlayers.forEach((p) => {
              if (p.player !== null) {
                p.player.playerNumber = p.number;
                p.player.position = p.position
                p.player.img = blobToDataURL(p.player.image)
                if(p.position === 'BR'){
                  homeKeepers.push(p.player)
                }
                homePlayers.push(p.player);
              }

            })
            this.homePlayers = homePlayers;
            this.homeKeepers = homeKeepers;
          }


          // Przypisanie graczy gości do zmiennej
          let visitingPlayers = [];
          let visitingKeepers = [];
          if (this.match.visitingTeamMatchLineup !== null) {
            this.match.visitingTeamMatchLineup.matchPlayers.forEach((p) => {
              if (p.player !== null) {
                p.player.playerNumber = p.number;
                p.player.position = p.position
                p.player.img = blobToDataURL(p.player.image)
                visitingPlayers.push(p.player);
                if(p.position === 'BR') {
                  visitingKeepers.push(p.player)
                }
              }
            })
            this.visitingPlayers = visitingPlayers;
            this.visitingKeepers = visitingKeepers;
          }

        })

  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    saveSquads() {
      let homePlayers = this.$refs.homeTeamSquad.players;
      let visitingPlayers = this.$refs.visitingTeamSquad.playersVisiting;

      let homePlayersEdited = []
      homePlayers.forEach((h) => {
        let x = {}
        x.playerId = h.id
        x.number = h.playerNumber;
        x.position = h.position
        homePlayersEdited.push(x)
      })

      let visitingPlayersEdited = []
      visitingPlayers.forEach((v) => {
        let x = {}
        x.playerId = v.id
        x.number = v.playerNumber;
        x.position = v.position
        visitingPlayersEdited.push(x);
      })
      //homePlayersSquad
      const jsonHome = JSON.stringify(homePlayersEdited);

      axios.post('http://localhost:8080/api/matches/' + this.id + '/' + this.match.homeTeam.id + '/home_team_players',
          jsonHome, {
            headers: {
              'Authorization': 'Bearer '+this.User.userInfo.token,
              'Content-Type': `application/json`,
            }
          }
      )
          .then(response => {
            console.log(response)
          })

      //vistingPlayersSquad
      const jsonVisiting = JSON.stringify(visitingPlayersEdited);


      setTimeout(()=>{
        axios.post('http://localhost:8080/api/matches/' + this.id + '/' + this.match.visitingTeam.id + '/visiting_team_players',
            jsonVisiting, {
              headers: {
                'Authorization': 'Bearer '+this.User.userInfo.token,
                'Content-Type': `application/json`,
              }
            }
        )
            .then(response => {
              console.log(response)
            })
      }, 1000)

    },
    startMatch() {
      this.matchStarted = true;
      axios.put("http://localhost:8080/api/matches/" + this.match.id + "/status", {
        status: 'IN_PROGRESS',
      }, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `multipart/form-data`,
        }
      }).then(response => {
        if (response.status === 200) {
          UIkit.notification({
            message: 'Pomyślnie rozpoczęto spotkanie.',
            status: 'success',
            pos: 'top-center',
            timeout: 5000
          });
        }
      })
      this.$emit('incrementKey')
      setTimeout(() => {
        let switcherLiElements = document.getElementById("switcher-tlh-administrator").getElementsByTagName('li');
        let switcher = UIkit.switcher('#component-tab-left-match')
        switcher.show(2);
        activeLi(2, switcherLiElements);
      }, 500)
    },
    incrementKey(context) {
      this.keyValue++;
      if (context === 'akcje-meczowe') {
        setTimeout(() => {
          let switcherLiElements = document.getElementById("switcher-tlh-administrator").getElementsByTagName('li');
          let switcher = UIkit.switcher('#component-tab-left-match')
          switcher.show(2);
          activeLi(2, switcherLiElements);
        }, 500)
      }
      if(context === 'rzuty-karne'){
        setTimeout(() => {
          let switcherLiElements = document.getElementById("switcher-tlh-administrator").getElementsByTagName('li');
          let switcher = UIkit.switcher('#component-tab-left-match')
          switcher.show(3);
          activeLi(3, switcherLiElements);
        }, 500)
      }
      this.$emit('incrementKey')
    },

    changeContext() {
      // this.$router.push('/panel')
      setTimeout(() => {
        let switcherLiElements = document.getElementById("switcher-tlh-administrator").getElementsByTagName('li');
        UIkit.switcher('#component-tab-left').show(6);
        activeLi(6, switcherLiElements);
      }, 1000)

    }
  }
}
</script>

<style scoped>
#navbar-tlo {
  background-color: #8debff;
  position: absolute;
  top: 0;
  width: 100%;
  height: 250px;
  z-index: -1;
}

#przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
}

#menu-mecz > ul > li > a {

  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-mecz > ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}

#mecz-naglowek {
  background-color: #e1feff;
  padding: 50px;
  border-top: 15px solid #8debff;
}

.tlh-obrazek-sredni > img {
  width: 100px;
}

#przycisk-zapisz-sklad {
  text-align: center;
}

.match-sub-score {
  font-weight: bold;
  border-top: 5px solid #a6efff;
}
</style>