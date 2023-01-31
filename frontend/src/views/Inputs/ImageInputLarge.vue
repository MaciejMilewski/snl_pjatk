<template>
  <div class="uk-margin" uk-margin>
    <div
        id="tlh-obrazek-duzy"
        @click="selectImage"
        style="
          width: 200px;
          height: 200px;
          display: inline-block;
          background: #8debff;
          margin-right: 15px;">
      <div v-if="imageObject">
        <img class="tlh-ikona-duza" :src="imageObject.previewImage"/>
      </div>
      <div v-else>
        <img class="tlh-ikona-duza" :src="image.previewImage"/>
      </div>

    </div>
    <div uk-form-custom="target: true">
      <input type="file" ref="imageInput" @input="submitImage(this.$refs.imageInput)">
      <input class="uk-input uk-form-width-large" type="text" placeholder="Kliknij, aby wybrać zdjęcie" disabled>
    </div>
  </div>

</template>

<script>
import {reactive, toRefs} from "vue";
import UIkit from "uikit";

export default {
  name: "ImageInputLargeView",
  props: ['imageObject'],
  setup(props, context) {
    const state = reactive({
      image: {
        previewImage: '',
        imageName: '',
        imageInput: null,
        blob: null,
      }
    })

    function submitImage(ref) {

      const imgType = ref.files[0].type;
      if (!(imgType === 'image/png' || imgType === 'image/jpeg')) {
        UIkit.notification({
          message: 'Dozwolone są tylko pliki z rozszerzeniem .png lub .jpg',
          status: 'danger',
          pos: 'top-center',
          timeout: 10000
        });
      } else {
        let file = ref.files
        this.image.imageInput = ref
        //Sprawdzenie czy jest określona zmienna i czy zawiera element zerowy - nasze zdjęcie
        if (file && file[0]) {
          this.image.imageName = file[0].name
          //Wykorzystanie obiektu FileReader w celu załadowania zawartości zdjęcia
          let fileReader = new FileReader();
          fileReader.onload = fR => {
            this.image.previewImage = fR.target.result
          }
          fileReader.readAsDataURL(file[0])
          // context.emit('input', file[0])
          fetch(this.image.previewImage).then((res) => res.blob()).then(blob => this.image.blob = blob)
        }

        context.emit('update:imageObject', state.image)
      }
    }

    return {
      submitImage,
      ...toRefs(state)
    }
  },
  methods: {
    selectImage() {
      //Wywołanie imageInput
      this.$refs.imageInput.click();
    },
  }


}
</script>

<style>
@import "@/assets/styles/player/form/image_input/imageinput_large.less";
</style>