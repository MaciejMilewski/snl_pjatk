<template>
  <div>
    <div uk-grid id="tlh-druzyny-grid">
      <div class="uk-width-auto@m" id="tlh-druzyny-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-druzyny; animation: uk-animation-fade">
          <li @mouseenter="hover" @mouseleave="disableHover" @click="incrementKeyParent"><a class="players-tab-left">
            <span v-if="isHoverAddTeam">
            <img :src="require(`@/assets/icons/team-hover.svg`)" style="width: 20px">
          </span>
            <span v-else>
            <img :src="require(`@/assets/icons/team.svg`)" style="width: 20px">
          </span>
            Zespoły</a></li>
          <li><a  class="players-tab-left" ><span uk-icon="icon: plus" />Dodaj zespół</a></li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-druzyny" class="uk-switcher">
          <li>
            <TeamListAdmin
                :teams="teams"
                @incrementKey="incrementKeyParent"
            />
          </li>
          <li>
            <TeamForm/>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import TeamForm from "@/components/Team/TeamForm";
import TeamListAdmin from "@/views/Teams/Team/Admin/TeamListAdmin";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
export default {
  name: "TeamsTab",
  components: {TeamForm,TeamListAdmin},
  data() {
    return {
      isHoverAddTeam: false,
      isHoverTeam: false,
      teams: [],
    }
  },
  created() {
    axios.get("http://localhost:8080/api/teams")
        .then(response => {
          const data = response.data.data;
          if (data !== undefined)
            data.forEach((d) => {
              if (d.image !== null) {
                d.image = blobToDataURL(d.image)
              }
            })
          this.teams = data;
        })
  },
  methods: {
    hover(){
      this.isHoverAddTeam = true
    },
    disableHover(){
      this.isHoverAddTeam = false
    },
    hoverTeam(){
      this.isHoverTeam = true
    },
    disableHoverTeam(){
      this.isHoverTeam = false
    },
    incrementKeyParent(){
      this.$emit('incrementKey')
    }
  }
}
</script>

<style scoped>
#tlh-druzyny-grid {
  background-color: #8debff;
  border-top-left-radius: 10px;
}
#tlh-druzyny-uk-width-auto {
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