<template>
  <div id="notatki-medyka-sedziego-form">
    <h2 class="uk-text-center">Status: <b>{{ status }}</b></h2>
    <div v-if="status === 'DISAPPROVAL' || status === 'CORRECTED'">
      <div v-for="comment in comments" :key="comment.id">
        <div style="border: 1px solid #a6efff">
          <div><b>Autor: </b> {{comment.author}}</div>
          <div><b>Treść: </b>{{comment.message}}</div>
        </div>
      </div>
    </div>
    <textarea v-model="note" rows="4" cols="160" placeholder="Treść notatki" />
    <div>

      <button
          class="uk-button uk-button-default uk-width-1-2 margin-left"
          @click="accept()">
        Akceptuj
      </button>
      <button
          class="uk-button uk-button-default uk-width-1-2 margin-left"
          @click="disaproval()">
        Odrzuć
      </button>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "MedicOrJudgeNotes",
  props: ['status', 'matchId'],
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
  data() {
    return {
      note: null,
      comments: []
    }
  },
  methods: {
    disaproval() {
      ///matches/{id}/status/acceptance parametr isAccepted: true
      axios.put("http://localhost:8080/api/matches/" + this.matchId + "/status/acceptance", {
        status: 'DISAPPROVAL',
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
    accept() {
      axios.put("http://localhost:8080/api/matches/" + this.matchId + "/status/acceptance", {
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
    }
  }
}
</script>

<style scoped>

</style>