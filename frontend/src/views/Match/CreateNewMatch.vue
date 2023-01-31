<template>
    <div v-if="User !== null && (User.group === 'deweloper' || User.group === 'sekretarz')">
      <form @submit.prevent="submitForm" class="" style="padding-right: 0">
        <div class="uk-form-controls">
          <label for="informacje-data-i-czas" class="uk-label">Data i czas spotkania</label>
          <div class="uk-grid" id="informacje-data-i-czas">
            <div class="uk-width-1-2" id="czas">
              <input
                  type="time"
                  placeholder="Czas spotkania"
                  class="uk-input"
                  id="czas-spotkania"
                  v-model="reactiveForm.matchTime"
              >
            </div>
            <div class="uk-width-1-2" id="data">
              <input
                  type="date"
                  placeholder="Data spotkania"
                  class="uk-input"
                  id="data-spotkania"
                  v-model="reactiveForm.matchDate"
              >
            </div>
          </div>


          <PlaceInput :placeholder="'Miejsce'" v-model:name="reactiveForm.place"/>

          <div v-if="editContext === false">
            <label for="informacje-druzyna-gospodarzy" class="uk-label">Drużyna gospodarzy</label>
            <div class="uk-grid" id="informacje-druzyna-gospodarzy">
              <div class="uk-width-1-1" id="druzyna-gospodarzy">
                <HomeTeam v-model="reactiveForm.homeTeam" id="druzyna-gospodarzy-input" @teamSelected="selectTeam"/>
              </div>
            </div>

            <div v-if="this.homeTeamSelected === true">
              <label for="informacje-druzyna-gosci" class="uk-label">Drużyna gości</label>
              <div class="uk-grid" id="informacje-druzyna-gosci">
                <div class="uk-width-1-1" id="druzyna-gosci">
                  <VisitingTeam v-model="reactiveForm.visitingTeam" id="druzyna-gosci-input" ref="guests-team"/>
                </div>
              </div>
            </div>

          </div>


          <div v-if="!editContext">
            <label for="informacje-sedziowie" class="uk-label">Sędziowie główni</label>
            <div class="uk-grid" id="informacje-sedziowie">
              <div class="uk-width-1-2" id="sedzia-pierwszy">
                <select v-model="reactiveForm.refereeOne" class="uk-select" style="margin-top: 10px">
                  <option v-for="sedzia in this.sedziowieMedycy" :value="sedzia.id+'-'+sedzia.username"
                          :key="sedzia.id">{{ sedzia.username }}
                  </option>
                </select>

              </div>
              <div class="uk-width-1-2" id="sedzia-drugi">
                <select v-model="reactiveForm.refereeTwo" class="uk-select" style="margin-top: 10px">
                  <option v-for="sedzia in this.sedziowieMedycy" :value="sedzia.id+'-'+sedzia.username"
                          :key="sedzia.id">{{ sedzia.username }}
                  </option>
                </select>
              </div>
            </div>

            <label for="informacje-medyk" class="uk-label">Medyk</label>
            <div class="uk-grid" id="informacje-medyk">
              <div class="uk-width-1-1">
                <select v-model="reactiveForm.medic" class="uk-select" style="margin-top: 10px">
                  <option v-for="sedzia in this.sedziowieMedycy" :value="sedzia.id+'-'+sedzia.username"
                          :key="sedzia.id">{{ sedzia.username }}
                  </option>
                </select>
              </div>
            </div>

            <label for="informacje-sekretarz" class="uk-label">Sekretarz</label>
            <div class="uk-grid" id="informacje-sekretarz">
              <div class="uk-width-1-1">
                <select v-model="reactiveForm.secretary" class="uk-select" style="margin-top: 10px">
                  <option v-for="sekretarz in this.sekretarze" :value="sekretarz.id" :key="sekretarz.id">
                    {{ sekretarz.username }}
                  </option>
                </select>
              </div>
            </div>
          </div>


          <label for="informacje-sedziowie-liniowi" class="uk-label">Sędziowie liniowi</label>
          <div class="uk-grid" id="informacje-sedziowie-liniowi">
            <div class="uk-width-1-2" id="sedzia-liniowy-pierwszy">
              <LinesmenOne :placeholder="'Sędzia liniowy '" v-model:name="reactiveForm.linesmenOne" id="sedzia-liniowy-pierwszy-input"/>

            </div>
            <div class="uk-width-1-2" id="sedzia-linowy-drugi">
              <LinesmenTwo :placeholder="'Sędzia liniowy '" v-model:name="reactiveForm.linesmenTwo" id="sedzia-liniowy-drugi-input"/>
            </div>
          </div>

          <label for="informacje-sedziowie-kar" class="uk-label">Sędziowie kar</label>
          <div class="uk-grid" id="informacje-sedziowie-kar">
            <div class="uk-width-1-2" id="sedzia-kar-pierwszy">
              <PenaltyRefereeOne :placeholder="'Sędzia kar '" v-model:name="reactiveForm.penaltyRefereeOne" id="sedzia-kar-pierwszy-id"/>

            </div>
            <div class="uk-width-1-2" id="sedzia-kar-drugi">
              <PenaltyRefereeTwo :placeholder="'Sędzia kar '" v-model:name="reactiveForm.penaltyRefereeTwo" id="sedzia-kar-drugi-id"/>
            </div>
          </div>

          <label for="informacje-sedzia-czasowy" class="uk-label">Sędzia czasowy</label>
          <div class="uk-grid" id="informacje-sedzia-czasowy">
            <div class="uk-width-1-1" id="sedzia-czasowy">
              <TimeReferee :placeholder="'Sędzia czasowy'" v-model:name="reactiveForm.timeReferee" id="sedzia-czasowy-input"/>
            </div>
          </div>

          <label for="informacje-trenerzy-host" class="uk-label">Trener drużyny gospodarzy</label>
          <div class="uk-grid" id="informacje-trener-gospodarzy">
            <div class="uk-width-1-2" id="trener-gospodarzy">
              <HomeTrainer :placeholder="'Trener gospodarzy'" v-model:name="reactiveForm.homeTrainer" id="trener-gospodarzy-input"/>

            </div>
            <div class="uk-width-1-2" id="menadzer-gospodarzy">
              <HomeManager :placeholder="'Kierownik gospodarzy'" v-model:name="reactiveForm.homeManager" id="menadzer-gospodarzy-input"/>
            </div>
          </div>

          <label for="informacje-trenerzy-host" class="uk-label">Trener drużyny gości</label>
          <div class="uk-grid" id="informacje-trener-gosci">
            <div class="uk-width-1-2" id="trener-gosci">
              <VisitingTrainer :placeholder="'Trener gości'" v-model:name="reactiveForm.visitingTrainer" id="trener-gosci-input"/>

            </div>
            <div class="uk-width-1-2" id="menadzer-gosci">
              <VisitingManager :placeholder="'Kierownik gości'" v-model:name="reactiveForm.visitingManager" id="menadzer-gosci-input"/>
            </div>
          </div>

          <label for="informacje-obserwator" class="uk-label">Obserwator</label>
          <div class="uk-grid" id="informacje-obserwator">
            <div class="uk-width-1-1" id="obserwator">
              <Spectator :placeholder="'Obserwator'" v-model:name="reactiveForm.spectator" id="obserwator-input"/>
            </div>
          </div>

          <label for="informacje-informacje-dodatkowe" class="uk-label">Informacje dodatkowe</label>
          <div class="uk-grid" id="informacje-dodatkowe">
            <div class="uk-width-1-2" id="frekwencja">
              <input
                  type="number"
                  min="0"
                  class="uk-input"
                  style="margin-top: 10px"
                  placeholder="Frekwencja"
                  v-model="reactiveForm.attendance">
            </div>
            <div class="uk-width-1-2" id="spiker">
              <Spiker :placeholder="'Spiker'" v-model:name="reactiveForm.spiker" id="spiker-input"/>
            </div>


          </div>


          <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-margin">Zapisz</button>
        </div>

      </form>

  </div>
  <div v-else>
    <label for="informacje-data-i-czas-inf" class="uk-label">Data i czas spotkania</label>
    <div class="uk-grid" id="informacje-data-i-czas-inf">
      <div class="uk-width-1-2" id="data-inf-czas">
        <div class="uk-margin-top">
          <b>Czas spotkania: </b>{{ reactiveForm.matchTime }}
        </div>
      </div>
      <div class="uk-width-1-2" id="data-inf">
        <div class="uk-margin-top">
          <b>Data spotkania: </b>{{ reactiveForm.matchDate }}
        </div>
      </div>
    </div>

    <label for="miejsce-inf" class="uk-label">Miejsce spotkania</label>
    <div class="uk-grid" id="miejsce-inf">
      <div class="uk-width-1-1">
        <div class="uk-margin-top">
          <b>Miejsce spotkania: </b>{{ reactiveForm.place }}
        </div>
      </div>
    </div>


    <label for="informacje-sedziowie-inf" class="uk-label">Sędziowie główni</label>
    <div class="uk-grid" id="informacje-sedziowie-inf">
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Sędzia: </b>{{ reactiveForm.refereeOne }}
        </div>
      </div>
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Sędzia: </b>{{ reactiveForm.refereeTwo }}
        </div>

      </div>
    </div>

    <label for="informacje-sedziowie-liniowi-inf" class="uk-label">Sędziowie liniowi</label>
    <div class="uk-grid" id="informacje-sedziowie-liniowi-inf">
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Sędzia liniowy: </b>{{ reactiveForm.linesmenOne }}
        </div>
      </div>
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Sędzia liniowy: </b>{{ reactiveForm.linesmenTwo }}
        </div>
      </div>
    </div>

    <label for="informacje-sedziowie-kar-inf" class="uk-label">Sędziowie kar</label>
    <div class="uk-grid" id="informacje-sedziowie-kar-inf">
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Sędzia kar: </b>{{ reactiveForm.penaltyRefereeOne }}
        </div>

      </div>
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Sędzia kar: </b>{{ reactiveForm.penaltyRefereeTwo }}
        </div>

      </div>
    </div>

    <label for="informacje-sedzia-czasowy-inf" class="uk-label">Sędzia czasowy</label>
    <div class="uk-grid" id="informacje-sedzia-czasowy-inf">
      <div class="uk-width-1-1">
        <div class="uk-margin-top">
          <b>Sędzia czasowy: </b>{{ reactiveForm.timeReferee }}
        </div>
      </div>
    </div>

    <label for="informacje-trenerzy-host-inf" class="uk-label">Trener drużyny gospodarzy</label>
    <div class="uk-grid" id="informacje-trener-gospodarzy-inf">
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Trener gospodarzy: </b>{{ reactiveForm.homeTrainer }}
        </div>
      </div>
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Kierownik gospodarzy: </b>{{ reactiveForm.homeManager }}
        </div>
      </div>
    </div>

    <label for="informacje-trenerzy-host-inf" class="uk-label">Trener drużyny gości</label>
    <div class="uk-grid" id="informacje-trener-gosci-inf">
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Trener gości: </b>{{ reactiveForm.visitingTrainer }}
        </div>

      </div>
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Kierownik gości: </b>{{ reactiveForm.visitingManager }}
        </div>

      </div>
    </div>

    <label for="informacje-obserwator-inf" class="uk-label">Obserwator</label>
    <div class="uk-grid" id="informacje-obserwator-inf">
      <div class="uk-width-1-1">
        <div class="uk-margin-top">
          <b>Obserwator: </b>{{ reactiveForm.spectator }}
        </div>
      </div>
    </div>


    <label for="informacje-informacje-dodatkowe-inf" class="uk-label">Informacje dodatkowe</label>
    <div class="uk-grid" id="informacje-dodatkowe-inf">
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Frekwencja: </b>{{ reactiveForm.attendance }}
        </div>
      </div>
      <div class="uk-width-1-2">
        <div class="uk-margin-top">
          <b>Spiker: </b>{{ reactiveForm.spiker }}
        </div>
      </div>
    </div>


  </div>


