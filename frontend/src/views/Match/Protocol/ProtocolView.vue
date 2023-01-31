<template>
  <div v-if="this.match">

    <Vue3Html2pdf
        :show-layout="false"
        :float-layout="true"
        :enable-download="false"
        :preview-modal="true"
        filename="protokół"
        :pdf-quality="1"
        :manual-pagination="false"
        :paginate-elements-by-height="700"
        pdf-format="a4"
        pdf-orientation="portrait"
        pdf-content-width="800px"
        ref="html2Pdf"
    >
      <template v-slot:pdf-content>
        <div id="tlh-protocol" style="margin-top: 15px; border: 1px solid grey;">

          <!--  Nagłówek - informacje  -->
          <table width="950" id="tlh-protocol-info-top" border="0" cellspacing="0" cellpadding="0"
                 class="uk-table uk-table-striped">
            <tbody>
            <tr>
              <td colspan="1">
                <img :src="require(`@/assets/logo.png`)" alt="Polski Hokej" width="350"
                     style="margin-left: 10px;margin-top: 25px;">
              </td>
              <td width="300" align="center">
                <h2 style="font-weight: bold">{{ leagueName }}</h2>
                <h2 style="font-weight: bold">PROTOKÓŁ ZAWODÓW</h2>
              </td>
              <td width="300" style="padding-top: 0px;" align="center">
                <table class="uk-table uk-table-striped" style="width: 250px; text-align: center;">
                  <tbody>
                  <tr>
                    <td style="border-left: 1px solid grey; border-right: 1px solid grey; border-bottom: 1px solid #111; height: 100px;">
                    </td>
                  </tr>
                  <tr>
                    <td style="border: 1px solid #111;"><small>SŁUŻBA MEDYCZNA</small></td>
                  </tr>
                  </tbody>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="4" class="tlh-border">
                <table width="700" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                  <tbody>
                  <tr style="text-align: center;">
                    <td width="28" class="tlh-right-max" rowspan="2">DATA</td>
                    <td width="100" class="tlh-border-right uk-text-center" rowspan="1">{{ match.date }}</td>
                    <td width="50" class="tlh-right-max" rowspan="2">MIEJSCE&nbsp;</td>
                    <td width="190" class="tlh-border-right uk-text-center" rowspan="2">{{ match.place }}</td>
                    <td width="30" class="tlh-right-max" rowspan="2">START&nbsp;</td>
                    <td class="tlh-border-right uk-text-center" rowspan="2"> {{ match.time }} &nbsp;</td>
                    <td width="50" class="tlh-right-max" rowspan="2">KONIEC&nbsp;</td>
                    <td width="30" class="tlh-border-right" rowspan="2"> {{ match.timeEnd }}&nbsp;</td>
                    <td width="65" class="tlh-right-max" rowspan="2">LIGA&nbsp;</td>
                    <td width="50" class="tlh-border-right" rowspan="2"> {{ leagueName }} &nbsp;</td>
                    <td width="65" class="tlh-right-max uk-text-center" rowspan="2">NR MECZU&nbsp;</td>
                    <td width="50" rowspan="2"> {{ match.id }}</td>
                    <td width="65" class="tlh-right-max" rowspan="2">WIDZÓW&nbsp;</td>
                    <td width="50" rowspan="2"> {{ match.attendance }}</td>
                  </tr>
                  </tbody>
                </table>
              </td>
            </tr>
            </tbody>
          </table>
          <!--  Gospodarze   -->
          <table width="700" id="gospodarze" border="0" cellspacing="0" cellpadding="0"
                 class="uk-table uk-table-striped">
            <tbody>
            <tr>
              <td width="230" valign="top" class="tlh-border-right-top-left">
                <table width="200" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                  <tbody>
                  <tr>
                    <td width="80" class="tlh-right-max uk-text-center"><strong>A - GOSPODARZE </strong></td>
                    <td class="uk-text-center"><strong> {{ match.homeTeam.name }} </strong></td>
                  </tr>
                  </tbody>
                </table>
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr>
                    <td width="15" align="center" class="tlh-right-top-bottom-dark">Nr</td>
                    <td class="tlh-right-top-bottom-dark">Imię i Nazwisko</td>
                    <td width="15" align="center" class="tlh-right-top-bottom-dark">Poz</td>
                    <td width="15" align="center" class="tlh-top-bottom-dark-max">T/N</td>
                  </tr>

                  <!--   Renderowanie listy zawodników gospodarzy         -->
                  <tr v-for="homePlayer in homePlayers" :key="homePlayer.id">
                    <td align="center" class="tlh-right-bottom">{{ homePlayer.playerNumber }}</td>
                    <td class="tlh-right-bottom">{{ homePlayer.name }} {{ homePlayer.surname }}</td>
                    <td align="center" class="tlh-right-bottom">{{ homePlayer.position }}</td>
                    <td align="center" class="tlh-right-bottom">T</td>
                  </tr>

                  </tbody>
                </table>
              </td>
              <td width="280" valign="top" class="tlh-border-right-top">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr style="height: 13px;">
                    <td colspan="6" align="center" class="tlh-bottom-max">BRAMKI</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-right-bottom_dark">G</td>
                    <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                    <td align="center" class="tlh-right-bottom_dark">S</td>
                    <td align="center" class="tlh-right-bottom_dark">A1</td>
                    <td align="center" class="tlh-right-bottom_dark">A2</td>
                    <td align="center" class="tlh-right-bottom_dark2">SM</td>
                  </tr>

                  <!--     Akcja meczowa - GOAL       -->
                  <tr v-for="action in actions" :key="action.id">
                    <td v-if="action.type === 'GOAL'  && action.teamType === 'homeTeam'" align="center"
                        class="tlh-right-bottom">{{ action.id }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.gameMinute }}:{{ action.gameSecond }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.scorerNumber }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.assistant1Number }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.assistant2Number }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                        class="tlh-right-bottom">{{ action.situation }}
                    </td>
                  </tr>


                  </tbody>
                </table>
              </td>
              <td width="200" valign="top" class="tlh-border-right-top">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr style="height: 13px;">
                    <td colspan="6" align="center" class="tlh-bottom-max"><strong>KARY</strong></td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                    <td align="center" class="tlh-right-bottom_dark">NR</td>
                    <td align="center" class="tlh-right-bottom_dark">MIN</td>
                    <td align="center" class="tlh-right-bottom_dark">RODZAJ</td>
                    <!--              <td align="center" class="tlh-right-bottom_dark">START</td>-->
                    <!--              <td align="center" class="tlh-bottom-dark-max">KONIEC</td>-->
                  </tr>

                  <!--    Akcje meczowe - kary [GOSPODARZE]        -->
                  <tr v-for="action in actions" :key="action.id">
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-right-bottom"
                        align="center">
                      {{ action.gameMinute }}:{{ action.gameSecond }}
                    </td>
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-right-bottom"
                        align="center">
                      {{ action.penalizedPlayerNumber }}
                    </td>
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-right-bottom"
                        align="center"> {{ action.timeLength }}
                    </td>
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-border-bottom"
                        align="center"> {{
                        action.typePenalty
                      }}
                    </td>
                  </tr>

                  </tbody>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="3" valign="top" class="d_border_left_bottom_right">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr>
                    <td width="20" class="tlh-right-max" align="center">KIEROWNIK:</td>
                    <td width="196">{{ match.homeTeamMenager }}</td>
                    <td width="20" align="center" class="tlh-right-max">TRENER:</td>
                    <td width="266">{{ match.homeTeamCoach }}</td>
                    <td width="20" class="tlh-right-max" align="center">PODPIS:</td>
                    <td width="188"></td>
                  </tr>
                  </tbody>
                </table>
              </td>
            </tr>
            </tbody>
          </table>

          <!--  Goscie   -->
          <table width="700" id="goscie" border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped"
                 style="margin-top: 75px;">
            <tbody>
            <tr>
              <td width="230" valign="top" class="tlh-border-right-top-left">
                <table width="200" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                  <tbody>
                  <tr>
                    <td width="80" class="tlh-right-max uk-text-center"><strong>B - GOŚCIE </strong></td>
                    <td class="uk-text-center"><strong> {{ match.visitingTeam.name }} </strong></td>
                  </tr>
                  </tbody>
                </table>
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr>
                    <td width="15" align="center" class="tlh-right-top-bottom-dark">Nr</td>
                    <td class="tlh-right-top-bottom-dark">Imię i Nazwisko</td>
                    <td width="15" align="center" class="tlh-right-top-bottom-dark">Poz</td>
                    <td width="15" align="center" class="tlh-top-bottom-dark-max">T/N</td>
                  </tr>

                  <!--   Renderowanie listy zawodników gospodarzy         -->
                  <tr v-for="visitingPlayer in visitingPlayers" :key="visitingPlayer.id">
                    <td align="center" class="tlh-right-bottom">{{ visitingPlayer.playerNumber }}</td>
                    <td class="tlh-right-bottom">{{ visitingPlayer.name }} {{ visitingPlayer.surname }}</td>
                    <td align="center" class="tlh-right-bottom">{{ visitingPlayer.position }}</td>
                    <td align="center" class="tlh-right-bottom">T</td>
                  </tr>

                  </tbody>
                </table>
              </td>
              <td width="280" valign="top" class="tlh-border-right-top">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr style="height: 13px;">
                    <td colspan="6" align="center" class="tlh-bottom-max">BRAMKI</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-right-bottom_dark">G</td>
                    <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                    <td align="center" class="tlh-right-bottom_dark">S</td>
                    <td align="center" class="tlh-right-bottom_dark">A1</td>
                    <td align="center" class="tlh-right-bottom_dark">A2</td>
                    <td align="center" class="tlh-right-bottom_dark2">SM</td>
                  </tr>

                  <!--     Akcja meczowa - GOAL       -->
                  <tr v-for="action in actions" :key="action.id">
                    <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                        class="tlh-right-bottom">{{ action.id }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.gameMinute }}:{{ action.gameSecond }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.scorerNumber }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.assistant1Number }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                        class="tlh-right-bottom">
                      {{ action.assistant2Number }}
                    </td>
                    <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                        class="tlh-right-bottom">{{ action.situation }}
                    </td>
                  </tr>


                  </tbody>
                </table>
              </td>
              <td width="200" valign="top" class="tlh-border-right-top">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr style="height: 13px;">
                    <td colspan="6" align="center" class="tlh-bottom-max"><strong>KARY</strong></td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                    <td align="center" class="tlh-right-bottom_dark">NR</td>
                    <td align="center" class="tlh-right-bottom_dark">MIN</td>
                    <td align="center" class="tlh-right-bottom_dark">RODZAJ</td>
                    <!--              <td align="center" class="tlh-right-bottom_dark">START</td>-->
                    <!--              <td align="center" class="tlh-bottom-dark-max">KONIEC</td>-->
                  </tr>

                  <!--    Akcje meczowe - kary [Goscie]        -->
                  <tr v-for="action in actions" :key="action.id">
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-right-bottom"
                        align="center">
                      {{ action.gameMinute }}:{{ action.gameSecond }}
                    </td>
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-right-bottom"
                        align="center">
                      {{ action.penalizedPlayerNumber }}
                    </td>
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-right-bottom"
                        align="center"> {{ action.timeLength }}
                    </td>
                    <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-border-bottom"
                        align="center"> {{
                        action.typePenalty
                      }}
                    </td>
                  </tr>

                  </tbody>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="3" valign="top" class="d_border_left_bottom_right">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody>
                  <tr>
                    <td width="20" class="tlh-right-max" align="center">KIEROWNIK:</td>
                    <td width="196">{{ match.visitingTeamMenager }}</td>
                    <td width="20" align="center" class="tlh-right-max">TRENER:</td>
                    <td width="266">{{ match.visitingTeamCoach }}</td>
                    <td width="20" class="tlh-right-max" align="center">PODPIS:</td>
                    <td width="188"></td>
                  </tr>
                  </tbody>
                </table>
              </td>
            </tr>
            </tbody>
          </table>

          <!--  Tabelka podsumowanie     -->
          <table width="710" id="podsumowanie" border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;"
                 class="uk-table uk-table-striped">
            <tbody><tr>
              <td valign="top" class="tlh-border">
                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tbody><tr>
                    <td align="center" class="tlh-right-bottom_dark2">&nbsp;  </td>
                    <td align="center" class="tlh-right-bottom-dark" colspan="3">BRAMKI</td>
                    <td align="center" class="tlh-right-bottom-dark" colspan="3">KARY</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-right-bottom_dark2">TERCJA</td>
                    <td align="center" class="tlh-right-bottom-dark">G A : B</td>
                    <td align="center" class="tlh-right-bottom-dark">PPGF A:B</td>
                    <td align="center" class="tlh-right-bottom-dark">SHGF A:B</td>
                    <td align="center" class="tlh-right-bottom-dark">PIM A:B</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">1</td>
                    <td align="center" class="tlh-right-bottom">{{score.regular.p1Score}}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p1Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p1Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ penaltiesTable.p1Penalties }}</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">2</td>
                    <td align="center" class="tlh-right-bottom">{{score.regular.p2Score}}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p2Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p2Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ penaltiesTable.p2Penalties }}</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">3</td>
                    <td align="center" class="tlh-right-bottom">{{score.regular.p3Score}}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p3Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p3Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ penaltiesTable.p3Penalties }}</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">DOGRYWKA</td>
                    <td align="center" class="tlh-right-bottom">{{score.regular.p4Score}}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p4Score }}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p4Score }}</td>
                    <td align="center" class="tlh-right-bottom"></td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">KARNE</td>
                    <td align="center" class="tlh-right-bottom">:</td>
                    <td align="center" class="tlh-right-bottom">-</td>
                    <td align="center" class="tlh-border-bottom">-</td>
                    <td align="center" class="tlh-border-bottom">-</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">RAZEM</td>
                    <td align="center" class="tlh-right-bottom">{{score.regular.finalScore}}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.powerPlay.finalScore }}</td>
                    <td align="center" class="tlh-right-bottom">{{ score.shortHanded.finalScore }}</td>
                    <td align="center" class="tlh-right-bottom">{{ penaltiesTable.finalPenalties }}</td>
                  </tr>
                  </tbody></table>
              </td>
              <td valign="top" class="tlh-border-right">


              </td>
              <td valign="top" class="tlh-border-right">
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                  <tbody>
                  <tr>
                    <td align="center" class="tlh-right-bottom_dark2" style="border: 1px solid #000;"> CZAS DLA "A" </td>
                    <td align="center" class="tlh-bottom-dark-max" style="border: 1px solid #000;"> CZAS DLA "B" </td>
                  </tr>
                  <tr >
                    <td align="center" class="tlh-border-bottom" >{{breakTimeHome}}</td>
                    <td align="center" class="tlh-border-bottom">{{breakTimeVisiting}}</td>
                  </tr>
                  <tr>
                    <td align="center" class="tlh-right-dark" style="width: 50%; border-bottom: 0px; background: #e5e5e5;" > OBSERWATOR MECZU</td>
                    <td align="center" class="tlh-border-bottom" style="border-bottom: 0px;">{{match.spectator}}  </td>
                  </tr>

                  </tbody></table>
              </td>

              <td valign="top" class="tlh-border-right">
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                  <tbody><tr>
                    <td align="center" class="tlh-bottom-dark-max" colspan="2">ZMIANY BRAMKARZY</td>
                  </tr>
                  <tr>
                    <td valign="top" width="50%">
                      <table width="100%" border="0" cellspacing="0" cellpadding="2" class="border uk-table uk-table-striped">
                        <tbody><tr>
                          <td align="center" class="tlh-right-bottom_dark2" style="border-right: 1px solid #000;">CZAS A</td>
                          <td align="center" class="tlh-right-bottom_dark2" style="border-right: 1px solid #000;">Nr</td>
                        </tr>

                        <tr v-for="a in actions" :key="a.id">
                          <td align="center" class="tlh-border-bottom" style="border-right: 1px solid #000;"
                              v-if="a.teamType === 'homeTeam' && a.type === 'CHANGE_GOALKEEPER'">{{ a.gameMinute }}:{{a.gameSecond}}</td>
                          <td align="center" class="tlh-border-bottom"
                              v-if="a.teamType === 'homeTeam' && a.type === 'CHANGE_GOALKEEPER'">
                            {{ a.goalkeeperInNumber }}

                          </td>
                        </tr>

                        </tbody></table>
                    </td>
                    <td valign="top" class="border" width="50%">
                      <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                        <tbody><tr>
                          <td align="center" class="tlh-bottom-dark-max">CZAS B</td>
                          <td align="center" class="tlh-right-bottom_dark2" style="border-right: 0px; border-left: 1px solid #000;">Nr</td>
                        </tr>

                        <tr v-for="a in actions" :key="a.id">
                          <td align="center" class="tlh-border-bottom" style="border-right: 1px solid #000;"
                              v-if="a.teamType === 'visitingTeam' && a.type === 'CHANGE_GOALKEEPER'">{{ a.gameMinute }}:{{a.gameSecond}}</td>
                          <td align="center" class="tlh-border-bottom"
                              v-if="a.teamType === 'visitingTeam' && a.type === 'CHANGE_GOALKEEPER'">
                            {{ a.goalkeeperInNumber }}

                          </td>
                        </tr>

                        </tbody></table>
                    </td>
                  </tr>


                  </tbody></table>
              </td>
            </tr>
            </tbody></table>

          <!-- Informacje dodatkowe   -->
          <table border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped" style="margin-top: 55px;">
            <tbody>
            <tr>
              <td class="tlh-border">
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                  <tbody>
                  <tr>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. KAR</td>
                    <td width="101" class="tlh-right-bottom" style="text-align: center;">{{
                        match.penaltyReferee1
                      }}
                    </td>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">
                      SEKRETARZ
                    </td>
                    <td width="101" class="tlh-right-bottom" style="text-align: center;">
                      {{ match.secretary }}
                    </td>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. LINIOWY</td>
                    <td width="101" class="tlh-right-bottom" style="text-align: center;">{{ match.linesmen1 }}</td>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. GŁÓWNY</td>
                    <td width="101" class="tlh-border-bottom" style="text-align: center;">{{ match.referee1 }}</td>
                  </tr>
                  <tr>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. KAR</td>
                    <td class="tlh-right-bottom" style="text-align: center;">{{ match.penaltyReferee2 }}</td>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">SPIKER</td>
                    <td class="tlh-right-bottom" style="text-align: center;">
                      {{ match.speaker }}
                    </td>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. LINIOWY</td>
                    <td class="tlh-right-bottom" style="text-align: center;">{{ match.linesmen2 }}</td>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. GŁÓWNY</td>
                    <td class="tlh-border-bottom" style="text-align: center;">{{ match.referee2 }}</td>
                  </tr>

                  <tr>
                    <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. CZASU</td>
                    <td class="tlh-right-bottom" style="text-align: center;">{{ match.timeReferee }}</td>
                  </tr>
                  </tbody>
                </table>

                <table border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped">
                  <tbody>
                  <tr>
                    <td class="tlh-bottom-right-bottom-max" width="110" style="text-align: center;"><b>UWAGI NA
                      ODWROCIE</b>
                    </td>
                    <td class="tlh-right-bottom" width="65" style="text-align: center;">TAK NIE</td>
                    <td class="tlh-bottom-right-bottom-max" width="74" style="text-align: center;">PODPIS SEKRETARZA
                    </td>
                    <td class="tlh-right-bottom" width="101">&nbsp;</td>
                    <td class="tlh-bottom-right-bottom-max" width="74" style="text-align: center;">PODPIS DRUŻYNA "A"
                    </td>
                    <td class="tlh-right-bottom" width="88">&nbsp;</td>
                    <td class="tlh-bottom-right-bottom-max" width="74" style="text-align: center;">PODPIS DRUŻYNA "B"
                    </td>
                    <td class="tlh-right-bottom" width="87">&nbsp;</td>
                    <td class="tlh-bottom-right-bottom-max" style="text-align: center;" width="74">PODPIS S. GŁÓWNEGO
                    </td>
                    <td class="tlh-right-bottom" width="100" style="border-right: 0px;">&nbsp;</td>
                  </tr>
                  </tbody>
                </table>
              </td>
            </tr>
            </tbody>
          </table>

          <!--  Notatki  -->

          <table width="100%" style="margin-top: 5px;" border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
              <td class="tlh-bottom-right-bottom-max" style="border: 2px solid #000; text-align: center;">
                <b>Notatki:</b>

              </td>
            </tr>
            <tr>
              <td class="tlh-right-bottom" style="border-left: 1px solid #000;">
                <p style="margin-left: 10px;" v-for="comment in comments" :key="comment.id">
                  <b>Autor: </b>{{ comment.author }}<br><b>Treść: </b> {{ comment.message }}</p>
              </td>
            </tr>
            </tbody>
          </table>

        </div>
      </template>
    </Vue3Html2pdf>
    <button
        class="uk-button uk-button-default uk-width-1-1"
        @click="exportAsPdf()">
      Eksportuj do PDF
    </button>
    <div id="tlh-protocol-preview" style="margin-top: 15px; border: 1px solid grey;">

      <!--  Nagłówek - informacje  -->
      <table width="950" id="tlh-protocol-info-top" border="0" cellspacing="0" cellpadding="0"
             class="uk-table uk-table-striped">
        <tbody>
        <tr>
          <td colspan="1">
            <img :src="require(`@/assets/logo.png`)" alt="Polski Hokej" width="350"
                 style="margin-left: 10px;margin-top: 25px;">
          </td>
          <td width="300" align="center">
            <h2 style="font-weight: bold">{{ leagueName }}</h2>
            <h2 style="font-weight: bold">PROTOKÓŁ ZAWODÓW</h2>
          </td>
          <td width="400" style="padding-top: 0px;" align="center">
            <table class="uk-table uk-table-striped" style="width: 250px; text-align: center;">
              <tbody>
              <tr>
                <td style="border-left: 1px solid grey; border-right: 1px solid grey; border-bottom: 1px solid #111; height: 100px;">
                </td>
              </tr>
              <tr>
                <td style="border: 1px solid #111;"><small>SŁUŻBA MEDYCZNA</small></td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="tlh-border">
            <table width="700" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
              <tbody>
              <tr style="text-align: center;">
                <td width="28" class="tlh-right-max" rowspan="2">DATA</td>
                <td width="28" class="tlh-right-max" rowspan="1" style="border-bottom: 1px solid #000;">PLAN</td>
                <td width="100" class="tlh-border-right uk-text-center" rowspan="1">{{ match.date }}</td>
                <td width="50" class="tlh-right-max" rowspan="2">MIEJSCE&nbsp;</td>
                <td width="190" class="tlh-border-right uk-text-center" rowspan="2">{{ match.place }}</td>
                <td width="30" class="tlh-right-max" rowspan="2">START&nbsp;</td>
                <td class="tlh-border-right uk-text-center" rowspan="2"> {{ match.time }} &nbsp;</td>
                <td width="50" class="tlh-right-max" rowspan="2">KONIEC&nbsp;</td>
                <td width="30" class="tlh-border-right" rowspan="2"> {{ match.timeEnd }}&nbsp;</td>
                <td width="65" class="tlh-right-max" rowspan="2">LIGA&nbsp;</td>
                <td width="50" class="tlh-border-right" rowspan="2"> {{ leagueName }} &nbsp;</td>
                <td width="65" class="tlh-right-max uk-text-center" rowspan="2">NR MECZU&nbsp;</td>
                <td width="50" rowspan="2"> {{ match.id }}</td>
                <td width="65" class="tlh-right-max" rowspan="2">WIDZÓW&nbsp;</td>
                <td width="50" rowspan="2"> {{ match.attendance }}</td>
              </tr>
              <tr style="text-align: center;">
                <td width="28" class="tlh-right-max" rowspan="1">ROZEGR</td>
                <td width="100" class="tlh-border-right uk-text-center" rowspan="1">{{ match.date }}</td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        </tbody>
      </table>
      <!--  Gospodarze   -->
      <table width="700" id="gospodarze" border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped">
        <tbody>
        <tr>
          <td width="230" valign="top" class="tlh-border-right-top-left">
            <table width="200" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
              <tbody>
              <tr>
                <td width="80" class="tlh-right-max uk-text-center"><strong>A - GOSPODARZE </strong></td>
                <td class="uk-text-center"><strong> {{ match.homeTeam.name }} </strong></td>
              </tr>
              </tbody>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr>
                <td width="15" align="center" class="tlh-right-top-bottom-dark">Nr</td>
                <td class="tlh-right-top-bottom-dark">Imię i Nazwisko</td>
                <td width="15" align="center" class="tlh-right-top-bottom-dark">Poz</td>
              </tr>

              <!--   Renderowanie listy zawodników gospodarzy         -->
              <tr v-for="homePlayer in homePlayers" :key="homePlayer.id">
                <td align="center" class="tlh-right-bottom">{{ homePlayer.playerNumber }}</td>
                <td class="tlh-right-bottom">{{ homePlayer.name }} {{ homePlayer.surname }}</td>
                <td align="center" class="tlh-right-bottom">{{ homePlayer.position }}</td>
              </tr>

              </tbody>
            </table>
          </td>
          <td width="280" valign="top" class="tlh-border-right-top">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr style="height: 13px;">
                <td colspan="6" align="center" class="tlh-bottom-max">BRAMKI</td>
              </tr>
              <tr>
                <td align="center" class="tlh-right-bottom_dark">G</td>
                <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                <td align="center" class="tlh-right-bottom_dark">S</td>
                <td align="center" class="tlh-right-bottom_dark">A1</td>
                <td align="center" class="tlh-right-bottom_dark">A2</td>
                <td align="center" class="tlh-right-bottom_dark2">SM</td>
              </tr>

              <!--     Akcja meczowa - GOAL       -->
              <tr v-for="action in actions" :key="action.id">
                <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                    class="tlh-right-bottom">{{ action.id }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                    class="tlh-right-bottom">
                  {{ action.gameMinute }}:{{ action.gameSecond }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam' " align="center"
                    class="tlh-right-bottom">
                  {{ action.scorerNumber }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam'" align="center"
                    class="tlh-right-bottom">
                  {{ action.assistant1Number }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam'" align="center"
                    class="tlh-right-bottom">
                  {{ action.assistant2Number }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'homeTeam'" align="center"
                    class="tlh-right-bottom">{{ action.situation }}
                </td>
              </tr>


              </tbody>
            </table>
          </td>
          <td width="200" valign="top" class="tlh-border-right-top">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr style="height: 13px;">
                <td colspan="6" align="center" class="tlh-bottom-max"><strong>KARY</strong></td>
              </tr>
              <tr>
                <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                <td align="center" class="tlh-right-bottom_dark">NR</td>
                <td align="center" class="tlh-right-bottom_dark">MIN</td>
                <td align="center" class="tlh-right-bottom_dark">RODZAJ</td>
                <!--              <td align="center" class="tlh-right-bottom_dark">START</td>-->
                <!--              <td align="center" class="tlh-bottom-dark-max">KONIEC</td>-->
              </tr>

              <!--    Akcje meczowe - kary [GOSPODARZE]        -->
              <tr v-for="action in actions" :key="action.id">
                <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-right-bottom"
                    align="center">
                  {{ action.gameMinute }}:{{ action.gameSecond }}
                </td>
                <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-right-bottom"
                    align="center">
                  {{ action.penalizedPlayerNumber }}
                </td>
                <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-right-bottom"
                    align="center"> {{ action.timeLength }}
                </td>
                <td v-if="action.type === 'PENALTY' && action.teamType === 'homeTeam'" class="tlh-border-bottom"
                    align="center"> {{
                    action.typePenalty
                  }}
                </td>
              </tr>

              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="3" valign="top" class="d_border_left_bottom_right">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr>
                <td width="20" class="tlh-right-max" align="center">KIEROWNIK:</td>
                <td width="196">{{ match.homeTeamMenager }}</td>
                <td width="20" align="center" class="tlh-right-max">TRENER:</td>
                <td width="266">{{ match.homeTeamCoach }}</td>
                <td width="20" class="tlh-right-max" align="center">PODPIS:</td>
                <td width="188"></td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        </tbody>
      </table>

      <!--  Goscie   -->
      <table width="700" id="goscie" border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped"
             style="margin-top: 15px;">
        <tbody>
        <tr>
          <td width="230" valign="top" class="tlh-border-right-top-left">
            <table width="200" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
              <tbody>
              <tr>
                <td width="80" class="tlh-right-max uk-text-center"><strong>B - GOŚCIE </strong></td>
                <td class="uk-text-center"><strong> {{ match.visitingTeam.name }} </strong></td>
              </tr>
              </tbody>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr>
                <td width="15" align="center" class="tlh-right-top-bottom-dark">Nr</td>
                <td class="tlh-right-top-bottom-dark">Imię i Nazwisko</td>
                <td width="15" align="center" class="tlh-right-top-bottom-dark">Poz</td>
              </tr>

              <!--   Renderowanie listy zawodników gospodarzy         -->
              <tr v-for="visitingPlayer in visitingPlayers" :key="visitingPlayer.id">
                <td align="center" class="tlh-right-bottom">{{ visitingPlayer.playerNumber }}</td>
                <td class="tlh-right-bottom">{{ visitingPlayer.name }} {{ visitingPlayer.surname }}</td>
                <td align="center" class="tlh-right-bottom">{{ visitingPlayer.position }}</td>
              </tr>

              </tbody>
            </table>
          </td>
          <td width="280" valign="top" class="tlh-border-right-top">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr style="height: 13px;">
                <td colspan="6" align="center" class="tlh-bottom-max">BRAMKI</td>
              </tr>
              <tr>
                <td align="center" class="tlh-right-bottom_dark">G</td>
                <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                <td align="center" class="tlh-right-bottom_dark">S</td>
                <td align="center" class="tlh-right-bottom_dark">A1</td>
                <td align="center" class="tlh-right-bottom_dark">A2</td>
                <td align="center" class="tlh-right-bottom_dark2">SM</td>
              </tr>

              <!--     Akcja meczowa - GOAL       -->
              <tr v-for="action in actions" :key="action.id">
                <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam' " align="center"
                    class="tlh-right-bottom">{{ action.id }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam'" align="center"
                    class="tlh-right-bottom">
                  {{ action.gameMinute }}:{{ action.gameSecond }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam'" align="center"
                    class="tlh-right-bottom">
                  {{ action.scorerNumber }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam'" align="center"
                    class="tlh-right-bottom">
                  {{ action.assistant1Number }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam'" align="center"
                    class="tlh-right-bottom">
                  {{ action.assistant2Number }}
                </td>
                <td v-if="action.type === 'GOAL' && action.teamType === 'visitingTeam'" align="center"
                    class="tlh-right-bottom">{{ action.situation }}
                </td>
              </tr>


              </tbody>
            </table>
          </td>
          <td width="200" valign="top" class="tlh-border-right-top">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr style="height: 13px;">
                <td colspan="6" align="center" class="tlh-bottom-max"><strong>KARY</strong></td>
              </tr>
              <tr>
                <td align="center" class="tlh-right-bottom_dark">CZAS</td>
                <td align="center" class="tlh-right-bottom_dark">NR</td>
                <td align="center" class="tlh-right-bottom_dark">MIN</td>
                <td align="center" class="tlh-right-bottom_dark">RODZAJ</td>
                <!--              <td align="center" class="tlh-right-bottom_dark">START</td>-->
                <!--              <td align="center" class="tlh-bottom-dark-max">KONIEC</td>-->
              </tr>

              <!--    Akcje meczowe - kary [GOSPODARZE]        -->
              <tr v-for="action in actions" :key="action.id">
                <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-right-bottom"
                    align="center">
                  {{ action.gameMinute }}:{{ action.gameSecond }}
                </td>
                <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-right-bottom"
                    align="center">
                  {{ action.penalizedPlayerNumber }}
                </td>
                <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-right-bottom"
                    align="center"> {{ action.timeLength }}
                </td>
                <td v-if="action.type === 'PENALTY' && action.teamType === 'visitingTeam'" class="tlh-border-bottom"
                    align="center"> {{
                    action.typePenalty
                  }}
                </td>
              </tr>

              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="3" valign="top" class="d_border_left_bottom_right">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody>
              <tr>
                <td width="20" class="tlh-right-max" align="center">KIEROWNIK:</td>
                <td width="196">{{ match.visitingTeamMenager }}</td>
                <td width="20" align="center" class="tlh-right-max">TRENER:</td>
                <td width="266">{{ match.visitingTeamCoach }}</td>
                <td width="20" class="tlh-right-max" align="center">PODPIS:</td>
                <td width="188"></td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        </tbody>
      </table>

      <!--  Tabelka podsumowanie     -->
      <table width="710" id="podsumowanie" border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;"
             class="uk-table uk-table-striped">
        <tbody><tr>
          <td valign="top" class="tlh-border">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tbody><tr>
                <td align="center" class="tlh-right-bottom_dark2">&nbsp;  </td>
                <td align="center" class="tlh-right-bottom-dark" colspan="3">BRAMKI</td>
                <td align="center" class="tlh-right-bottom-dark" colspan="3">KARY</td>
              </tr>
              <tr>
                <td align="center" class="tlh-right-bottom_dark2">TERCJA</td>
                <td align="center" class="tlh-right-bottom-dark">G A : B</td>
                <td align="center" class="tlh-right-bottom-dark">PPGF A:B</td>
                <td align="center" class="tlh-right-bottom-dark">SHGF A:B</td>
                <td align="center" class="tlh-right-bottom-dark">PIM A:B</td>
              </tr>
              <tr>
                <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">1</td>
                <td align="center" class="tlh-right-bottom">{{score.regular.p1Score}}</td>
                <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p1Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p1Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ penaltiesTable.p1Penalties }}</td>
              </tr>
              <tr>
                <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">2</td>
                <td align="center" class="tlh-right-bottom">{{score.regular.p2Score}}</td>
                <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p2Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p2Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ penaltiesTable.p2Penalties }}</td>
              </tr>
              <tr>
                <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">3</td>
                <td align="center" class="tlh-right-bottom">{{score.regular.p3Score}}</td>
                <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p3Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p3Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ penaltiesTable.p3Penalties }}</td>
              </tr>
              <tr>
                <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">DOGRYWKA</td>
                <td align="center" class="tlh-right-bottom">{{score.regular.p4Score}}</td>
                <td align="center" class="tlh-right-bottom">{{ score.powerPlay.p4Score }}</td>
                <td align="center" class="tlh-right-bottom">{{ score.shortHanded.p4Score }}</td>
                <td align="center" class="tlh-right-bottom"></td>
              </tr>
              <tr>
                <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">KARNE</td>
                <td align="center" class="tlh-right-bottom">:</td>
                <td align="center" class="tlh-right-bottom">-</td>
                <td align="center" class="tlh-border-bottom">-</td>
                <td align="center" class="tlh-border-bottom">-</td>
              </tr>
              <tr>
                <td align="center" class="tlh-bottom-right-dark" style="background: #e5e5e5;">RAZEM</td>
                <td align="center" class="tlh-right-bottom">{{score.regular.finalScore}}</td>
                <td align="center" class="tlh-right-bottom">{{ score.powerPlay.finalScore }}</td>
                <td align="center" class="tlh-right-bottom">{{ score.shortHanded.finalScore }}</td>
                <td align="center" class="tlh-right-bottom">{{ penaltiesTable.finalPenalties }}</td>
              </tr>
              </tbody></table>
          </td>
          <td valign="top" class="tlh-border-right">


          </td>
          <td valign="top" class="tlh-border-right">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
              <tbody>
              <tr>
                <td align="center" class="tlh-right-bottom_dark2" style="border: 1px solid #000;"> CZAS DLA "A" </td>
                <td align="center" class="tlh-bottom-dark-max" style="border: 1px solid #000;"> CZAS DLA "B" </td>
              </tr>
              <tr >
                  <td align="center" class="tlh-border-bottom" >{{breakTimeHome}}</td>
                  <td align="center" class="tlh-border-bottom">{{breakTimeVisiting}}</td>
              </tr>
              <tr>
                <td align="center" class="tlh-right-dark" style="width: 50%; border-bottom: 0px; background: #e5e5e5;" > OBSERWATOR MECZU</td>
                <td align="center" class="tlh-border-bottom" style="border-bottom: 0px;">{{match.spectator}}  </td>
              </tr>

              </tbody></table>
          </td>

          <td valign="top" class="tlh-border-right">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
              <tbody><tr>
                <td align="center" class="tlh-bottom-dark-max" colspan="2">ZMIANY BRAMKARZY</td>
              </tr>
              <tr>
                <td valign="top" width="50%">
                  <table width="100%" border="0" cellspacing="0" cellpadding="2" class="border uk-table uk-table-striped">
                    <tbody><tr>
                      <td align="center" class="tlh-right-bottom_dark2" style="border-right: 1px solid #000;">CZAS A</td>
                      <td align="center" class="tlh-right-bottom_dark2" style="border-right: 1px solid #000;">Nr</td>
                    </tr>

                    <tr v-for="a in actions" :key="a.id">
                      <td align="center" class="tlh-border-bottom" style="border-right: 1px solid #000;"
                          v-if="a.teamType === 'homeTeam' && a.type === 'CHANGE_GOALKEEPER'">{{ a.gameMinute }}:{{a.gameSecond}}</td>
                      <td align="center" class="tlh-border-bottom"
                          v-if="a.teamType === 'homeTeam' && a.type === 'CHANGE_GOALKEEPER'">
                        {{ a.goalkeeperInNumber }}

                      </td>
                    </tr>

                    </tbody></table>
                </td>
                <td valign="top" class="border" width="50%">
                  <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
                    <tbody><tr>
                      <td align="center" class="tlh-bottom-dark-max">CZAS B</td>
                      <td align="center" class="tlh-right-bottom_dark2" style="border-right: 0px; border-left: 1px solid #000;">Nr</td>
                    </tr>

                    <tr v-for="a in actions" :key="a.id">
                      <td align="center" class="tlh-border-bottom" style="border-right: 1px solid #000;"
                          v-if="a.teamType === 'visitingTeam' && a.type === 'CHANGE_GOALKEEPER'">{{ a.gameMinute }}:{{a.gameSecond}}</td>
                      <td align="center" class="tlh-border-bottom"
                          v-if="a.teamType === 'visitingTeam' && a.type === 'CHANGE_GOALKEEPER'">
                        {{ a.goalkeeperInNumber }}

                      </td>
                    </tr>

                    </tbody></table>
                </td>
              </tr>


              </tbody></table>
          </td>
        </tr>
        </tbody></table>

      <!-- Informacje dodatkowe   -->
      <table border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped" style="margin-top: 15px;">
        <tbody>
        <tr>
          <td class="tlh-border">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="uk-table uk-table-striped">
              <tbody>
              <tr>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. KAR</td>
                <td width="101" class="tlh-right-bottom" style="text-align: center;">{{ match.penaltyReferee1 }}</td>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">
                  SEKRETARZ
                </td>
                <td width="101" class="tlh-right-bottom" style="text-align: center;">
                  {{ match.secretary }}
                </td>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. LINIOWY</td>
                <td width="101" class="tlh-right-bottom" style="text-align: center;">{{ match.linesmen1 }}</td>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. GŁÓWNY</td>
                <td width="101" class="tlh-border-bottom" style="text-align: center;">{{ match.referee1 }}</td>
              </tr>
              <tr>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. KAR</td>
                <td class="tlh-right-bottom" style="text-align: center;">{{ match.penaltyReferee2 }}</td>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">SPIKER</td>
                <td class="tlh-right-bottom" style="text-align: center;">
                  {{ match.speaker }}
                </td>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. LINIOWY</td>
                <td class="tlh-right-bottom" style="text-align: center;">{{ match.linesmen2 }}</td>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. GŁÓWNY</td>
                <td class="tlh-border-bottom" style="text-align: center;">{{ match.referee2 }}</td>
              </tr>

              <tr>
                <td width="74" class="tlh-bottom-right-bottom-max" style="text-align: center;">S. CZASU</td>
                <td class="tlh-right-bottom" style="text-align: center;">{{ match.timeReferee }}</td>
              </tr>
              </tbody>
            </table>

            <table border="0" cellspacing="0" cellpadding="0" class="uk-table uk-table-striped">
              <tbody>
              <tr>
                <td class="tlh-bottom-right-bottom-max" width="110" style="text-align: center;"><b>UWAGI NA ODWROCIE</b>
                </td>
                <td class="tlh-right-bottom" width="65" style="text-align: center;">TAK NIE</td>
                <td class="tlh-bottom-right-bottom-max" width="74" style="text-align: center;">PODPIS SEKRETARZA</td>
                <td class="tlh-right-bottom" width="101">&nbsp;</td>
                <td class="tlh-bottom-right-bottom-max" width="74" style="text-align: center;">PODPIS DRUŻYNA "A"</td>
                <td class="tlh-right-bottom" width="88">&nbsp;</td>
                <td class="tlh-bottom-right-bottom-max" width="74" style="text-align: center;">PODPIS DRUŻYNA "B"</td>
                <td class="tlh-right-bottom" width="87">&nbsp;</td>
                <td class="tlh-bottom-right-bottom-max" style="text-align: center;" width="74">PODPIS S. GŁÓWNEGO</td>
                <td class="tlh-right-bottom" width="100" style="border-right: 0px;">&nbsp;</td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        </tbody>
      </table>

      <!--  Notatki  -->

      <table width="100%" style="margin-top: 5px;" border="0" cellspacing="0" cellpadding="0">
        <tbody>
        <tr>
          <td class="tlh-bottom-right-bottom-max" style="border: 2px solid #000; text-align: center;"><b>Notatki:</b>

          </td>
        </tr>
        <tr>
          <td class="tlh-right-bottom" style="border-left: 1px solid #000;">
            <p style="margin-left: 10px;" v-for="comment in comments" :key="comment.id">
              <b>Autor: </b>{{ comment.author }}<br><b>Treść: </b> {{ comment.message }}</p>
          </td>
        </tr>
        </tbody>
      </table>

    </div>


  </div>
  <div v-else>
    Match not provided
  </div>

</template>

<script>
import Vue3Html2pdf from "vue3-html2pdf";
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "ProtocolView",
  components: {Vue3Html2pdf},
  props: ['match', 'homePlayers', 'visitingPlayers','score'],
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  data() {
    return {
      leagueName: 'TLH',
      actions: [],
      comments: [],
      penaltiesTable: [],
      breakTimeHome: null,
      breakTimeVisiting: null,

    }
  },
  created() {
    setTimeout(() => {
      axios.get("http://localhost:8080/api/matches/"+this.match.id+"/penaltiesTable")
          .then(response => {
            this.penaltiesTable = response.data;
          })
      axios.get("http://localhost:8080/api/matches/" + this.match.id + "/actions")
          .then(response => {
            this.actions = response.data.actions
            this.actions = this.actions.sort(function (obj1, obj2) {
              return obj1.totalTimeInSeconds - obj2.totalTimeInSeconds;
            })
            this.actions.forEach((a) => {
              if(a.teamType === 'visitingTeam' && a.type === 'BREAK_TIME'){
                this.breakTimeVisiting = a.gameMinute + ":" + a.gameSecond
              }
              if(a.teamType === 'homeTeam' && a.type === 'BREAK_TIME'){
                this.breakTimeHome = a.gameMinute + ":" + a.gameSecond
              }
              if (a.type === "PENALTY") {
                switch (a.timeLength) {
                  case "TWO":
                    a.timeLength = 2
                    break;
                  case "FOUR":
                    a.timeLength = 4
                    break;
                  case "TEN":
                    a.timeLength = 10
                    break;
                  case "TWENTY":
                    a.timeLength = 20
                    break;
                  case "TWENTY_FIVE":
                    a.timeLength = 25
                    break;
                  case "PENALTY_SHOT":
                    a.timeLength = "Rzut karny"
                    break;
                }
              }
              if (a.gameSecond < 10) {
                a.gameSecond = "0" + a.gameSecond;
              }
              if (a.gameMinute < 10) {
                a.gameMinute = "0" + a.gameMinute;
              }
            })
          })
    }, 100)

    axios.get("http://localhost:8080/api/matches/" + this.match.id + "/comments",{
      headers: {
        'Authorization': 'Bearer '+this.User.userInfo.token,
      }
    })
        .then(response => {
          this.comments = response.data;
        })

  },
  methods: {
    exportAsPdf() {
      this.$refs.html2Pdf.downloadPdf();
    }
  }
}
</script>

<style scoped>
#tlh-protocol-info-top {
  margin-bottom: 15px;
}

.tlh-border-right {
  border-right: 1px solid #999;
  text-align: right;
  height: 12px;
  padding: 10px;
}

.tlh-right-max {
  border-right: 1px solid #999;
  background-color: #e5e5e5;
  text-align: right;
  height: 12px;
  padding: 10px;
}

.tlh-border {
  border: 2px solid #000;
  height: 12px;
}

.tlh-border-right-top-left {
  border-top: 2px solid #000;
  border-right: 2px solid #000;
  border-left: 2px solid #000;
  height: 12px;
}

.tlh-right-bottom {
  border-right: 1px solid #111;
  border-bottom: 1px solid #111;
  height: 12px;
}

.tlh-right-top-bottom-dark {
  border-right: 1px solid #111;
  border-top: 1px solid #111;
  border-bottom: 2px solid #000;
  background-color: #e5e5e5;
  height: 12px;
  padding: 10px;
}

.tlh-border-bottom {
  border-bottom: #111 1px solid;
  height: 12px;
  border-right: #111 1px solid;
  border-left: #111 1px solid;
}

.tlh-top-bottom-dark-max {
  border-bottom: 2px solid #000;
  border-top: 1px solid #999;
  background-color: #e5e5e5;
  height: 12px;
  padding: 10px;
}

.tlh-border-right-top {
  border-top: 2px solid #000;
  border-right: 2px solid #000;
  border-left: 2px solid #000;
  height: 12px;
}

.tlh-bottom-max {
  border-bottom: #111 1px solid;
  background-color: #e5e5e5;
  height: 12px;
  font-weight: bold;
}

.tlh-bottom-right-dark {
  border: 1px solid #111;
  height: 12px;
}

.tlh-bottom-right-bottom-max {
  border-right: 1px solid #111;
  border-bottom: 1px solid #111;
  border-top: 1px solid #111;
  background-color: #e5e5e5;
  text-align: right;
  height: 12px;
}

.tlh-bottom-dark-max {
  border-bottom: 2px solid #000;
  border-left: 1px solid #000;
  background-color: #e5e5e5;
  height: 12px;
}


.tlh-right-bottom_dark {
  border-right: 1px solid #111;
  border-bottom: 2px solid #000;
  background-color: #e5e5e5;
  height: 12px;
}

.tlh-right-bottom-dark {
  border-right: 1px solid #111;
  border-bottom: 2px solid #000;
  background-color: #e5e5e5;
  height: 12px;
}

.tlh-right-bottom_dark2 {
  border-bottom: 2px solid #000;
  border-left: 1px solid #000;
  background-color: #e5e5e5;
  height: 12px;
}

.tlh-right-bottom {
  border-right: 1px solid #111;
  border-bottom: 1px solid #111;
  border-top: 1px solid #111;
  height: 12px;
}

.uk-table {
  margin: 0;
  border-spacing: 30px !important;
}

.uk-table td {
  padding: 2px;
  vertical-align: top;
}

.uk-table > th {
  padding: 10px !important;
  vertical-align: top;
}

</style>