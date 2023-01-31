<template>

  <VueMultiselect placeholder="-- Wybierz wygięcie kija wyszukując --"
                  label="zawodnik-kij"
                  v-model="value"
                  :options="options"
                  :custom-label="stickLabel"
                  :track-by="options.name"
                  :select-label="'Kliknij enter, aby wybrać'"
                  :deselect-label="'Kliknij enter, aby odznaczyć'"
                  aria-required="true"
  >
    <template v-slot:noResult>
      <span>Brak wyników</span>
    </template>
  </VueMultiselect>
</template>

<script>
import VueMultiselect from "vue-multiselect/src/Multiselect";
import playerStick from "@/assets/temp_data/playerStick.json"

export default {
  name: "PlayerStickView",
  components: {VueMultiselect},
  data() {
    return {
      value: null,
      options: playerStick
    }
  },
  methods: {
    stickLabel({ value}) {
      return `${value}`
    }
  }
}
</script>