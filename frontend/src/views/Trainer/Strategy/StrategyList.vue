<template>
  <div v-for="strategy in strategies" :key="strategy.id">
    <SingleStrategy :strategy="strategy" @incrementKey="incrementKey"/>
  </div>
</template>

<script>
import axios from "axios";
import SingleStrategy from "@/views/Trainer/Strategy/SingleStrategy";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {mapGetters} from "vuex";

export default {
  name: "StrategyList",
  emits: ['incrementKeyParent'],
  components: {SingleStrategy},
  data() {
    return {
      keyValue: 0,
      strategies: []
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  created() {
    let strategiesArray = []
    let _this = this
    axios.get("http://localhost:8080/api/teams/1/strategies",{ headers: {
        'Authorization': 'Bearer '+this.User.userInfo.token,
      }}).then(function (result) {
      result.data.forEach((data) => {
        data.image = blobToDataURL(data.image)
      })
      strategiesArray = result.data;
      _this.strategies = strategiesArray;
    })

  },
  methods: {
    incrementKey() {
      this.$emit('incrementKey')
    }
  }

}
</script>

<style scoped>

</style>