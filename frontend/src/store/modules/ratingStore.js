const ratingStore = {
  namespaced: true,
  state: {
    starId: 0,
    rating: 0,
  },

  getters: {
    rating({ rating }) {
      return rating;
    },
    starId({ starId }) {
      return starId;
    },
  },

  mutations: {
    SET_STAR_ID_STATE: (state, status) => {
      state.starId = status;
    },
    SET_RATING_STATE: (state, status) => {
      state.rating = status;
    },
  },

  actions: {
    setMyStarIdState({ commit }, status) {
      commit("SET_STAR_ID_STATE", status);
    },
    setMyRatingState({ commit }, status) {
      commit("SET_RATING_STATE", status);
    },
  },
};

export default ratingStore;
