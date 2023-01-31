<template>
  <div class="page-loader">
    <img id="page-loader-image"  :src="require(`@/assets/logo.svg`)">
  </div>
</template>

<script>
export default {
  name: "PageLoader"
}
</script>

<style scoped lang="scss">
.page-loader {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: #a6efff;
  z-index: 999;
}




.page-loader {

  #page-loader-image {
    animation: zoom 0.5s ease-in-out infinite alternate;
    width: 500px;
  }

  @keyframes zoom {
    from {
      transform: scale(1);
    }
    to {
      transform: scale(1.2);
    }
  }


}
</style>