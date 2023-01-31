<template>
  <input type="text"
         :placeholder="placeholder"
         className="uk-input"
         id="nazwa"
         :value="name"
         @input="$emit('update:name',$event.target.value)
           "
  >
</template>

<script>
export default {
  name: "InputName",
  props: {
    name: {
      type: [String]
    },
    placeholder: {
      type: [String]
    },
  }
}
</script>

<style scoped>
#nazwa {
  margin-top: 10px;
}
</style>