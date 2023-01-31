<template>
<h1 id="tlh-strategia-narysuj-cwiczenie-naglowek" >Narysuj ćwiczenie</h1>

  <div @click="clear" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px; border: 5px solid black"><span  uk-icon="icon: plus;ratio: 5" style="color: #a6efff;"/></div>
  <div>
    <VueSignature ref="signature" :sig-option="option" :h="'700px'"  :default-url="url" :key="keyValue" :id="'cwiczenie'"
    :clear-on-resize="true"
    />
  </div>

  <div @click="selectColor('red')" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px;background-color: red; border: 5px solid black"><span uk-icon="icon: pencil;ratio: 5" style="color: #a6efff;"/></div>
  <div @click="selectColor('green')" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px;background-color: green; border: 5px solid black"><span uk-icon="icon: pencil;ratio: 5" style="color: #a6efff;"/></div>
  <div @click="selectColor('yellow')" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px;background-color: yellow; border: 5px solid black"><span uk-icon="icon: pencil;ratio: 5" style="color: #a6efff;"/></div>
  <div @click="selectColor('orange')" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px;background-color: orange; border: 5px solid black"><span uk-icon="icon: pencil;ratio: 5" style="color: #a6efff;"/></div>
  <div @click="selectColor('white')" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px;background-color: white; border: 5px solid black"><span uk-icon="icon: pencil;ratio: 5" style="color: #a6efff;"/></div>
  <div @click="selectColor('black')" class='uk-margin uk-margin-left uk-display-inline-block' style="width: 100px;height:100px;background-color: black; border: 5px solid black"><span uk-icon="icon: pencil;ratio: 5" style="color: #a6efff;"/></div>
  <NameView v-model:name="nameValue"/>
  <button  class="uk-button uk-button-default uk-width-1-1 uk-margin" @click="save">Zapisz ćwiczenie</button>
</template>

<script>
import VueSignature from 'vue-signature'
import NameView from "@/views/Inputs/NameView";
import dataURLtoBlob from "@/assets/scripts/dataURLtoBlob";
import axios from "axios";
import UIkit from "uikit";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {mapActions, mapGetters} from "vuex";
export default {
  name: "StrategyView",
  components: {NameView, VueSignature},
  computed: {...mapGetters({User: "StateUser"})},
  emits: ['incrementKey'],
  data(){
    return {
      nameValue: '',
      url: require(`@/assets/hockey_field.jpg`),
      keyValue: 0,
      option :{
        penColor: "rgb(0,0,0)"
      }
    }
  },
  created() {
    this.url = require(`@/assets/hockey_field.jpg`);
  },
  methods: {
    ...mapActions(["SetStrategies"]),
    clear(){
      this.url = require(`@/assets/hockey_field.jpg`);
      this.keyValue++;
    },
    selectColor(color){
      var dataUrl = this.$refs.signature.save();
      if(color === 'red'){
        this.url = dataUrl;
        this.option.penColor = "rgb(255,0,0)";
        this.keyValue++;
      }
      if(color === 'green'){
        this.url = dataUrl;
        this.option.penColor = "rgb(0,128,0)";
        this.keyValue++;
      }
      if(color === 'yellow'){
        this.url = dataUrl;
        this.option.penColor = "rgb(255,255,0)";
        this.keyValue++;
      }
      if(color === 'orange'){
        this.url = dataUrl;
        this.option.penColor = "rgb(255,165,0)";
        this.keyValue++;
      }
      if(color === 'white'){
        this.url = dataUrl;
        this.option.penColor = "rgb(255,255,255)";
        this.keyValue++;
      }
      if(color === 'black'){
        this.url = dataUrl;
        this.option.penColor = "rgb(0,0,0)";
        this.keyValue++;
      }
      //

    },
    save(){
      var jpeg = this.$refs.signature.save('image/jpeg');
      var blob = dataURLtoBlob(jpeg);
      var name = this.nameValue
      axios.post("http://localhost:8080/api/teams/1/strategies", {
        name: this.nameValue,
        image: blob,
      },{
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
          'Content-Type': `multipart/form-data`,
        }
      }).then(function () {
        UIkit.notification({
          message: "Dodano nową strategię: " + name,
          status: "success",
          pos: "top-center",
          timeout: 5000
        })
      })
      setTimeout(()=>{
        let strategiesArray = []
        axios.get("http://localhost:8080/api/teams/1/strategies").then(function (result) {
          result.data.forEach((data) => {
            data.image = blobToDataURL(data.image)
            strategiesArray.push(data)
          })
        })
        this.SetStrategies(strategiesArray)
        this.$emit('incrementKey')
      },1000)
    }
  }
}
</script>

<style scoped>
#tlh-strategia-narysuj-cwiczenie-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>