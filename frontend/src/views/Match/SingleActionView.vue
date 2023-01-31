<template>
  <tr>
    <td width="5%">{{actionPrepared.minutes}}:{{actionPrepared.seconds}}</td>
    <td width="5%">{{ actionPrepared.typ }}</td>
    <td width="5%">{{actionPrepared.dlugosc}}</td>
    <td width="5%">{{actionPrepared.rodzaj}}</td>
    <td width="75%" v-html="actionPrepared.akcja"></td>
    <td width="10%" v-if="User !== null"><a @click="removeAction(actionPrepared.id)"><div  uk-icon="icon:  minus"/></a> </td>
  </tr>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "SingleActionView",
  props: ['action'],
  emits: ['incrementKey'],
  data(){
    return {
      actionPrepared: null,
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods : {
    removeAction(id){
      switch (this.actionPrepared.type){
        case 'PENALTY_SHOT_MISSED':
          axios.delete("http://localhost:8080/api/matches/penaltyShotMissed/"+id, {
            headers: {
              'Authorization': 'Bearer '+this.User.userInfo.token,
            }
          })
          break;
        case 'PENALTY_SHOT':
          axios.delete("http://localhost:8080/api/matches/penaltyShot/"+id, {
            headers: {
              'Authorization': 'Bearer '+this.User.userInfo.token,
            }
          })
          break;
        case 'PENALTY':
          axios.delete("http://localhost:8080/api/matches/penalties/"+id,{
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
          }
        })
          break;
        case 'CHANGE_GOALKEEPER':
          axios.delete("http://localhost:8080/api/matches/changeGolkiper/"+id,{
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
          }
        })
          break;
        case 'BREAK_TIME':
          axios.delete("http://localhost:8080/api/matches/breakeTime/"+id,{
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
          }
        })
          break;
        case 'GOAL':
          axios.delete("http://localhost:8080/api/matches/goals/"+id,{
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
          }
        })
          break;
      }

      setTimeout(()=>{
        this.$emit('incrementKey','akcje-meczowe')
      },500)

    }
  },
  created() {
    let actionPrepared = this.action
    // actionPrepared.czas = actionPrepared.gameMinute+":"+actionPrepared.gameSecond;
    switch(actionPrepared.type){
      case 'PENALTY_SHOT_MISSED':
        actionPrepared.typ = 'Rzut karny nietrafiony'
          actionPrepared.dlugosc = ''
          actionPrepared.rodzaj = ''
        break;
      case 'PENALTY_SHOT':
        actionPrepared.typ = 'Rzut karny'
        actionPrepared.dlugosc = ''
          actionPrepared.rodzaj = ''
          actionPrepared.akcja = actionPrepared.goalkeeperName + " "
              + actionPrepared.goalkeeperSurname
              + " -> " + actionPrepared.shooterName + " "
              + actionPrepared.shooterSurname
        break;
      case 'PENALTY':
        actionPrepared.typ = 'Kara'
        actionPrepared.dlugosc = actionPrepared.timeLength;
        actionPrepared.rodzaj = actionPrepared.typePenalty;
        actionPrepared.akcja = "#" + actionPrepared.penalizedPlayerNumber
            + " "
            + actionPrepared.penalizedPlayerName
            + " "
            + actionPrepared.penalizedPlayerSurname;
        break;
      case 'CHANGE_GOALKEEPER':
        actionPrepared.typ = 'Zmiana bramkarza'
          actionPrepared.dlugosc = ''
          actionPrepared.rodzaj = ''
          actionPrepared.akcja = "<span uk-icon='icon: arrow-up' style='color: green'>"
              + actionPrepared.goalkeeperInName
              + " "
              + actionPrepared.goalkeeperInSurname
              + "</span>  "
              + "<span uk-icon='icon: arrow-down' style='color: red;'> "
              + " "
              + actionPrepared.goalkeeperOutName
              + " "
              + actionPrepared.goalkeeperOutSurname
          + "</span>"
        break;
      case 'BREAK_TIME':
        actionPrepared.typ = 'Czas'
          actionPrepared.dlugosc = '';
        actionPrepared.rodzaj = ''
          actionPrepared.akcja = actionPrepared.teamName;
        break;
      case 'GOAL':
        actionPrepared.typ = 'Gol'
          actionPrepared.dlugosc = '';
        actionPrepared.rodzaj = actionPrepared.situation
          actionPrepared.akcja = "<b>#" + actionPrepared.scorerNumber
          + actionPrepared.scorerName
          + " "
          + actionPrepared.scorerSurname;

        if(actionPrepared.assistant1Name){
          actionPrepared.akcja = actionPrepared.akcja + "</b> #"+actionPrepared.assistant1Number + " " + actionPrepared.assistant1Name + " " +
              actionPrepared.assistant1Surname
        }
        if(actionPrepared.assistant2Name){
          actionPrepared.akcja =  actionPrepared.akcja  + " #" + actionPrepared.assistant2Number + " " +
              actionPrepared.assistant2Name + " " + actionPrepared.assistant2Surname
        }

        break;

    }
    actionPrepared.minutes = parseInt(actionPrepared.totalTimeInSeconds/60)
    actionPrepared.seconds = parseInt(actionPrepared.totalTimeInSeconds) % 60;
    if(actionPrepared.seconds < 10){
      actionPrepared.seconds = "0"+actionPrepared.seconds;
    }
    this.actionPrepared = actionPrepared;
  }
}
</script>

<style scoped>

</style>