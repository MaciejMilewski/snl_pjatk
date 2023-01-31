<template>
<SingleMatchView :key="keyValue"  @incrementKey="incrementKey"/>
</template>

<script>
import SingleMatchView from "@/views/Match/SingleMatchView.vue";
export default {
  name: "MatchComponent",
  components: {SingleMatchView},
  data(){
    return {
      keyValue: 0
    }
  },
  methods: {
    incrementKey(){
      this.keyValue++;
    }
  }
}
</script>

<style scoped>

</style>