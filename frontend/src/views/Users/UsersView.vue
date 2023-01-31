<template>
  <div>
    <div uk-grid id="tlh-uzytkownicy-grid">
      <div class="uk-width-auto@m" id="tlh-uzytkownicy-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-uzytkownicy; animation: uk-animation-fade">
          <li @click="incrementKeyParent" ><a class="users-tab-left" ><span uk-icon="icon: user"/>
            Użytkownicy</a></li>
          <li><a  @click="incrementKey" class="users-tab-left" href="#"><span uk-icon="icon: plus" />Dodaj użytkownika</a></li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-uzytkownicy" class="uk-switcher">
          <li><UsersListView :users="users" @switchToUser="switchToUser" :key="keyValue"/></li>
          <li><AddNewUserView/></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import AddNewUserView from "@/views/Users/AddNewUserView";
import axios from "axios";
import router from "@/router";
import UsersListView from "@/views/Users/UsersList";
import useKey from "@/composables/useKey";
import {mapGetters} from "vuex";
export default {
  name: "UsersView",
  components: {UsersListView, AddNewUserView},
  setup(){
    const {keyValue, incrementKey} = useKey(0, 1)
    function switchToUser(id){
      router.push('/edytuj-uzytkownika/'+id)
    }

    return {switchToUser, incrementKey, keyValue}
  },
  data(){
    return {
      users: [],
    }
  },
  methods: {
    incrementKeyParent(){
      this.$emit('incrementKey')
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  created() {
    axios.get('http://localhost:8080/api/auth/users', {
      headers: {
        'Authorization': 'Bearer '+this.User.userInfo.token
      }
    }).then((response)=> {
      this.users = response.data
    })
  }
}
</script>

<style scoped>
.uk-tab > .uk-active > a{
  font-weight: bold;
  border-color: #006394;
}
#tlh-uzytkownicy-grid {
  background-color: #8debff;
  border-top-left-radius: 10px;
}
#tlh-uzytkownicy-uk-width-auto {
  background-color: #a6efff;
  border-top-left-radius: 10px;
}
.uk-tab {
  background-color: #a6efff;
  padding: 10px;

  height: max-content !important;
}
.uk-tab > * > a:hover {
  color: #006394;
  font-weight: bold;
}
.uk-tab > .uk-active > a {
  font-weight: bold;
  color: #006394 !important;
  border-color: #006394;
  border-right-width: 5px;
}
</style>