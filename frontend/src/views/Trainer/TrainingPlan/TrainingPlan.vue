<template>
  <div v-if="imageSchedule !== null">
    <h1 id="tlh-plan-nazwa-naglowek">{{ description }}</h1>
    <button class="uk-button uk-button-default uk-width-1-1 uk-margin" @click="removeSchedule">Usuń istniejący plan
      treningowy
    </button>
    <img :src="imageSchedule" style="width: 1000px"/>
  </div>
  <h1 id="tlh-plan-dodaj-nowy-naglowek">Dodaj nowy plan treningowy</h1>
  <image-input-large-view v-model:image-object="reactiveForm.imageObjectValue"/>
  <name-view v-model:name="reactiveForm.name"/>
  <button id="przycisk-zapisz-plan-treningowy" class="uk-button uk-button-default uk-width-1-1 uk-margin"
          @click="saveTrainingPlan">Dodaj plan treningowy
  </button>
</template>

<script>
import ImageInputLargeView from "@/views/Inputs/ImageInputLarge";
import {computed, reactive} from "vue";
import NameView from "@/views/Inputs/NameView";
import axios from "axios";
import UIkit from "uikit";
import dataURLtoBlob from "@/assets/scripts/dataURLtoBlob";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import {mapGetters} from "vuex";

export default {
  name: "TrainingPlan",
  emits: ['incrementKey'],
  components: {NameView, ImageInputLargeView},
  computed: {...mapGetters({User: "StateUser"})},
  data() {
    return {
      imageSchedule: null,
      description: null,
      id: null,
    }
  },
  setup() {
    const state = reactive({
      form: {
        imageObjectValue: null,
        name: null,
      }
    })
    const reactiveForm = computed(function () {
      return state.form
    })
    return {
      reactiveForm
    }
  },
  created() {
    axios.get("http://localhost:8080/api/teams/"+this.User.userInfo.teamId+"/schedule",{
      headers: {
        'Authorization': 'Bearer '+this.User.userInfo.token
      }
    }).then((response) => {
      this.imageSchedule = blobToDataURL(response.data.image)
      this.description = response.data.description
      this.id = response.data.id
    })
  },
  methods: {
    removeSchedule(){
      axios.delete("http://localhost:8080/api/schedules/"+this.id, {
        headers: {
          'Authorization': 'Bearer '+this.User.userInfo.token,
        }
      }).then(function(){
        UIkit.notification({
          message: "Pomyślnie usunięto plan treningowy.",
          status: "success",
          pos: 'top-center',
          timeout: 5000
        })
      })
      this.$emit('incrementKey');
    },
    saveTrainingPlan() {
      let canAddSchedule = true;
      if (this.imageSchedule !== null) {
        canAddSchedule = false;
        UIkit.notification({
          message: "Aby dodać nowy plan treningowy, należy usunąć istniejący.",
          status: "warning",
          pos: 'top-center',
          timeout: 5000
        })
      }
      let name = this.reactiveForm.name;
      let imageBlob = dataURLtoBlob(this.reactiveForm.imageObjectValue.previewImage)
      if (canAddSchedule) {
        axios.post("http://localhost:8080/api/teams/1/schedule", {
          description: this.reactiveForm.name,
          image: imageBlob
        }, {
          headers: {
            'Authorization': 'Bearer '+this.User.userInfo.token,
            'Content-Type': `multipart/form-data`,
          }
        }).then(function () {
              UIkit.notification({
                message: "Pomyślnie dodano nowy plan: " + name,
                status: 'success',
                pos: 'top-center',
                timeout: 10000

              })
            }
        )
        this.$emit('incrementKey');
      }

    }
  }

}
</script>

<style scoped>
#tlh-plan-nazwa-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
#tlh-plan-dodaj-nowy-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}
</style>