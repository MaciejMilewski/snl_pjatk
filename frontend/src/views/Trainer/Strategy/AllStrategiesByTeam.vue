<template>
  <StrategyView  />
</template>

<script>
import StrategyView from "@/views/Trainer/Strategy/StrategyView";

export default {
  name: "AllStrategiesByTeam",
  components: {StrategyView},
  data(){
    return {
      keyValue: 0,
    }
  },
}
</script>

<style scoped>

</style>