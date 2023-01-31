<template>
  <input type="text"
         placeholder="Tytuł artykułu"
         class="uk-input"
         id="tytul"
         :value="title"
         @input="$emit('update:title',$event.target.value)
           "
  >
</template>

<script>
export default {
  name: "TitleView",
  props: {
    title: {
      type: [String]
    }
  }
}
</script>

<style scoped>

</style>