<template>
<SingleMatchView @incrementKey="incrementKey" :key="keyValue"/>
</template>

<script>
import SingleMatchView from "@/views/Match/SingleMatchView";
export default {
  name: "SingleMatchComponent",
  components: {SingleMatchView},
  data(){
    return {
      keyValue: 0,
    }
  },
  methods: {
    incrementKey(context){
      this.keyValue++;
      console.log(context);

    }
  }
}
</script>

<style scoped>

</style>