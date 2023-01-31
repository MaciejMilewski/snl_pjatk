<template>
  <div class="uk-container uk-align-center">
    <a href="#panel" id="przycisk-powrot" class="uk-button-default">
      <span uk-icon="icon: arrow-left; ratio: 2"/>Wróć
    </a>
<AddNewUserView :edit-context="true" :user="user" />
  </div>
</template>

<script>
import AddNewUserView from "@/views/Users/AddNewUserView";
export default {
  name: "EditUser",
  components: {AddNewUserView},
  created() {

  }
}
</script>

<style scoped>
#przycisk-powrot {
  font-size: 20px !important;
  border: none !important;
  padding: 10px !important;
  margin-bottom: 10px;
}
</style>