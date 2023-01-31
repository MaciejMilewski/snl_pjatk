<template>
  <input
      type="number"
      min="0"
      placeholder="Numer zawodnika"
      class="uk-input"
      id="numer-zawodnika"
      :value="playerNumber"
      @input="$emit('update:playerNumber',parseInt($event.target.value))"
  >
</template>

<script>
import {watch} from "vue";
import UIkit from "uikit";

export default {
  name: "PlayerNumberView",
  props: {
    playerNumber: {
      type: Number
    }
  },
  setup(props) {
    //Obsługa ostrzeżeń
    watch(() => props.playerNumber, (newValue) => {

      //Zawiera znak 'e'
      let pattern = /e/i;
      if (pattern.test(newValue)) {
        UIkit.notification({
          message: 'Numer zawiera znak `e`!',
          status: 'warning',
          pos: 'top-center',
          timeout: 10000
        });
      }
    })
  }
}
</script>