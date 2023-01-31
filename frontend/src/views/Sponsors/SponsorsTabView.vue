<template>
  <div>
    <div uk-grid id="tlh-sponsorzy-grid">
      <div class="uk-width-auto@m" id="tlh-sponsorzy-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-sponsorzy; animation: uk-animation-fade">
          <li @click="incrementKeyParent">
            <a class="sponsors-tab-left" >
              <span uk-icon="icon: image"/>
              Sponsorzy
            </a>
          </li>
          <li>
            <a class="sponsors-tab-left" href="#">
              <span uk-icon="icon: plus"/>Dodaj sponsora
            </a>
          </li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-sponsorzy" class="uk-switcher">
          <li>
            <SponsorsListAdmin
                @incrementKey="incrementKeyParent"
                :sponsors="sponsors"/>
          </li>
          <li>
            <SponsorFormComponent/>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import SponsorsListAdmin from "@/views/Sponsors/Admin/SponsorsListAdmin";
import SponsorFormComponent from "@/components/Sponsor/SponsorFormComponent";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";

export default {
  name: "SponsorsTabView",
  components: {SponsorFormComponent, SponsorsListAdmin},
  data() {
    return {
      sponsors: [],
    }
  },
  created() {
    axios.get("http://localhost:8080/api/sponsors")
        .then(response => {
          const data = response.data.sponsors;
          if (data !== undefined)
            data.forEach((d) => {
              if (d.image !== null) {
                d.image = blobToDataURL(d.image)
              }
            })
          this.sponsors = data;
        })
  },
  methods: {
    incrementKeyParent() {
      this.$emit('incrementKey')
    }
  }
}
</script>

<style scoped>
#tlh-sponsorzy-grid {
  background-color: #8debff;
  border-top-left-radius: 10px;
}

#tlh-sponsorzy-uk-width-auto {
  background-color: #a6efff;
  border-top-left-radius: 10px;
}

.uk-tab {
  background-color: #a6efff;
  padding: 10px;

  height: max-content !important;
}

.uk-tab > * > a:hover {
  color: #006394;
  font-weight: bold;
}

.uk-tab > .uk-active > a {
  font-weight: bold;
  color: #006394 !important;
  border-color: #006394;
  border-right-width: 5px;
}
</style>