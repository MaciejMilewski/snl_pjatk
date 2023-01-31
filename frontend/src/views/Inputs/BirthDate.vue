<template>
  <input
      type="date"
      placeholder="Data urodzenia"
      class="uk-input"
      id="data-urodzenia"
      :value="birthDate"
      @input="$emit('update:birthDate',$event.target.value)"
  >
</template>

<script>
import {watch} from "vue";
import UIkit from "uikit";

export default {
  name: "BirthDateView",
  props: {
    birthDate: {
      type: Date
    }
  },
  setup(props) {
    watch(() => props.birthDate, (newValue) => {
      if (props.birthDate !== undefined && props.birthDate.length > 0) {
        //Zbyt "duża" data
        if (newValue.length > 10) {
          UIkit.notification({
            message: 'Rok jest zbyt duży!',
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