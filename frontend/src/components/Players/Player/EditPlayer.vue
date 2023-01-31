<template>
  <div v-if="player">
    <div class="uk-container uk-align-center">
      <a href="#panel" id="przycisk-powrot" class="uk-button-default"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
      <PlayerForm :edit-context="true" :player="this.player" :key="keyValue"/>
    </div>
  </div>
  <div v-else>
    <PageLoader/>
  </div>

</template>

<script>
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {reactive, toRefs} from "vue";
import {useRoute} from "vue-router";
import PlayerForm from "@/components/Players/Player/PlayerForm";
import useKey from "@/composables/useKey";
import PageLoader from "@/components/Loader/PageLoader";
export default {
  name: "EditPlayer",
  components: {PageLoader, PlayerForm},
  setup(){
    const {keyValue, incrementKey} = useKey(0, 1)

    const router = useRoute()
    const state = reactive({
      id: router.params.id
    })
    return {
      keyValue,
      incrementKey,
      ...toRefs(state),
      router
    }
  },
  data(){
    return {
      player: {}
    }
  },
  created() {
    axios.get("http://localhost:8080/api/players/"+this.id)
        .then((response) =>{
          this.player = response.data
          if(this.player.stick === 'Lewy'){
            this.player.stick = {
              "value": "Lewe",
              "stick":"Lewy",
              "name":"Leworęczny"
            }
          }else{
            this.player.stick = {
              "name": "Praworęczny",
              "stick": "Prawy",
              "value": "Prawe"
            }
          }
          let objPosition = {}
          switch(this.player.position){
            case "BR": {
              objPosition.position = "BR"
              objPosition.name = "Bramkarz"
            }break;
            case "LO": {
              objPosition.position = "LO"
              objPosition.name = "Lewy obrońca"
            }break;
            case "PO": {
              objPosition.position = "PO"
              objPosition.name = "Prawy obrońca"
            }break;
            case "LS": {
              objPosition.position = "LS"
              objPosition.name = "Lewy napastnik"
            }break;
            case "SR": {
              objPosition.position = "SR"
              objPosition.name = "Środkowy napastnik"
            }break;
            case "PS": {
              objPosition.position = "PS"
              objPosition.name = "Prawy napastnik"
            }break;

          }
          this.player.position = objPosition
          this.player.id = this.id
          this.player.image = blobToDataURL(this.player.image)
          this.player.teamImage = blobToDataURL(this.player.teamImage)
          this.incrementKey()
        })
  }
}
</script>

<style scoped>
#przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
}
</style>