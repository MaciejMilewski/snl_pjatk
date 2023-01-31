<template>
  <div id="submenu-start"
       uk-scrollspy="target: [uk-scrollspy-menu]; cls: uk-animation-fade; delay: 100;">
    <div id="menu-start" uk-scrollspy-menu>
      <ul class="uk-flex-center" uk-tab="connect: #component-tab-left; animation: uk-animation-slide-right">
        <li><a @click="scrollTo('nadchodzacy-mecz')" href="/#nadchodzacy-mecz">Nadchodzący mecz </a></li>
        <li><a @click="scrollTo('sponsorzy')" href="#">Sponsorzy</a></li>
      </ul>
    </div>
  </div>
  <NewsView/>
  <div ref="nadchodzacy-mecz">
    <UpcomingMatch/>
  </div>
  <div ref="sponsorzy">
    <SponsorsList/>
  </div>


</template>

<script>
import SponsorsList from "@/views/Sponsors/SponsorsList";
import NewsView from "@/views/News/News";
import UpcomingMatch from "@/views/UpcomingMatch/UpcomingMatch";

export default {
  name: "StartComponent",
  components: {NewsView, SponsorsList, UpcomingMatch},
  methods: {
    scrollTo(refName) {
      var element = this.$refs[refName]
      element.scrollIntoView({behavior: 'smooth'})
    }
  }
}
</script>

<style scoped>
#submenu-start {
  margin-bottom: 15px;
}

#menu-start > ul > li > a {
  font-weight: bold !important;
  border-bottom: 5px solid #006394;
  padding: 15px;
}

#menu-start > ul > li.uk-active > a {
  color: #006394;
}

.uk-tab > * > a:hover {
  color: #006394;
}
</style>