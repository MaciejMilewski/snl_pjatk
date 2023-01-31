<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class-heading]; cls: uk-animation-slide-left-medium; delay: false;">
    <div class="uk-container" >
      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-nadchodzacy-mecz-naglowek" class="uk-h1" uk-scrollspy-class-heading> Nadchodzący mecz</div>
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-1@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                <SingleUpcomingMatch :match="match" :htmlCountdown="htmlCountdown"/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import SingleUpcomingMatch from "@/views/UpcomingMatch/SingleUpcomingMatch";
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
export default {
  name: "UpcomingMatchView",
  components: {SingleUpcomingMatch},
  data(){
    return {
      match: null,
      htmlCountdown: null,
    }
  },
  created() {
    axios.get("http://localhost:8080/api/matches/upcoming").then(response => {
      this.match =  response.data;

      this.match.visitingImage = blobToDataURL(this.match.visitingTeam.image)
      this.match.homeImage = blobToDataURL(this.match.homeTeam.image)
      let matchTime = this.match.startTime[0]+this.match.startTime[1]+this.match.startTime[2]+this.match.startTime[3]+this.match.startTime[4]
          +this.match.startTime[5]+this.match.startTime[6]+this.match.startTime[7]+this.match.startTime[8]+this.match.startTime[9]+"T"
          +this.match.startTime[11]+this.match.startTime[12]+this.match.startTime[13]+this.match.startTime[14]+this.match.startTime[15]+this.match.startTime[16]
          +this.match.startTime[17]+this.match.startTime[18]+"+00:00"
      this.htmlCountdown = `<div uk-countdown="date: ${matchTime}">` + "<span class=\"uk-countdown-number uk-countdown-days\"></span>\n" +
          "        <span class=\"uk-countdown-separator\">:</span>\n" +
          "        <span class=\"uk-countdown-number uk-countdown-hours\"></span>\n" +
          "        <span class=\"uk-countdown-separator\">:</span>\n" +
          "        <span class=\"uk-countdown-number uk-countdown-minutes\"></span>\n" +
          "        <span class=\"uk-countdown-separator\">:</span>\n" +
          "        <span class=\"uk-countdown-number uk-countdown-seconds\"></span></div>"
    })
  }
}
</script>

<style scoped>
#tlh-nadchodzacy-mecz-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>