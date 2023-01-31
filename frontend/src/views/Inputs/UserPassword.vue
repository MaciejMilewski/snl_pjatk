<template>
  <input type="password"
         placeholder="Hasło"
         class="uk-input"
         id="haslo"
         :value="password"
         @input="$emit('update:password',$event.target.value)"
  >
</template>

<script>
export default {
  name: "UserPasswordView",
  props: {
    password: {
      type: String,
    }
  }
}
</script>

<style scoped>

</style>