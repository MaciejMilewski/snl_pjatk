// store/modules/auth.js


const state = {
    filter: {
        isVisible: true,
    },
};
const getters = {
    isVisible: (state) => !!state.filter.isVisible,
    StateFilter: (state) => state.filter,
};
const actions = {
    async SetFilterVisible({ commit }) {
        commit('FilterVisible');
    },
    async SetFilterInvisible({ commit }) {
        commit('FilterInvisible');
    },

};
const mutations = {
    FilterVisible(state) {
        state.filter.isVisible = true;
    },
    FilterInvisible(state){
        state.filter.isVisible = false;
    },
};

export default {
    state,
    getters,
    actions,
    mutations,
};
