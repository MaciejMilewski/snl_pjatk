<template>
  <div v-if="isRegister === false">
    <LoginFormView/>
  </div>
</template>

<script>
import LoginFormView from "@/views/Login/LoginFormView";
export default {
  name: "LoginForm",
  components: {LoginFormView},
  data() {
    return {
      isRegister: false
    }
  }
}
</script>

<style scoped>
@import '@/assets/styles/login/loginform.less';
</style>