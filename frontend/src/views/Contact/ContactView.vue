<template>
  <div class="uk-section uk-margin-left uk-margin-right">
    <h1 class="uk-text-center"> Skontaktuj się z nami w celu stworzenia konta w serwisie.</h1>
    <div class="uk-child-width-1-3@m" uk-grid>
      <div>
        <div class="uk-card uk-card-default">
          <div class="uk-card-media-left uk-cover-container">
            <img :src="require(`@/assets/contact/grzegorz.png`)"  height="300" alt="">
          </div>
          <div class="uk-card-body">
            <h3 class="uk-card-title">Grzegorz Cytawa</h3>
            <p>Backend developer</p>
            <p>Adres email: s19617@pjwstk.edu.pl</p>
          </div>
        </div>
      </div>

      <div>
        <div class="uk-card uk-card-default">
          <div class="uk-flex-last@s uk-card-media-right uk-cover-container">
            <img :src="require(`@/assets/contact/mikolaj.png`)"  height="300" alt="">
          </div>
          <div class="uk-card-body">
            <h3 class="uk-card-title">Mikołaj Herzberg</h3>
            <p>Frontend developer</p>
            <p>Adres email: s18551@pjwstk.edu.pl</p>
          </div>

        </div>
      </div>

      <div>
        <div class="uk-card uk-card-default">
          <div class="uk-card-media-top">
            <img :src="require(`@/assets/contact/maciej.png`)"  height="300" alt="">
          </div>
          <div class="uk-card-body">
            <h3 class="uk-card-title">Maciej Milewski</h3>
            <p>Backend developer</p>
            <p>Adres email: s17947@pjwstk.edu.pl </p>
          </div>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "ContactView"
}
</script>

<style scoped>

</style>