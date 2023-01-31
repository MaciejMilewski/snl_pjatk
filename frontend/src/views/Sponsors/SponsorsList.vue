<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class-heading]; cls: uk-animation-slide-left-medium; delay: false;">
    <div class="uk-container" >
      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-kontener-sponsorzy-naglowek" class="uk-h1" uk-scrollspy-class-heading> Sponsorzy</div>
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-6@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                <div id="sponsorzy" v-for="sponsor in sponsors" v-bind:key="sponsor.id"
                     uk-scrollspy="target: [uk-scrollspy-class-sponsor]; cls: uk-animation-scale-up; delay: 1000;">
                  <SponsorView uk-scrollspy-class-sponsor
                               :name="sponsor.name"
                               :image="sponsor.image"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import sponsors from '@/assets/temp_data/sponsors.json'
import SponsorView from '@/views/Sponsors/Sponsor'
import axios from "axios";
export default {
  name: "SponsorsList",
  components: {SponsorView},
  data(){
    return {
      sponsors: []
    }
  },
  created() {
    axios.get("http://localhost:8080/api/sponsors")
        .then(response => {
          let data = response.data.sponsors;
          let sponsorsData = [];
          if(data !== undefined)
            data.forEach((d) => {
              let dataUri = "data:image/png;base64," + d.image
              d.image = dataUri
              sponsorsData.push(d)
            })

          this.sponsors = sponsorsData
        })
  }
}
</script>

<style scoped>
#tlh-kontener-sponsorzy-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>