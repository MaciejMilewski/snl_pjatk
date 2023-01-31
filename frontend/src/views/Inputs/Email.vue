<template>
  <input
      type="email"
      id="email"
      name="email"
      class="uk-input"
      placeholder="Adres email"
      :value="email"
      @input="$emit('update:email',$event.target.value)">
</template>

<script>
export default {
  name: "EmailInputView",
  props: {
    email: {
      type: [String]
    }
  }
}
</script>

<style scoped>

</style>