<template>
  <div v-if="player !== {}">
    <PlayerFormView :edit-context="false" :player="player" @submitPlayerForm="submitPlayerForm"/>
  </div>
  <div v-else>
    <PageLoader/>

  </div>

</template>

<script>


import PlayerFormView from "@/views/Player/PlayerFormView";
import dataURLtoBlob from "@/assets/scripts/dataURLtoBlob";
import PageLoader from "@/components/Loader/PageLoader";
import axios from "axios";
import UIkit from "uikit";
import {mapGetters} from "vuex";

export default {
  name: "PlayerForm",
  props: ['player'],
  components: {PlayerFormView, PageLoader},
  computed: {...mapGetters({User: "StateUser"})},
  methods: {
    submitPlayerForm(form, method) {
      //Obsługa formularza

      //Usunięcie spacji
      form.nameValue.trim()
      form.birthplaceValue.trim()
      form.surnameValue.trim()

      //Dzisiejsza data
      let today = new Date().toISOString().slice(0, 10)

      //Czy data została wybrana?
      if (!form.birthdateValue.length) {
        UIkit.notification({
          message: '[Błąd podczas wysyłania formularza] Nie podano daty lub jest nieprawidłowa.',
          status: 'danger',
          pos: 'top-center',
          timeout: 10000
        });
      } else {
        //Czas przyszły
        if (today < form.birthdateValue) {
          UIkit.notification({
            message: '[Błąd podczas wysyłania formularza] Data nie może być w czasie przyszłym.',
            status: 'danger',
            pos: 'top-center',
            timeout: 10000
          });
        } else {
          //Czas teraźniejszy lub przeszły
          const yearFromBirthdate = parseInt(
              form.birthdateValue[0]
              + form.birthdateValue[1]
              + form.birthdateValue[2]
              + form.birthdateValue[3]);
          const yearFromToday = parseInt(
              today[0]
              + today[1]
              + today[2]
              + today[3]
          )
          //Niech maksymalna różnica wynosi 120 lat
          if (yearFromToday - yearFromBirthdate > 120) {
            UIkit.notification({
              message: '[Błąd podczas wysyłania formularza] Data jest za niska.' +
                  ' Maksymalna różnica daty dzisiejszej do przeszłej wynosi 120 lat.',
              status: 'danger',
              pos: 'top-center',
              timeout: 10000
            });
          } else {
            //Data jest prawidłowa
            //Czy podano imię
            if (!form.nameValue) {
              UIkit.notification({
                message: '[Błąd podczas wysyłania formularza] Nie podano imienia.',
                status: 'danger',
                pos: 'top-center',
                timeout: 10000
              });
            } else {
              //Czy podano nazwisko
              if (!form.surnameValue) {
                UIkit.notification({
                  message: '[Błąd podczas wysyłania formularza] Nie podano nazwiska.',
                  status: 'danger',
                  pos: 'top-center',
                  timeout: 10000
                });
              } else {
                //Czy podano miejsce urodzenia
                if (!form.birthplaceValue) {
                  UIkit.notification({
                    message: '[Błąd podczas wysyłania formularza] Nie podano miejsca urodzenia.',
                    status: 'danger',
                    pos: 'top-center',
                    timeout: 10000
                  });
                } else {
                  //Czy wybrano drużynę
                  if (!form.teamValue) {
                    UIkit.notification({
                      message: '[Błąd podczas wysyłania formularza] Nie wybrano drużyny.',
                      status: 'danger',
                      pos: 'top-center',
                      timeout: 10000
                    });
                  } else {
                    //Blob image
                    let blob = null;
                    if (typeof form.imageObjectValue === 'object') {
                      if (form.imageObjectValue !== null && form.imageObjectValue !== 'data:image/png;base64,null') {
                        blob = dataURLtoBlob(form.imageObjectValue.previewImage);
                      }

                    } else {
                      blob = dataURLtoBlob(form.imageObjectValue)
                    }

                    //Dane są prawidłowe
                    //const player = new FormData();

                    // Player post
                    if (method === 'post')
                      axios.post('http://localhost:8080/api/teams/' + form.teamValue.id + '/players', {
                            name: form.nameValue,
                            surname: form.surnameValue,
                            playerNumber: parseInt(form.playerNumberValue),
                            position: form.playerPositionValue.position,
                            birthDate: form.birthdateValue,
                            birthPlace: form.birthplaceValue,
                            weight: parseInt(form.weightValue),
                            stick: form.stickValue.stick,
                            image: blob,
                            team: form.teamValue.id
                          }, {
                            headers: {
                              // Overwrite Axios's automatically set Content-Type
                              'Authorization': 'Bearer ' + this.User.userInfo.token,
                              'Content-Type': 'multipart/form-data'
                            }
                          }
                      )
                          .then(function (response) {
                            if (response.status === 201) {
                              UIkit.notification({
                                message: "Dodano nowego zawodnika! [" + form.nameValue + " " + form.surnameValue + "]",
                                status: 'success',
                                pos: 'top-center'
                              })
                              return true;
                            }
                          })
                          .catch((error) => {
                            console.log(error)
                            UIkit.notification({
                              message: error.response.data.message,
                              status: 'danger',
                              pos: 'top-center'
                            })
                            return false;
                          })
                    // Player update
                    if (method === 'update') {
                      axios.put('http://localhost:8080/api/players/' + form.id, {
                            name: form.nameValue,
                            surname: form.surnameValue,
                            playerNumber: parseInt(form.playerNumberValue),
                            position: form.playerPositionValue.position,
                            birthDate: form.birthdateValue,
                            birthPlace: form.birthplaceValue,
                            weight: parseInt(form.weightValue),
                            stick: form.stickValue.stick,
                            image: blob
                          }, {
                            headers: {
                              // Overwrite Axios's automatically set Content-Type
                              'Authorization': 'Bearer ' + this.User.userInfo.token,
                              'Content-Type': 'multipart/form-data'
                            }
                          }
                      )
                          .then(function (response) {
                            if (response.status === 200) {
                              UIkit.notification({
                                message: "Edytowano zawodnika! [" + form.nameValue + " " + form.surnameValue + "]",
                                status: 'success',
                                pos: 'top-center'
                              })
                              return true;
                            }
                          })
                          .catch((error) => {
                            UIkit.notification({
                              message: error.response.data.message,
                              status: 'danger',
                              pos: 'top-center'
                            })
                            return false;
                          })
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  },

}
</script>