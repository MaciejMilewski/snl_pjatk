<template>
<EmailInputView v-model:email="reactiveForm.email" style="margin-top:10px"/>
<UserNameView v-model:username="reactiveForm.username"/>
<UserPasswordView v-model:password="reactiveForm.password"/>
  <select @change="onSelectRole" name="roles" id="tlh-roles" v-model="reactiveForm.role" class="uk-input">
    <option value="">--- Wybierz rolę użytkownika ---</option>
    <option value="admin">Admin</option>
    <option value="secretary">Sekretarz</option>
    <option value="trainer">Trener</option>
    <option value="medicOrJudge">Medyk lub Sędzia</option>
  </select>
  <div v-if="canAssignTeam"><TeamIdView v-model="reactiveForm.team"/></div>
    <button @click="submitForm" class="uk-button uk-button-default uk-width-1-1 uk-margin">Dodaj użytkownika</button>
</template>

<script>
import UserNameView from "@/views/Inputs/UserName";
import UserPasswordView from "@/views/Inputs/UserPassword";
import EmailInputView from "@/views/Inputs/Email";
import {computed, reactive} from "vue";
import UIkit from "uikit";
import axios from "axios";
import TeamIdView from "@/views/Inputs/TeamId";
import {mapGetters} from "vuex";
export default {
  name: "AddNewUserView",
  components: {TeamIdView, EmailInputView, UserPasswordView, UserNameView},
  computed: {...mapGetters({User: "StateUser"})},
  data(){
    return {
      canAssignTeam: false
    }
  },
  setup(){
    const state = reactive({
      form: {
        email: null,
        username: null,
        password: null,
        role: '',
        team: null,
      },
    })
    const reactiveForm = computed(function (){
        return state.form;

    })
    return {
      reactiveForm
    }
  },
  methods:{
    onSelectRole(){
      if(this.reactiveForm.role === 'trainer'){
        this.canAssignTeam = true;
      }
    },
    submitForm(){
      let canAddUser = true
      if(this.reactiveForm.username === null){
        canAddUser = false;
       UIkit.notification({
         message: "Nazwa użytkownika jest pusta!",
         status: 'warning',
         pos: 'top-center',
         timeout: 5000
       })
      }
      if(this.reactiveForm.password === null){
        canAddUser = false;
        UIkit.notification({
          message: "Hasło użytkownika jest puste!",
          status: 'warning',
          pos: 'top-center',
          timeout: 5000
        })
      }
      if(this.reactiveForm.email === null){
        canAddUser = false;
        UIkit.notification({
          message: "Email użytkownika jest pusty!",
          status: 'warning',
          pos: 'top-center',
          timeout: 5000
        })
      }
      if(this.reactiveForm.role === ''){
        canAddUser = false;
        UIkit.notification({
          message: "Wybierz rolę użytkownika!",
          status: 'warning',
          pos: 'top-center',
          timeout: 5000
        })
      }
      if(this.canAssignTeam && this.reactiveForm.team === null){
        canAddUser = false;
        UIkit.notification({
          message: "Wybierz drużynę trenera!",
          status: 'warning',
          pos: 'top-center',
          timeout: 5000
        })
      }
      const json = JSON.stringify({
        username: this.reactiveForm.username,
        email: this.reactiveForm.email,
        password: this.reactiveForm.password,
        role: [this.reactiveForm.role]
      })
      if(canAddUser)
      axios.post("http://localhost:8080/api/auth/signup", json,{
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `application/json`,
        }}).then((response)=>{
        UIkit.notification({
          message: "Dodano nowego użytkownika "+this.reactiveForm.email,
          status: "success",
          pos: "top-center",
          timeout: 5000
        })
        console.log(response);
      })
      setTimeout(() => {
        
      },1000)
    }
  }
}
</script>

<style scoped>

</style>