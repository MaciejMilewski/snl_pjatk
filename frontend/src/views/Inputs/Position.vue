<template>
  <VueMultiselect placeholder="-- Wybierz pozycję wyszukując --"
                  label="pozycja-zawodnika"
                  v-model="value"
                  id="pozycja-zawodnika"
                  :options="options"
                  :custom-label="positionLabel"
                  :select-label="'Kliknij enter, aby wybrać'"
                  :deselect-label="'Kliknij enter, aby odznaczyć'"

  >
    <template v-slot:noResult>
      <span>Taka pozycja nie istnieje.</span>
    </template>
  </VueMultiselect>

</template>

<script>
import VueMultiselect from 'vue-multiselect/src/Multiselect';
import positionOptions from '@/assets/temp_data/playerPositions.json';

export default {
  name: "PlayerPositionView",
  components: {VueMultiselect},
  data() {
    return {
      value: null,
      options: positionOptions
    }
  },
  methods: {
    positionLabel({name, position}) {
      return `${position} - [${name}]`
    },
  }
}
</script>