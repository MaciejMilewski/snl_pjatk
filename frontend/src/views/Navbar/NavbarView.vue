<template>
    <nav class="uk-navbar-container uk-margin" uk-navbar>


    <!--Lewo-->

    <div class="uk-navbar-left">

      <div v-if="User">
        <div v-if="User.group === 'deweloper' || User.group === 'trener' || User.group === 'sekretarz' || User.group === 'sedzia_medyk'">
          <a id="uk-navbar-ustawienia"
              class="uk-navbar-toggle uk-navbar-toggle-animate"
             uk-icon="icon: settings; ratio: 2"
             href="#/panel"
             style="color: #006394"
             aria-label="Otwórz menu"></a>
        </div>
      </div>

      <a class="uk-navbar-item uk-logo" href="#">
        <img :src="require(`@/assets/krazek.svg`)">
      </a>

      <a class="uk-navbar-item">
        <router-link to="/">Aktualności</router-link>
      </a>

      <a class="uk-navbar-item">
        <router-link to="/ligi">Ligi</router-link>
      </a>

      <a class="uk-navbar-item">
        <router-link to="/zespoly">Zespoły</router-link>
      </a>

      <a class="uk-navbar-item">
        <router-link to="/kontakt">Kontakt</router-link>
      </a>

    </div>

    <!--Środek-->
    <div class="uk-navbar-center">
      <a class="uk-navbar-item uk-logo" href="#">
        <img class="uk-animation-stroke" :src="require(`@/assets/logo.svg`)">
      </a>
    </div>

    <!--    Prawo -->
    <div class="uk-navbar-right">
      <div v-if="User">
        <a class="uk-navbar-item" v-if="User.group === 'deweloper' || User.group === 'trener' || User.group === 'sekretarz' || User.group === 'sedzia_medyk' ">
          <router-link to="/wyloguj-sie" @click="logout()">Wyloguj się <span uk-icon="icon: sign-out"/></router-link>
        </a>
      </div>
      <div v-else>
        <a class="uk-navbar-item">
          <router-link to="/zaloguj-sie">Zaloguj się <span uk-icon="icon: sign-in"/></router-link>
        </a>
      </div>
      <!--      Wyszukiwarka-->
      <div class="uk-navbar-item">
        <SearchComponent/>
      </div>
    </div>
  </nav>
  <router-view/>
</template>

<script>
import {mapGetters} from "vuex"
import SearchComponent from "@/components/Search/SearchComponent"

export default {
  name: "NavbarComponentView",
  components: {SearchComponent},
  inject: ['isSearching'],
  computed: {
    ...mapGetters({User: "StateUser", background: "StateBackground"})
  },
  methods: {
    async logout() {
      await this.$store.dispatch('LogOut');
    },
  }
}
</script>
<style lang="less" >
@import "@/assets/styles/navbar/navbar.less";
#uk-navbar-ustawienia:hover {
  color: #a6efff !important; ;
}
</style>

