// store/modules/auth.js

import axios from 'axios';

const state = {
    user: null,
    playerIndex: 0,
    articles: null,
    players: [{}],
    playersVisiting: [{}],
    playersFive: [{}],
    fiveIndex: null,
    strategies: [],
    background: false,
};
const getters = {
    isAuthenticated: (state) => !!state.user,
    StateUser: (state) => state.user,
    StatePlayers: (state) => state.players,
    StatePlayersVisiting: (state) => state.playersVisiting,
    StatePlayerIndex: (state) => state.playerIndex,
    StatePlayerTemp: (state) => state.playerTemp,
    StatePlayerFiveTemp: (state) => state.playerFiveTemp,
    StatePlayersFive: (state) => state.playersFive,
    StateFiveIndex: (state) => state.fiveIndex,
    StateBackground: (state) => state.background,
    StateStrategies: (state) => state.strategies,
};
const actions = {
    async SetBackground({commit}){
        await commit('setNavbarBackground')
    },
    async SetBackgroundOff({commit}){
        await commit('setNavbarBackgroundOff')
    },
    async SetFiveIndex({commit}, fiveIndex){
      await commit('setFiveIndex', fiveIndex)
    },
    async SetPlayerFiveTemp({commit},playerTemp){
      await commit('setPlayerFiveTemp', playerTemp)
    },
    async SetPlayerFive({commit}, payload){
      await commit('setPlayerFive', payload.playerTemp, payload.index)
    },
    async SetPlayers({commit}, players){
        await commit('setPlayers', players)
    },
    async SetPlayersVisiting({commit}, players){
        await commit('setPlayersVisiting', players)
    },
    async SetStrategies({commit}, strategies){
        await commit('setStrategies', strategies)
    },
    async SetPlayer({commit}, payload){
      await commit('setPlayer',payload.playerTemp,payload.index)
    },
    async SetPlayerVisiting({commit}, payload){
        await commit('setPlayerVisiting',payload.playerTemp,payload.index)
    },
    async SetPlayerTemp({commit}, playerTemp){
        await commit('setPlayerTemp',playerTemp)
    },
    async SetPlayerIndex({commit},playerIndex){
        await commit('setPlayerIndex',playerIndex)
    },
    //Akcja - logowanie
    async LogIn({commit}, user) {
        //Nowy obiekt userObj
        const userObj = {}
        //Przypisanie obiektu do nowego obiektu
        user.forEach((value, key) => userObj[key] = value)
        let userState = {}
        userState.username = userObj['username']

        // Json
        const jsonCredentials = JSON.stringify({
            username: userObj['username'],
            password: userObj['password'],
        })
        let userData = {};
        //Zapytanie do backend
        await axios.post("http://localhost:8080/api/auth/signin", jsonCredentials, {
            headers: {
                'Content-Type': `application/json`,
            }
        }).then(response => {
             userData.email = response.data.email
            userData.id = response.data.id;
             switch(response.data.roles[0]){
                 // Przypisanie grupy użytkownika
                 case 'ROLE_TRAINER':
                     userState.group = 'trener'
                     break;
                 case 'ROLE_MEDIC_OR_JUDGE':
                     userState.group = 'sedzia_medyk'
                     break;
                 case 'ROLE_ADMIN':
                     userState.group = 'deweloper'
                     break;
                 case 'ROLE_SECRETARY':
                     userState.group = 'sekretarz'
                     break;
             }
             userData.token = response.data.token
            userData.teamId = response.data.teamId

        })
        userState.userInfo = userData;


        await commit('setUser', userState);
    },

    //Akcja - Zapytanie o artykuły
    async GetArticles({commit}){
        const response = await axios.get('articles')
        commit('setArticles', response.data)
    },

    //Akcja - Tworzenie nowego artykułu
    async CreateArticle({dispatch}, article) {
        await axios.post('articles', article);
        return await dispatch('GetArticles');
    },

    //Akcja - wylogowanie
    async LogOut({commit}) {
        const user = {};
        commit('LogOut', user);
    },
};
const mutations = {
    setNavbarBackgroundOff(state){
        state.background = false;
    },
    setNavbarBackground(state){
      state.background = true;
    },
    setStrategies(state, strategies){
        state.strategies = strategies
    },
    setFiveIndex(state, fiveIndex){
      state.fiveIndex = fiveIndex;
    },
    setPlayerFiveTemp(state,playerTemp){
      state.playerFiveTemp = playerTemp
    },
    setPlayerFive(state){
      state.playersFive[state.playerIndex] = state.playerFiveTemp;
    },
    setPlayer(state){
      state.players[state.playerIndex] = state.playerTemp;
    },
    setPlayerVisiting(state){
        state.playersVisiting[state.playerIndex] = state.playerTemp;
    },
    setPlayers(state, players){
        state.players = players
    },
    setPlayersVisiting(state, players){
        state.playersVisiting = players
    },
    setPlayerTemp(state,playerTemp){
        console.log("setPlayerTemp", playerTemp)
        state.playerTemp = playerTemp
    },
    setPlayerIndex(state, playerIndex){
      state.playerIndex = playerIndex;
    },
    setUser(state, user) {
        state.user = user;
    },
    setArticles(state, articles) {
        state.articles = articles;
    },
    LogOut(state) {
        state.user = null;
        state.articles = null;
    },
};
const base64Code = function (str) {
    // Zabezpieczenie przed "podglądaniem" zapytania
    //https://developer.mozilla.org/en-US/docs/Glossary/Base64
    return window.btoa(unescape(encodeURIComponent(str)));
}
export default {
    state,
    getters,
    actions,
    mutations,
    base64Code,
};
