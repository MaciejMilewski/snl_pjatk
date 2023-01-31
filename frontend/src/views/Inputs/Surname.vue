<template>
  <input
      type="text"
      placeholder="Nazwisko"
      class="uk-input"
      id="nazwisko"
      :value="lastName"
      @input="$emit('update:lastName', $event.target.value)"
  >
</template>

<script>
import {watch} from "vue";
import UIkit from "uikit";

export default {
  name: "SurnameView",
  props: {
    lastName: {
      type: String
    }
  },
  setup(props) {
    //Obsługa ostrzeżeń
    watch(() => props.lastName, (newValue) => {
      if (props.lastName !== undefined && props.lastName.length > 0) {
        //Nazwisko z małej litery
        if (newValue[0] === newValue[0].toLowerCase()) {
          UIkit.notification({
            message: 'Nazwisko rozpocznij od wielkiej litery!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
        let pattern = / /i;
        //Nazwisko zawiera spacje
        if (pattern.test(newValue)) {
          UIkit.notification({
            message: 'Nazwisko zawiera spacje!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
      }
    })
  },
}
</script>