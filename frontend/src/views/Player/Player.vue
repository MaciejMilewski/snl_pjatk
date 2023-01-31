<template>
  <tr @click="singlePlayerView">
    <td><img :src="image" style="height: 100px; width: auto"></td>
    <td>{{ id }}</td>
    <td>{{ name }}</td>
    <td>{{ surname }}</td>
    <td>{{ playerNumber }}</td>
    <td>{{ position }}</td>
    <td>{{ birthDate }}</td>
    <td>{{ birthplace }}</td>
    <td>{{ weight }}</td>
    <td>{{ stick }}</td>
  </tr>
</template>

<script>
import router from "@/router";
export default {
  name: "PlayerView",
  props: ['image','id','name','surname','playerNumber','position','birthDate','birthplace','weight','stick','teamId','viewType'],
  setup(props){
    function singlePlayerView(){
        router.push('/zawodnik/'+props.id)
    }
    return {
      singlePlayerView
    }
  }
}
</script>

<style scoped>

</style>