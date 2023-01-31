<template>
 <div v-if="status === 'DISAPPROVAL' || status === 'CORRECTED'">
   <div v-for="comment in comments" :key="comment.id">
     <div style="border: 1px solid #a6efff">
       <div><b>Autor: </b> {{comment.author}}</div>
       <div><b>Treść: </b>{{comment.message}}</div>
     </div>
   </div>
   <div v-if="User.group !== 'deweloper'">
      <textarea
          v-model="note"
          rows="4"
          cols="160"
          placeholder="Treść notatki"
          style="margin-top: 15px"
      />
   </div>

   <div>
     <button
         class="uk-button uk-button-default uk-width-1-2"
         @click="agree()">
       Zatwierdź
     </button>
     <button v-if="User.group === 'sekretarz'"
         class="uk-button uk-button-default uk-width-1-2"
         @click="disagree()">
       Odrzuć
     </button>
   </div>

 </div>
  <div v-else>
    <button
        class="uk-button uk-button-default uk-width-1-1"
        @click="closeMatch()">
      Zakończ mecz
    </button>
  </div>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "CloseMatchView",
  props: ['matchId','status'],
  data(){
    return {
      comments: [],
      note: null,
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  created() {
    axios.get("http://localhost:8080/api/matches/"+ this.matchId+"/comments",{
      headers: {
        'Authorization': 'Bearer '+this.User.userInfo.token,
      }
    })
        .then(response=>{
          this.comments = response.data;
        })
  },
  methods: {
    agree(){
      let status = 'CORRECTED';
      if(this.User.group === 'deweloper'){
        status = 'PROTOCOL_FINALIZED'
      }
      axios.put("http://localhost:8080/api/matches/" + this.matchId + "/status/acceptance", {
        status: status,
        userId: this.User.userInfo.id,
        isAccepted: true,
      }, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `multipart/form-data`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie zaakceptowano.',
                status: 'success',
                pos: 'top-center',
                timeout: 2500
              });
            }
          })
    },
    disagree(){
      axios.put("http://localhost:8080/api/matches/" + this.matchId + "/status/acceptance", {
        status: 'CONFLICT',
        userId: this.User.userInfo.id,
        isAccepted: false,
      }, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `multipart/form-data`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie odrzucono.',
                status: 'success',
                pos: 'top-center',
                timeout: 2500
              });
            }
          })
      //http://localhost:8080/api/matches/4/comments
      axios.post("http://localhost:8080/api/matches/"+this.matchId+"/comments", {
        message: this.note,
        userId: this.User.userInfo.id,
      }, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `multipart/form-data`,
        }
      })
          .then(response => {
            if(response.status === 201){
              UIkit.notification({
                message: 'Pomyślnie dodano notatkę.',
                status: 'success',
                pos: 'top-center',
                timeout: 2500
              });
            }
          })
    },
    closeMatch() {
      let status = 'FINISHED';
      if(this.status === 'CONFLICT'){
        status = 'PROTOCOL_FINALIZED'
      }
      axios.put("http://localhost:8080/api/matches/" + this.matchId + "/status/acceptance", {
        status: status,
        userId: this.User.userInfo.id,
        isAccepted: true,
      }, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `multipart/form-data`,
        }
      })
          .then(response => {
            if (response.status === 200) {
              UIkit.notification({
                message: 'Pomyślnie zakończono mecz.',
                status: 'success',
                pos: 'top-center',
                timeout: 2500
              });
            }
          })
    },
  }
}
</script>

<style scoped>

</style>