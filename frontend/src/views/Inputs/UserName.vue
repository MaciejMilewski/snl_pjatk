<template>
  <input type="text"
         placeholder="Login"
         class="uk-input"
         id="nazwa-uzytkownika"
         :value="username"
         @input="$emit('update:username',$event.target.value)
           "
  >
</template>

<script>

export default {
  name: "UserNameView",
  props: {
    username: {
      type: String
    }
  },
}
</script>