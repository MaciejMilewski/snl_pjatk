<template>
  <div class="uk-visible@l">
    <NavbarView/>
  </div>
  <div class="uk-hidden@l">
    <NavbarMobileView/>
  </div>
</template>

<script>
import NavbarView from "@/views/Navbar/NavbarView";
import NavbarMobileView from "@/views/Navbar/NavbarMobileView";
export default {
  name: "NavbarComponent",
  components: {NavbarView, NavbarMobileView},
  computed: {
    isSearching: {
      get() {
        return this.isSearching
      },
      set(value){
        this.isSearching = value
      }
    }
  }
}
</script>

<style scoped>

</style>