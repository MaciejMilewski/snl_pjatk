<template>
  <nav class="uk-navbar-container uk-margin" uk-navbar id="navbar-mobile">


    <!--Lewo-->
    <a class="uk-navbar-item uk-logo" href="#">
      <img :src="require(`@/assets/krazek.svg`)">
    </a>

    <div class="uk-navbar-left">
      <a class="uk-navbar-toggle uk-navbar-toggle-animate" uk-navbar-toggle-icon href="#"></a>
      <div class="uk-navbar-dropdown">
        <ul class="uk-nav uk-navbar-dropdown-nav">
          <li class="uk-active"><router-link to="/">Aktualności</router-link></li>
          <li><router-link to="/ligi">Ligi</router-link></li>
          <li><router-link to="/zespoly">Zespoły</router-link></li>
          <li><router-link to="/kontakt">Kontakt</router-link></li>
        </ul>
      </div>

      <a class="uk-navbar-item uk-logo" href="#">
        <img id="zdjecie-logo" class="uk-animation-stroke" :src="require(`@/assets/logo.svg`)">
      </a>

    </div>

    <!--    Prawo -->
    <div class="uk-navbar-right">

      <!--      Wyszukiwarka-->
      <div class="uk-navbar-item">
        <SearchComponent/>
      </div>
    </div>
  </nav>
  <router-view/>
</template>

<script>
import SearchComponent from "@/components/Search/SearchComponent";
export default {
  name: "NavbarComponentMobileView",
  components: {SearchComponent}
}
</script>

<style lang="less" scoped>
#zdjecie-logo {
  height: 75px !important;
}
#navbar-mobile {
  .uk-navbar-left {
    .uk-navbar-dropdown{
      width: 400px;
      border-radius: 30px;
      box-shadow: 2px 2px 2px 1px rgba(0,0,0,0.2);
      background-image: linear-gradient(to left, #8debff,#a6efff,#aef9ff,#ccfbff,#e1feff);
    }
  }
  padding: 20px 0 0 0 ;
  ul {
    list-style: none;
    width: 400px;
    padding-left: 40px;
    li{
      font-weight: bold;
      color: #006394;
      font-size: 2rem;
      margin-bottom: 20px;
      cursor: pointer;
      .router-link-active {
        color: #0e6dcd;
      }
      a {
        color: #006394;

        &:hover {
          color: #0e6dcd;
        }
      }
      &:hover {
        color: #0e6dcd;
      }
    }
  }
}

</style>