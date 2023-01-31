<template>
  <div></div>
</template>
<script>
import UIkit from 'uikit';
export default {
  name: "LogoutComponent",
  mounted() {
    UIkit.notification({
      message: 'Pomyślnie wylogowano.',
      status: 'success',
      pos: 'top-center',
      timeout: 10000
    });
    this.$router.push('/')
  }
}
</script>

<style scoped>

</style>