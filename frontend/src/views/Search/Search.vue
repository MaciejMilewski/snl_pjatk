<template>
  <input class="uk-input uk-form-width-small" type="text"
         placeholder="Wyszukaj..." uk-toggle="target: #wyszukiwarka" @click="keyValue++">
  <div id="wyszukiwarka" class="uk-position-center wyszukiwarka" uk-modal="center: true; esc-close: true; bg-close: false;">
    <div id="wyszukiwarka-dialog" class="uk-modal-dialog uk-modal-body">
      <span style="margin-top: 10px"></span>
      <button id="przycisk-zamkniecie" class="uk-modal-close-outside uk-close-large" type="button" uk-close></button>
      <h2 class="uk-modal-title uk-text-center">Wyszukiwarka

          <span id="wyszukiwarka-ikona" uk-icon="icon: search; ratio: 2;"/>

      </h2>
      <form>
        <SearchMultiSelect v-model="reactiveForm.searchValue" :key="keyValue" @closeModalWindow="closeModalWindow"/>
      </form>
    </div>
  </div>
</template>

<script>
import {reactive, toRefs, computed} from "vue";
import UIkit from "uikit";
import SearchMultiSelect from "@/views/Search/SearchMultiSelect";

export default {
  name: "SearchView",
  components: {SearchMultiSelect},
  data() {
    return {
      keyValue: 0
    }
  },
  setup() {
    function closeModalWindow(){
      const modal = UIkit.modal("#wyszukiwarka");
      setTimeout(() => {
        modal.cls = "uk-close"
        modal.hasAnimation = true;
        modal.toggle();
      },200)
    }
    const state = reactive({
      form: {
        searchValue: null,
      }
    })
    const reactiveForm = computed(function () {
      return state.form
    })
    return {
      closeModalWindow,
      reactiveForm,
      ...toRefs(state),
    }
  }


}
</script>

<style scoped>

#wyszukiwarka {
  height: 500px;

  background: #006394;
  border-radius: 25px;
  z-index: 999;
  border: 5px solid #009fb6;
}

.uk-modal-title {
  background: #a6efff;
  padding: 10px;
  border-top-right-radius: 25px;
  border-bottom-left-radius: 25px;
  color: #009fb6;
}

#wyszukiwarka-dialog {
  background: #006394;
}

#wyszukiwarka-ikona {
  margin-left: 20px;
}

#przycisk-zamkniecie {
  padding: 0;
  margin-top: 15px
}

</style>