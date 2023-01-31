<template>
  <input type="text"
         placeholder="Imię"
         class="uk-input"
         id="imie"
         :value="firstName"
         @input="$emit('update:firstName',$event.target.value)
           "
  >
</template>

<script>
import {watch} from 'vue'
import UIkit from 'uikit';

export default {
  name: "NameView",
  props: {
    firstName: {
      type: [String]
    }
  },
  setup(props) {
    //Obsługa ostrzeżeń
    watch(() => props.firstName, (newValue) => {
      if (props.firstName !== undefined && props.firstName.length > 0) {
        //Imię z małej litery
        if (newValue[0] === newValue[0].toLowerCase()) {
          UIkit.notification({
            message: 'Imię rozpocznij od wielkiej litery!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
        let pattern = / /i;
        //Imię zawiera spacje
        if (pattern.test(newValue)) {
          UIkit.notification({
            message: 'Imię zawiera spacje!',
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