</template>

<script>
import HomeTeam from "@/views/Inputs/TeamId";
import VisitingTeam from "@/views/Inputs/TeamId";
import LinesmenOne from "@/views/Inputs/InputName";
import LinesmenTwo from "@/views/Inputs/InputName";
import PlaceInput from "@/views/Inputs/InputName";
import HomeTrainer from "@/views/Inputs/InputName";
import HomeManager from "@/views/Inputs/InputName";
import VisitingManager from "@/views/Inputs/InputName";
import VisitingTrainer from "@/views/Inputs/InputName";
import Spectator from "@/views/Inputs/InputName";
import TimeReferee from "@/views/Inputs/InputName";
import PenaltyRefereeOne from "@/views/Inputs/InputName";
import PenaltyRefereeTwo from "@/views/Inputs/InputName";
import Spiker from "@/views/Inputs/InputName";
import axios from "axios";
import {computed, reactive, toRefs} from "vue";
import {mapGetters} from "vuex";
import UIkit from "uikit";

export default {
  name: "CreateNewMatch",
  props: ['match', 'editContext'],
  components: {
    PlaceInput,
    LinesmenOne,
    LinesmenTwo,
    HomeTrainer,
    HomeManager,
    VisitingTrainer,
    VisitingManager,
    Spectator,
    TimeReferee,
    PenaltyRefereeOne,
    PenaltyRefereeTwo,
    Spiker,
    HomeTeam,
    VisitingTeam

  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  data() {
    return {
      sekretarze: [],
      sedziowieMedycy: [],
      comments: [],
      homeTeamSelected: false,
    }
  },
  created() {
    let sekretarze = [];
    let sedziowieMedycy = [];
    axios.get("http://localhost:8080/api/auth/users",{
    headers: {
      'Authorization': 'Bearer '+this.User.userInfo.token,
    }
    }).then(response => {
      response.data.forEach((d) => {
        if (d.roles[0].name === 'ROLE_SECRETARY') {
          sekretarze.push(d)
        } else if (d.roles[0].name === 'ROLE_MEDIC_OR_JUDGE') {
          sedziowieMedycy.push(d);
        }
      })
      this.sekretarze = sekretarze;
      this.sedziowieMedycy = sedziowieMedycy;
    })
    axios.get("http://localhost:8080/api/matches/"+this.match.id+'/comments',{
      headers: {
        'Authorization': 'Bearer '+this.User.userInfo.token,
      }
    })
        .then(response => {
          this.comments = response.data;
        })

  },
  setup(props) {
    let referee1 = '';
    let referee2 = '';
    if(props.match.referee1Id){
      referee1+=props.match.referee1Id+'-'
    }
    if(props.match.referee2Id){
      referee2+=props.match.referee2Id+'-'
    }

    const state = reactive({
      form: {
        place: '',
        refereeOne: '',
        refereeTwo: '',
        linesmenOne: '',
        linesmenTwo: '',
        homeTrainer: '',
        homeManager: '',
        visitingTrainer: '',
        visitingManager: '',
        spectator: '',
        timeReferee: '',
        penaltyRefereeOne: '',
        penaltyRefereeTwo: '',
        spiker: '',
        attendance: 0,
        homeTeam: '',
        visitingTeam: '',
        matchTime: '',
        matchDate: '',
        medic: '',
        secretary: '',
      },
      formFilled: {
        place: props.match.place,
        refereeOne: referee1 +  props.match.referee1,
        refereeTwo: referee2 + props.match.referee2,
        linesmenOne: props.match.linesmen1,
        linesmenTwo: props.match.linesmen2,
        homeTrainer: props.match.homeTeamCoach,
        homeManager: props.match.homeTeamMenager,
        visitingTrainer: props.match.visitingTeamCoach,
        visitingManager: props.match.visitingTeamMenager,
        spectator: props.match.spectator,
        timeReferee: props.match.timeReferee,
        penaltyRefereeOne: props.match.penaltyReferee1,
        penaltyRefereeTwo: props.match.penaltyReferee2,
        spiker: props.match.speaker,
        attendance: props.match.attendance,
        homeTeam: '',
        visitingTeam: '',
        matchTime: props.match.time,
        matchDate: props.match.date,
        medicNot: props.match.medicNot,
        refereeNot: props.match.refereeNot,
        medic: props.match.medicId,
        secretary: props.match.secretaryId,
      }
    })
    const reactiveForm = computed(function () {
      if (props.match.place) {
        return state.formFilled
      } else {
        return state.form
      }
    })
    console.log(reactiveForm);
    return {
      reactiveForm,
      ...toRefs(state)
    }
  },

  methods: {
    selectTeam(value){
      this.homeTeamSelected = true;
      setTimeout(()=>{
        this.$refs["guests-team"].options = value
      },500)

    },
    submitForm() {
      let sekretarzId;
      let sedzia1;
      let sedzia2;
      let medyk;
      let sedzia1Id;
      let sedzia2Id;
      let sedzia1Username;
      let sedzia2Username;
      let medykId;
      if (!this.match.place) {
        sekretarzId = this.reactiveForm.secretary
        sedzia1 = this.reactiveForm.refereeOne.split('-')
        sedzia2 = this.reactiveForm.refereeTwo.split('-')
        medyk = this.reactiveForm.medic.split('-')
        sedzia1Id = sedzia1[0];
        sedzia2Id = sedzia2[0];
        sedzia1Username = sedzia1[1];
        sedzia2Username = sedzia2[1];
        medykId = medyk[0];
      }


      const json = JSON.stringify({
        startTime: this.reactiveForm.matchDate + " " + this.reactiveForm.matchTime + ":00",
        speaker: this.reactiveForm.spiker,
        penaltyReferee2: this.reactiveForm.penaltyRefereeTwo,
        penaltyReferee1: this.reactiveForm.penaltyRefereeOne,
        timeReferee: this.reactiveForm.timeReferee,
        spectator: this.reactiveForm.spectator,
        visitingTeamCoach: this.reactiveForm.visitingTrainer,
        visitingTeamMenager: this.reactiveForm.visitingManager,
        homeTeamCoach: this.reactiveForm.homeTrainer,
        homeTeamMenager: this.reactiveForm.homeManager,
        linesmen2: this.reactiveForm.linesmenTwo,
        linesmen1: this.reactiveForm.linesmenOne,
        referee2: sedzia2Username,
        referee1: sedzia1Username,
        attendance: this.reactiveForm.attendance,
        place: this.reactiveForm.place,
        homeTeamId: this.reactiveForm.homeTeam.id,
        visitingTeamId: this.reactiveForm.visitingTeam.id,
        secretaryId: sekretarzId,
        medicId: medykId,
        referee1Id: sedzia1Id,
        referee2Id: sedzia2Id,
      })
      if (this.match.place) {
        // Edytowanie mecz info
        axios.put("http://localhost:8080/api/matches/" + this.match.id + "/info", json, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `application/json`
          }
        }).then(response =>  {
          if(response.status === 200){
              UIkit.notification({
                message: "Pomyślnie edytowano informacje dotyczące meczu",
                status: "success",
                pos: "top-center",
                timeout: 5000
              })
          }
        })

      } else {
        // Tworzenie mecz info
        axios.post("http://localhost:8080/api/matches/info", json, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `application/json`,
          }
        })
            .then(response => {
              if (response.status === 201) {
                UIkit.notification({
                  message: 'Pomyślnie dodano nowy mecz',
                  status: 'success',
                  pos: 'top-center',
                  timeout: 10000
                });
              }
            })
      }

    }
  }
}
</script>

<style scoped>
.uk-label {
  margin-top: 15px;
}


</style>