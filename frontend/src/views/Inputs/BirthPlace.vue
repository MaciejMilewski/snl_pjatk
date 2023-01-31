<template>
  <input
      type="text"
      placeholder="Miejsce urodzenia"
      class="uk-input"
      id="miejsce-urodzenia"
      :value="birthplace"
      @input="$emit('update:birthplace',$event.target.value)"
  >
</template>

<script>
import {watch} from "vue";
import UIkit from "uikit";

export default {
  name: "BirthPlaceView",
  props: {
    birthplace: {
      type: String
    }
  },
  setup(props) {
    //Obsługa ostrzeżeń
    watch(() => props.birthplace, (newValue) => {
      if (props.birthplace !== undefined && props.birthplace.length > 0) {
        //Miejscowość z małej litery
        if (newValue[0] === newValue[0].toLowerCase()) {
          UIkit.notification({
            message: 'Miejscowość rozpocznij od wielkiej litery!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
      }
    })

  }
}
</script>