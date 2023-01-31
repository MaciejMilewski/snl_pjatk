<template>
  <tr>
    <td>{{ id }}</td>
    <td>{{ email }}</td>
    <td>{{ username }}</td>
  </tr>
</template>

<script>
export default {
  name: "SingleUserView",
  props: ['id','email','username'],

}
</script>

<style scoped>

</style>