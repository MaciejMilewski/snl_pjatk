<template>
  <input type="text"
         placeholder="Autor"
         class="uk-input"
         id="autor"
         :value="author"
         @input="$emit('update:author',$event.target.value)"
  >
</template>

<script>
export default {
  name: "AuthorView",
  props: {
    author: {
      type: [String]
    }
  }
}
</script>

<style scoped>

</style>