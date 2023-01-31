<template>
  <input
      type="number"
      min="1"
      :placeholder="placeholder"
      class="uk-input"
      id="zawodnik-waga"
      :value="weight"
      @input="$emit('update:weight', parseInt($event.target.value))"
  >
</template>

<script>
import {watch} from "vue";
import UIkit from "uikit";

export default {
  name: "WeightView",
  data(){
    return {
      placeholder: "Waga zawodnika (kg)"
    }
  },
  props: {
    weight: {
      type: Number
    }
  },
  setup(props){
    //Obsługa ostrzeżeń
    watch(() => props.weight, (newValue) => {
      //Sprawdzić wagę
      //Najcięższy człowiek na Ziemii - Manuel Uribe - ok. 600kg
      if(newValue >= 600 ){
        UIkit.notification({
          message: 'Czy jesteś pewien wagi? '+newValue + "kg to trochę dużo...",
          status: 'warning',
          pos: 'top-center',
          timeout: 10000
        });
      }

    })
  }

}
</script>