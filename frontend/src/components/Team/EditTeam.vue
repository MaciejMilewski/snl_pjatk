<template>
  <div class="uk-container uk-align-center">
    <a href="#panel" @click='changeContextView' id="przycisk-powrot" class="uk-button-default uk-margin-bottom"><span uk-icon="icon: arrow-left; ratio: 2"/>Wróć</a>
    <TeamFormComponent :edit-context="true" :team="team" :key="keyValue"/>
  </div>
</template>

<script>
import TeamFormComponent from "@/components/Team/TeamForm";
import useKey from "@/composables/useKey";
import {useRoute} from "vue-router";
import {reactive, toRefs} from "vue";
import UIkit from "uikit";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
export default {
  name: "EditTeam",
  components: {TeamFormComponent},
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
      team: {}
    }
  },
  methods: {
    changeContextView(){
      setTimeout(()=>{
        UIkit.switcher('#component-tab-left').show(2)
      },500)
    }
  },
  created() {
    axios.get("http://localhost:8080/api/teams/"+this.id)
        .then((response) =>{
          this.team = response.data
          if(this.team.image !== null){
            this.team.image = blobToDataURL(this.team.image)
          }
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