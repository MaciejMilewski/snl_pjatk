<template>
  <input type="text"
         placeholder="Nazwa"
         className="uk-input"
         id="nazwa"
         :value="name"
         @input="$emit('update:name',$event.target.value)
           "
  >
</template>

<script>
export default {
  name: "TeamNameView",
  props: {
    name: {
      type: [String]
    }
  }
}
</script>

<style scoped>

</style>