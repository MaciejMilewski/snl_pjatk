<template>
  <div v-if="!User"
       class="uk-card uk-card-default uk-grid-collapse uk-child-width-1-2@s uk-margin"
       uk-grid
       uk-scrollspy="target: [uk-scrollspy-image]; cls: uk-animation-slide-left-medium; delay: 200;"
  >
    <div class="uk-card-media-left uk-cover-container"
         uk-scrollspy-image

    >
      <img :src="require(`@/assets/login_unsplash.jpg`)" alt="" id="zdjecie-login" uk-cover>
      <canvas height="650" width="auto"></canvas>
    </div>
    <div>
      <div class="uk-card-body" uk-scrollspy="target: [uk-scrollspy-login]; cls: uk-animation-slide-right-medium; delay: 500;">
        <h3 class="uk-card-title uk-text-center" id="logowanie-heading" uk-scrollspy-login>Logowanie</h3>
        <form @submit.prevent="submitForm(form)" class="uk-form" uk-scrollspy-login>
          <div class="uk-form-controls" >
            <div class="uk-grid">
              <div class="uk-width-expand">
                <UserNameView v-model:username="form.username" />
              </div>
              <div class="uk-width-expand">
                <UserPasswordView v-model:password="form.password"/>
              </div>
            </div>

          </div>
          <button class="uk-button uk-button-default uk-width-1-1">Zaloguj się</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import UserNameView from "@/views/Inputs/UserName";
import { reactive, toRefs} from "vue";
import UserPasswordView from "@/views/Inputs/UserPassword";
import {mapActions, mapGetters} from 'vuex'
import UIkit from "uikit";

export default {
  name: "LoginFormView",
  components: {UserPasswordView, UserNameView},
  setup(){
    const state = reactive({
      form: {
        username: '',
        password: '',
      }
    })
    return {
      ...toRefs(state)
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods:{
    //Zaciągnięcie metody LogIn z vuex
    ...mapActions(['LogIn']),
    async submitForm() {
      //Create user data
      let User = new FormData();
      User.set('username', this.form.username);
      User.set('password',this.form.password);
      try {
        await this.LogIn(User)
        this.showError = false;
        UIkit.notification({
          message: "Wprowadzone dane są prawidłowe :)",
          status: 'success',
          pos: 'top-center'
        })
        this.$router.push('/panel')
      }catch (error){
        this.showError = true;
        UIkit.notification({
          message: "Wprowadzone dane są nieprawidłowe!",
          status: 'danger',
          pos: 'top-center'
        })
      }
    }
  }
}
</script>
<style>

</style>