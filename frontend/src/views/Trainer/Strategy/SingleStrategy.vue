<template>
  <h1 class="tlh-strategia-naglowek uk-margin-top" style="display: inline-block;width: 80%">{{strategy.name}}</h1>
  <div  class="strategia-usun" style="display: inline-block">
    <a @click="removeStrategy(strategy.id)"><span class="strategia-usun-span"><h1 class="strategia-usun-naglowek">Usuń </h1></span></a>
  </div>
  <img :src="strategy.image"/>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";

export default {
  name: "SingleStrategy",
  props:['strategy'],
  emits: ['incrementKey'],
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  methods: {
    ...mapActions(['SetStrategies']),
    removeStrategy(strategyId){
      axios.delete("http://localhost:8080/api/strategies/"+strategyId,{
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
        }
      })
      let strategiesArray = []
      axios.get("http://localhost:8080/api/teams/1/strategies").then(function (result) {
        result.data.forEach((data) => {
          data.image = blobToDataURL(data.image)
          strategiesArray.push(data)
        })
      })
      this.SetStrategies(strategiesArray)
      setTimeout(()=>{
        this.$emit('incrementKey');
      },500)

    }
  }
}
</script>

<style scoped>
.tlh-strategia-naglowek{
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
.strategia-usun {
  font-weight: bold;
  color: #006394;
}
.strategia-usun-span {
  color: #006394;
}
.strategia-usun-naglowek {
  display: inline-block;
  background-color: #006394;
  color: #a6efff;
  font-weight: bold;
  border-bottom-left-radius: 20px;
  padding: 10px
}
</style>