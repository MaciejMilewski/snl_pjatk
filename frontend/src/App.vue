<template>
  <NavbarComponent/>
</template>

<style>
@import "@/assets/styles/uikit/uikit.css";

#app {
  font-family: Lato, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
<script>
import NavbarComponent from "@/components/Navbar/NavbarComponent";
export default {
  name: "AppTLH",
  components: {NavbarComponent},
}
</script>
