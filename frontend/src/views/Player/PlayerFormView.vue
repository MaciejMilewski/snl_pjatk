<template>
  <form @submit.prevent="submitForm" class="uk-form-large" style="padding-right: 0">

    <!--    Informacje podstawowe-->
    <div class="uk-form-controls">
      <ImageInputView v-model:image-object="reactiveForm.imageObjectValue"/>
    </div>

    <div class="uk-form-controls">

      <label for="informacje-podstawowe" class="uk-label">Informacje podstawowe</label>
      <div class="uk-grid" id="informacje-podstawowe">
        <div class="uk-width-1-2" id="imie">
          <PlayerNameView :id="viewIds.nameId" v-model:first-name="reactiveForm.nameValue"/>
        </div>
        <div class="uk-width-1-2">
          <PlayerSurnameView :id="viewIds.surnameId" v-model:last-name="reactiveForm.surnameValue"/>
        </div>
      </div>
    </div>
    <div class="uk-form-controls">
      <div class="uk-grid">
        <div class="uk-width-1-2">
          <PlayerNumberView :id="viewIds.playerNumberId" v-model:player-number.number="reactiveForm.playerNumberValue"/>
        </div>
        <div class="uk-width-1-2">
          <PlayerPositionView :id="viewIds.playerPositionId" v-model="reactiveForm.playerPositionValue"/>
        </div>
      </div>
    </div>

    <div class="uk-form-controls">
      <div class="uk-width">
        <label for="#data-urodzenia" class="uk-label">Urodziny</label>
        <PlayerBirthDateView :id="viewIds.birthDateId" v-model:birth-date.lazy="reactiveForm.birthdateValue"/>

      </div>

      <div class="uk-width">
        <PlayerBirthPlaceView :id="viewIds.birthPlaceId" v-model:birthplace="reactiveForm.birthplaceValue"/>
      </div>
    </div>

    <div class="uk-form-controls">
      <label for="informacje-dodatkowe" class="uk-label">Informacje dodatkowe</label>
      <div class="uk-grid" id="informacje-dodatkowe">
        <div class="uk-width-1-2">
          <PlayerWeightView :id="viewIds.weightId" v-model:weight.number="reactiveForm.weightValue"/>

        </div>
        <div class="uk-width-1-2">
          <PlayerStickView :id="viewIds.stickId" v-model="reactiveForm.stickValue"/>

        </div>
        <div class="uk-width uk-margin">
          <PlayerTeamIdView :id="viewIds.teamId" v-model="reactiveForm.teamValue"/>
        </div>
      </div>
    </div>
    <div v-if="editContext === false">
      <button class="uk-button uk-button-default uk-width-1-1 uk-margin">Dodaj zawodnika</button>
    </div>
    <div v-else>
      <button type="submit" class="uk-button uk-button-default uk-width-1-1 uk-margin">Aktualizuj zawodnika</button>
    </div>

  </form>
</template>

<script>
//Importowanie mikrowidoków
import PlayerBirthDateView from "@/views/Inputs/BirthDate";
import PlayerBirthPlaceView from "@/views/Inputs/BirthPlace";
import PlayerWeightView from "@/views/Inputs/Weight";
import PlayerNumberView from "@/views/Inputs/PlayerNumber";
import PlayerSurnameView from "@/views/Inputs/Surname";
import PlayerNameView from "@/views/Inputs/Name";
import PlayerStickView from "@/views/Inputs/Stick";
import PlayerTeamIdView from "@/views/Inputs/TeamId";
import PlayerPositionView from "@/views/Inputs/Position";
import {reactive, toRefs, computed} from "vue";
import ImageInputView from "@/views/Inputs/ImageInput";

export default {
  name: 'PlayerFormView',
  emits: ['submitPlayerForm'],
  props: ['player', 'editContext'],
  setup(props, context) {
    const initialName = String('')
    const state = reactive({
      form: {
        nameValue: initialName,
        surnameValue: '',
        playerNumberValue: parseInt(''),
        playerPositionValue: '',
        birthdateValue: '',
        birthplaceValue: '',
        weightValue: parseInt(''),
        stickValue: '',
        teamValue: null,
        imageObjectValue: null,
      },
      formPlayer: {
        id: props.player.id,
        nameValue: props.player.name,
        surnameValue: props.player.surname,
        playerNumberValue: parseInt(props.player.playerNumber),
        playerPositionValue: props.player.position,
        birthdateValue: props.player.birthDate,
        birthplaceValue: props.player.birthPlace,
        weightValue: parseInt(props.player.weight),
        stickValue: props.player.stick,
        teamValue: {
          image: props.player.teamImage,
          name: props.player.teamName
        },
        imageObjectValue: {
          previewImage: props.player.image
        },
      }
    })
    const reactiveForm = computed(function () {
      if (props.player.name) {
        return state.formPlayer
      } else
        return state.form
    })

    // Wysyłanie fomularza poprzez emit
    function submitForm() {
      if (props.player.name)
        context.emit('submitPlayerForm', reactiveForm.value, 'update')
      else
        context.emit('submitPlayerForm', reactiveForm.value, 'post', props.player.id)
    }


    return {
      reactiveForm,
      ...toRefs(state),
      submitForm,
    }
  },
  data() {
    return {
      isAddingNewPlayer: true,
      viewIds: {
        nameId: 'zawodnik-imie',
        surnameId: 'zawodnik-nazwisko',
        playerNumberId: 'zawodnik-numer',
        playerPositionId: 'zawodnik-pozycja',
        birthDateId: 'zawodnik-data-urodzenia',
        birthPlaceId: 'zawodnik-miejsce-urodzenia',
        weightId: 'zawodnik-waga',
        stickId: 'zawodnik-kij',
        teamId: 'zawodnik-id-zespolu',
      }
    }
  }
  ,
  components: {
    ImageInputView,
    PlayerTeamIdView,
    PlayerStickView,
    PlayerNameView,
    PlayerSurnameView,
    PlayerNumberView,
    PlayerWeightView,
    PlayerBirthPlaceView,
    PlayerBirthDateView,
    PlayerPositionView
  }
  ,
}
</script>

<style>
/* Notifications */
@import "@/assets/styles/notifications/notifications.less";

/* Player Position && Player Stick  */
@import "@/assets/styles/player/multiselect/multiselect.css";

/* Form styling */
@import "@/assets/styles/player/form/form.css";

</style>