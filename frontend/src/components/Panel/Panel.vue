<template>
  <div>

    <div v-if="User !== null">
      <div v-if="User.group === 'deweloper'">
        <DeveloperPanelComponent @incrementKey="incKey" :key="keyValue" />
      </div>
      <div v-if="User.group === 'trener'">
        <TrainerPanelComponent @incrementKey="incrementKey" :key="keyValue" />
      </div>
      <div v-if="User.group === 'sekretarz'">
        <SecretaryPanelComponent />
      </div>
      <div v-if="User.group === 'sedzia_medyk'">
        <MedicOrJudglePanelComponent />
      </div>
    </div>
    <div v-else>
      You cannot login
    </div>

  </div>


</template>

<script>
import DeveloperPanelComponent from "@/components/Developer/DeveloperPanel";
import useKey from "@/composables/useKey";
import activeLi from "@/assets/scripts/setActiveLi";
import UIkit from "uikit";
import {mapGetters} from "vuex";
import TrainerPanelComponent from "@/components/Trainer/TrainerPanelComponent";
import SecretaryPanelComponent from "@/components/Secretary/SecretaryPanelComponent";
import MedicOrJudglePanelComponent from "@/components/MedicOrJudge/MedicOrJudglePanelComponent";

export default {
  name: "PanelComponent",
  components: {MedicOrJudglePanelComponent, SecretaryPanelComponent, TrainerPanelComponent, DeveloperPanelComponent},
  setup() {
    const {keyValue, incrementKey} = useKey(0, 1)

    function incKey(context) {
      // Wszystkie elementy li z komponentu switcher uikit
      let switcherLiElements = document.getElementById("switcher-tlh-administrator").getElementsByTagName('li');

      setTimeout(() => {
        console.log(context)
        if (context === 'player') {
          incrementKey();
        } else if (context === 'article') {
          // Kontekst artykułu
          incrementKey();
          setTimeout(() => {
            let switcher = UIkit.switcher('#component-tab-left')
            switcher.show(1);
            activeLi(1, switcherLiElements);
          }, 100)
        } else if (context === 'team') {
          // Kontekst zespołu
          incrementKey();
          setTimeout(() => {
            let switcher = UIkit.switcher('#component-tab-left')
            switcher.show(2);
            activeLi(2, switcherLiElements);

          }, 100)
        }
        else if (context === 'sponsor') {
          // Kontekst zespołu
          incrementKey();
          setTimeout(() => {
            let switcher = UIkit.switcher('#component-tab-left')
            switcher.show(3);
            activeLi(3, switcherLiElements);
          }, 100)
        }
      }, 100)
    }

    return {
      keyValue, incrementKey, incKey
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
}
</script>

<style scoped>

</style>