<template>

  <div class="uk-align-center uk-text-center">
    <img :src="teamImage"/>

  </div>

  <h3 class="naglowek-piatka">Pierwsza piątka</h3>
  <div class="uk-text-center">

    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(0)">
      <div v-if="teamType === 'home' ">
        <div v-if="this.players[0] !== undefined "/>
        <p>{{ this.players[0].name }}</p>
        <img :src="this.players[0].img" style="height: 60px">
        <p>{{ this.players[0].surname }}</p>
        <div class="player-position">{{ this.players[0].position }}</div>
        <div class="player-position">{{ this.players[0].playerNumber }}</div>
      </div>
      <div v-else-if="teamType === 'visiting'">
        <div v-if="this.playersVisiting[0] !== undefined">
          <p>{{ this.playersVisiting[0].name }}</p>
          <img :src="this.playersVisiting[0].img" style="height: 60px">
          <p>{{ this.playersVisiting[0].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[0].position }}</div>
          <div class="player-position">{{ this.playersVisiting[0].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>

    </div>

    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(1)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[1]  !== undefined">
          <p>{{ this.players[1].name }}</p>
          <img :src="this.players[1].img" style="height: 60px">
          <p>{{ this.players[1].surname }}</p>
          <div class="player-position">{{ this.players[1].position }}</div>
          <div class="player-position">{{ this.players[1].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[1] !== undefined">
          <p>{{ this.playersVisiting[1].name }}</p>
          <img :src="this.playersVisiting[1].img" style="height: 60px">
          <p>{{ this.playersVisiting[1].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[1].position }}</div>
          <div class="player-position">{{ this.playersVisiting[1].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>

    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(2)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[2]  !== undefined">
          <p>{{ this.players[2].name }}</p>
          <img :src="players[2].img" style="height: 60px">
          <p>{{ this.players[2].surname }}</p>
          <div class="player-position">{{ this.players[2].position }}</div>
          <div class="player-position">{{ this.players[2].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[2]  !== undefined">
          <p>{{ this.playersVisiting[2].name }}</p>
          <img :src="this.playersVisiting[2].img" style="height: 60px">
          <p>{{ this.playersVisiting[2].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[2].position }}</div>
          <div class="player-position">{{ this.playersVisiting[2].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>

    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(3)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[3]  !== undefined">
          <p>{{ this.players[3].name }}</p>
          <img :src="this.players[3].img" style="height: 60px">
          <p>{{ this.players[3].surname }}</p>
          <div class="player-position">{{ this.players[3].position }}</div>
          <div class="player-position">{{ this.players[3].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[3]  !== undefined">
          <p>{{ this.playersVisiting[3].name }}</p>
          <img :src="this.playersVisiting[3].img" style="height: 60px">
          <p>{{ this.playersVisiting[3].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[3].position }}</div>
          <div class="player-position">{{ this.playersVisiting[3].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>

    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(4)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[4]  !== undefined">
          <p>{{ this.players[4].name }}</p>
          <img :src="this.players[4].img" style="height: 60px">
          <p>{{ this.players[4].surname }}</p>
          <div class="player-position">{{ this.players[4].position }}</div>
          <div class="player-position">{{ this.players[4].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[4]  !== undefined">
          <p>{{ this.playersVisiting[4].name }}</p>
          <img :src="this.playersVisiting[4].img" style="height: 60px">
          <p>{{ this.playersVisiting[4].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[4].position }}</div>
          <div class="player-position">{{ this.playersVisiting[4].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
  </div>

  <h3 class="naglowek-piatka">Druga piątka</h3>

  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(5)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[5]  !== undefined">
          <p>{{ this.players[5].name }}</p>
          <img :src="this.players[5].img" style="height: 60px">
          <p>{{ this.players[5].surname }}</p>
          <div class="player-position">{{ this.players[5].position }}</div>
          <div class="player-position">{{ this.players[5].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[5]  !== undefined">
          <p>{{ this.playersVisiting[5].name }}</p>
          <img :src="this.playersVisiting[5].img" style="height: 60px">
          <p>{{ this.playersVisiting[5].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[5].position }}</div>
          <div class="player-position">{{ this.playersVisiting[5].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>


    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(6)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[6]  !== undefined">
          <p>{{ this.players[6].name }}</p>
          <img :src="this.players[6].img" style="height: 60px">
          <p>{{ this.players[6].surname }}</p>
          <div class="player-position">{{ this.players[6].position }}</div>
          <div class="player-position">{{ this.players[6].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[6]  !== undefined">
          <p>{{ this.playersVisiting[6].name }}</p>
          <img :src="this.playersVisiting[6].img" style="height: 60px">
          <p>{{ this.playersVisiting[6].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[6].position }}</div>
          <div class="player-position">{{ this.playersVisiting[6].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(7)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[7]  !== undefined">
          <p>{{ this.players[7].name }}</p>
          <img :src="this.players[7].img" style="height: 60px">
          <p>{{ this.players[7].surname }}</p>
          <div class="player-position">{{ this.players[7].position }}</div>
          <div class="player-position">{{ this.players[7].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[7]  !== undefined">
          <p>{{ this.playersVisiting[7].name }}</p>
          <img :src="this.playersVisiting[7].img" style="height: 60px">
          <p>{{ this.playersVisiting[7].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[7].position }}</div>
          <div class="player-position">{{ this.playersVisiting[7].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(8)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[8]  !== undefined">
          <p>{{ this.players[8].name }}</p>
          <img :src="this.players[8].img" style="height: 60px">
          <p>{{ this.players[8].surname }}</p>
          <div class="player-position">{{ this.players[8].position }}</div>
          <div class="player-position">{{ this.players[8].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[8]  !== undefined">
          <p>{{ this.playersVisiting[8].name }}</p>
          <img :src="this.playersVisiting[8].img" style="height: 60px">
          <p>{{ this.playersVisiting[8].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[8].position }}</div>
          <div class="player-position">{{ this.playersVisiting[8].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(9)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[9]  !== undefined">
          <p>{{ this.players[9].name }}</p>
          <img :src="this.players[9].img" style="height: 60px">
          <p>{{ this.players[9].surname }}</p>
          <div class="player-position">{{ this.players[9].position }}</div>
          <div class="player-position">{{ this.players[9].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[9]  !== undefined">
          <p>{{ this.playersVisiting[9].name }}</p>
          <img :src="this.playersVisiting[9].img" style="height: 60px">
          <p>{{ this.playersVisiting[9].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[9].position }}</div>
          <div class="player-position">{{ this.playersVisiting[9].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
  </div>

  <h3 class="naglowek-piatka">Trzecia piątka</h3>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(10)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[10]  !== undefined">
          <p>{{ this.players[10].name }}</p>
          <img :src="this.players[10].img" style="height: 60px">
          <p>{{ this.players[10].surname }}</p>
          <div class="player-position">{{ this.players[10].position }}</div>
          <div class="player-position">{{ this.players[10].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[10]  !== undefined">
          <p>{{ this.playersVisiting[10].name }}</p>
          <img :src="this.playersVisiting[10].img" style="height: 60px">
          <p>{{ this.playersVisiting[10].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[10].position }}</div>
          <div class="player-position">{{ this.playersVisiting[10].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(11)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[11]  !== undefined">
          <p>{{ this.players[11].name }}</p>
          <img :src="this.players[11].img" style="height: 60px">
          <p>{{ this.players[11].surname }}</p>
          <div class="player-position">{{ this.players[11].position }}</div>
          <div class="player-position">{{ this.players[11].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[11]  !== undefined">
          <p>{{ this.playersVisiting[11].name }}</p>
          <img :src="this.playersVisiting[11].img" style="height: 60px">
          <p>{{ this.playersVisiting[11].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[11].position }}</div>
          <div class="player-position">{{ this.playersVisiting[11].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(12)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[12]  !== undefined">
          <p>{{ this.players[12].name }}</p>
          <img :src="this.players[12].img" style="height: 60px">
          <p>{{ this.players[12].surname }}</p>
          <div class="player-position">{{ this.players[12].position }}</div>
          <div class="player-position">{{ this.players[12].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[12]  !== undefined">
          <p>{{ this.playersVisiting[12].name }}</p>
          <img :src="this.playersVisiting[12].img" style="height: 60px">
          <p>{{ this.playersVisiting[12].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[12].position }}</div>
          <div class="player-position">{{ this.playersVisiting[12].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(13)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[13]  !== undefined">
          <p>{{ this.players[13].name }}</p>
          <img :src="this.players[13].img" style="height: 60px">
          <p>{{ this.players[13].surname }}</p>
          <div class="player-position">{{ this.players[13].position }}</div>
          <div class="player-position">{{ this.players[13].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[13]  !== undefined">
          <p>{{ this.playersVisiting[13].name }}</p>
          <img :src="this.playersVisiting[13].img" style="height: 60px">
          <p>{{ this.playersVisiting[13].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[13].position }}</div>
          <div class="player-position">{{ this.playersVisiting[13].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(14)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[14]  !== undefined">
          <p>{{ this.players[14].name }}</p>
          <img :src="this.players[14].img" style="height: 60px">
          <p>{{ this.players[14].surname }}</p>
          <div class="player-position">{{ this.players[14].position }}</div>
          <div class="player-position">{{ this.players[14].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[14]  !== undefined">
          <p>{{ this.playersVisiting[14].name }}</p>
          <img :src="this.playersVisiting[14].img" style="height: 60px">
          <p>{{ this.playersVisiting[14].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[14].position }}</div>
          <div class="player-position">{{ this.playersVisiting[14].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
  </div>

  <h3 class="naglowek-piatka">Czwarta piątka</h3>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(15)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[15]  !== undefined">
          <p>{{ this.players[15].name }}</p>
          <img :src="this.players[15].img" style="height: 60px">
          <p>{{ this.players[15].surname }}</p>
          <div class="player-position">{{ this.players[15].position }}</div>
          <div class="player-position">{{ this.players[15].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[15]  !== undefined">
          <p>{{ this.playersVisiting[15].name }}</p>
          <img :src="this.playersVisiting[15].img" style="height: 60px">
          <p>{{ this.playersVisiting[15].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[15].position }}</div>
          <div class="player-position">{{ this.playersVisiting[15].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(16)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[16]  !== undefined">
          <p>{{ this.players[16].name }}</p>
          <img :src="this.players[16].img" style="height: 60px">
          <p>{{ this.players[16].surname }}</p>
          <div class="player-position">{{ this.players[16].position }}</div>
          <div class="player-position">{{ this.players[16].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[16]  !== undefined">
          <p>{{ this.playersVisiting[16].name }}</p>
          <img :src="this.playersVisiting[16].img" style="height: 60px">
          <p>{{ this.playersVisiting[16].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[16].position }}</div>
          <div class="player-position">{{ this.playersVisiting[16].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(17)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[17]  !== undefined">
          <p>{{ this.players[17].name }}</p>
          <img :src="this.players[17].img" style="height: 60px">
          <p>{{ this.players[17].surname }}</p>
          <div class="player-position">{{ this.players[17].position }}</div>
          <div class="player-position">{{ this.players[17].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[17]  !== undefined">
          <p>{{ this.playersVisiting[17].name }}</p>
          <img :src="this.playersVisiting[17].img" style="height: 60px">
          <p>{{ this.playersVisiting[17].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[17].position }}</div>
          <div class="player-position">{{ this.playersVisiting[17].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(18)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[18]  !== undefined">
          <p>{{ this.players[18].name }}</p>
          <img :src="this.players[18].img" style="height: 60px">
          <p>{{ this.players[18].surname }}</p>
          <div class="player-position">{{ this.players[18].position }}</div>
          <div class="player-position">{{ this.players[18].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[18]  !== undefined">
          <p>{{ this.playersVisiting[18].name }}</p>
          <img :src="this.playersVisiting[18].img" style="height: 60px">
          <p>{{ this.playersVisiting[18].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[18].position }}</div>
          <div class="player-position">{{ this.playersVisiting[18].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(19)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[19]  !== undefined">
          <p>{{ this.players[19].name }}</p>
          <img :src="this.players[19].img" style="height: 60px">
          <p>{{ this.players[19].surname }}</p>
          <div class="player-position">{{ this.players[19].position }}</div>
          <div class="player-position">{{ this.players[19].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[19]  !== undefined">
          <p>{{ this.playersVisiting[19].name }}</p>
          <img :src="this.playersVisiting[19].img" style="height: 60px">
          <p>{{ this.playersVisiting[19].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[19].position }}</div>
          <div class="player-position">{{ this.playersVisiting[19].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
  </div>

  <h3 class="naglowek-piatka">Bramkarze</h3>
  <div class="uk-text-center">
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(20)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[20]  !== undefined">
          <p>{{ this.players[20].name }}</p>
          <img :src="this.players[20].img" style="height: 60px">
          <p>{{ this.players[20].surname }}</p>
          <div class="player-position">{{ this.players[20].position }}</div>
          <div class="player-position">{{ this.players[20].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[20]  !== undefined">
          <p>{{ this.playersVisiting[20].name }}</p>
          <img :src="this.playersVisiting[20].img" style="height: 60px">
          <p>{{ this.playersVisiting[20].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[20].position }}</div>
          <div class="player-position">{{ this.playersVisiting[20].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
    <div class="dodawanie-skladu" @click="togglePlayer()" @mouseenter="changeIndex(21)">
      <div v-if="teamType === 'home'">
        <div v-if="this.players[21]  !== undefined">
          <p>{{ this.players[21].name }}</p>
          <img :src="this.players[21].img" style="height: 60px">
          <p>{{ this.players[21].surname }}</p>
          <div class="player-position">{{ this.players[21].position }}</div>
          <div class="player-position">{{ this.players[21].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
      <div v-else>
        <div v-if="this.playersVisiting[21]  !== undefined">
          <p>{{ this.playersVisiting[21].name }}</p>
          <img :src="this.playersVisiting[21].img" style="height: 60px">
          <p>{{ this.playersVisiting[21].surname }}</p>
          <div class="player-position">{{ this.playersVisiting[21].position }}</div>
          <div class="player-position">{{ this.playersVisiting[21].playerNumber }}</div>
        </div>
        <div v-else>
          <span uk-icon="icon:  plus; ratio: 2"/>
        </div>
      </div>
    </div>
  </div>


  <div v-if="teamType === 'home'">
    <div id="sklad" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title">Wybierz zawodnika</h2>
        <button @click="refreshSelection" class="uk-modal-close-default" type="button" uk-close></button>
        <SquadMultiSelect
            ref="squad-multi-select"
            id="sklad-multi-select"
            :team-id="teamId"
            :provide-players="false"
        />
        <PlayerPositionView ref="position-multi-select" id="pozycja-multi-select"/>
        <label for="player-number" class="uk-label" style="margin-top: 10px;">Numer zawodnika</label>
        <PlayerNumberView name="player-number" v-model:player-number.number="playerNumber"
                          style="margin-top: 10px;"/>
        <button type="submit" class="uk-button uk-button-default uk-width-1-1" @click="savePlayer()">Zapisz
        </button>
      </div>
    </div>
  </div>
  <div v-else>
    <div id="sklad-goscie" uk-modal>
      <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title">Wybierz zawodnika</h2>
        <button @click="refreshSelection" class="uk-modal-close-default" type="button" uk-close></button>
        <SquadMultiSelect
            ref="squad-multi-select-goscie"
            id="sklad-multi-select-goscie"
            :team-id="teamId"
            :provide-players="false"
        />
        <PlayerPositionView ref="position-multi-select-goscie" id="pozycja-multi-select-goscie"/>
        <label for="player-number" class="uk-label" style="margin-top: 10px;">Numer zawodnika</label>
        <PlayerNumberView name="player-number" v-model:player-number.number="playerNumber"
                          style="margin-top: 10px;"/>
        <button type="submit" class="uk-button uk-button-default uk-width-1-1" @click="savePlayer()">Zapisz
        </button>
      </div>
    </div>
  </div>


</template>

<script>
import UIkit from "uikit";
import {mapActions, mapGetters} from "vuex";
import SquadMultiSelect from "@/views/Squad/SquadMultiSelect";
import PlayerPositionView from "@/views/Inputs/Position";
import PlayerNumberView from "@/views/Inputs/PlayerNumber";

export default {
  name: "MatchSquadViewPanel",
  components: {SquadMultiSelect, PlayerPositionView, PlayerNumberView},
  props: ['teamImage', 'teamId', 'teamType','playersHomeProp','playersVisitingProp','matchId'],
   created() {
    if(this.teamType === 'home'){
       this.SetPlayers(this.playersHomeProp)
    }else if (this.teamType === 'visiting'){
       this.SetPlayersVisiting(this.playersVisitingProp)
    }

  },
  data() {
    return {
      playerNumber: null
    }
  },
  computed: {
    ...mapGetters({
      playerIndex: "StatePlayerIndex",
      playerTemp: "StatePlayerTemp",
      players: "StatePlayers",
      playersVisiting: "StatePlayersVisiting",
      fiveIndex: "StateFiveIndex"
    }),
  },
  methods: {
    ...mapActions(['SetPlayerIndex', 'SetPlayerTemp', "SetPlayer", "SetPlayerVisiting", "SetPlayers", "SetPlayersVisiting", "SetFiveIndex"]),
    refreshSelection() {
      this.$refs["position-multi-select"].value = null;
      this.$refs["squad-multi-select"].value = null;
      this.playerNumber = null;
    },
    async savePlayer() {
      let canAddPlayer = true;
      if (this.teamType === 'home') {
        if (this.$refs["position-multi-select"].value === null) {
          canAddPlayer = false;
          UIkit.notification({
            message: 'Nie wybrano pozycji!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
        if (this.$refs["squad-multi-select"].value == null) {
          canAddPlayer = false;
          UIkit.notification({
            message: 'Nie wybrano zawodnika!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
      } else if (this.teamType === 'visiting') {
        if (this.$refs["position-multi-select-goscie"].value === null) {
          canAddPlayer = false;
          UIkit.notification({
            message: 'Nie wybrano pozycji!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
        if (this.$refs["squad-multi-select-goscie"].value == null) {
          canAddPlayer = false;
          UIkit.notification({
            message: 'Nie wybrano zawodnika!',
            status: 'warning',
            pos: 'top-center',
            timeout: 10000
          });
        }
      }

      if (canAddPlayer) {
        let playerArray = [];
        let playersToSelectHome = null;
        let playersToSelectVisiting = null;
        let player = null;
        if (this.teamType === 'home') {
          player = this.$refs["squad-multi-select"].value;
          let position = this.$refs["position-multi-select"].value;
          player.position = position.position;
          playerArray.push(player)
          playersToSelectHome = this.$refs['squad-multi-select'].$data.options.filter(e => {
            return !playerArray.some(item => item.id === e.id);
          });
        } else if (this.teamType === 'visiting') {
          player = this.$refs["squad-multi-select-goscie"].value;
          let position = this.$refs["position-multi-select-goscie"].value;
          player.position = position.position;
          playerArray.push(player)
          playersToSelectVisiting = this.$refs['squad-multi-select-goscie'].$data.options.filter(e => {
            return !playerArray.some(item => item.id === e.id);
          });
        }


        // Sprawdzanie czy zawodnik już istnieje
        if (this.teamType === 'home') {
          this.players.forEach((p) => {
            if (p !== undefined)
              if (p.id === player.id) {
                canAddPlayer = false;
                UIkit.notification({
                  message: "Ten zawodnik już istnieje w składzie",
                  status: "warning",
                  pos: "top-center"
                })
              }
          })
        } else if (this.teamType === 'visiting') {
          this.playersVisiting.forEach((p) => {
            if (p !== undefined)
              if (p.id === player.id) {
                canAddPlayer = false;
                UIkit.notification({
                  message: "Ten zawodnik już istnieje w składzie",
                  status: "warning",
                  pos: "top-center"
                })
              }
          })
        }

        // Przypisanie zawodnika
        if (canAddPlayer) {
          if (this.teamType === 'home') {
            player.playerNumber = this.playerNumber;
            await this.SetPlayerTemp(player)
            await this.SetPlayer({playerTemp: this.playerTemp, index: this.playerIndex})
          } else if (this.teamType === 'visiting') {
            player.playerNumber = this.playerNumber;
            await this.SetPlayerTemp(player)
            await this.SetPlayerVisiting({playerTemp: this.playerTemp, index: this.playerIndex})
          }

        }
        if (this.teamType === 'home') {
          this.$refs["squad-multi-select"].$data.options = playersToSelectHome;
          this.$refs["squad-multi-select"].$forceUpdate();
          this.$refs["position-multi-select"].value = null;
        } else if (this.teamType === 'visiting') {
          this.$refs["squad-multi-select-goscie"].$data.options = playersToSelectVisiting;
          this.$refs["squad-multi-select-goscie"].$forceUpdate();
          this.$refs["position-multi-select-goscie"].value = null;
        }


      }

    },

    async changeIndex(index) {
      this.keyValue++;
      await this.SetPlayerIndex(index)
    },
    togglePlayer() {
      if (this.teamType === 'home') {
        const modal = UIkit.modal("#sklad")
        modal.toggle();
      } else if (this.teamType === 'visiting') {
        const modal = UIkit.modal("#sklad-goscie")
        modal.toggle();
      }

    },
  }
}
</script>

<style scoped lang="less">
.dodawanie-skladu {
  background-color: #a6efff;
  padding: 10px;
  display: inline-block;
  margin-left: 10px;
  margin-top: 10px;

  &:hover {
    color: #a6efff;
    background-color: #0e6dcd;
  }

}

#przycisk-zapisz-sklad {
  margin-bottom: 50px;
}

.uk-select {
  display: inline-block !important;
}


.uk-modal-title {
  background: #a6efff;
  padding: 10px;
  border-top-right-radius: 25px;
  border-bottom-left-radius: 25px;
  color: #009fb6;
}

#sklad-dialog {
  background: #006394;
}

#sklad-ikona {
  margin-left: 20px;
}

#przycisk-zamkniecie {
  padding: 0;
  margin-top: 15px;
}

.uk-button {
  margin-left: 10px
}

.player-position {
  color: #0e6dcd;
  font-size: x-large;
  font-weight: bold;

  &:hover {
    color: #a6efff;
  }
}

#tlh-mecz-sklady-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
  border-top-width: 10px;
  border-color: #006394;

}
.naglowek-piatka {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
  border-top-width: 10px;
  border-color: #006394;
}
</style>