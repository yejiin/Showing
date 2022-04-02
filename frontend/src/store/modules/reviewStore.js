const reviewStore = {
  namespaced: true,
  state: {
    modals: {
      myReviewList: false,
      writeReview: false,
      OtherReviews: false,
      reviewDetail: false,
      modifyReview: false,
      myReview: false,
    },
    reviewInfo: {
      reviewId: 0,
    },
  },

  getters: {},

  mutations: {
    SET_MY_REVIEW_LIST_STATE: (state, status) => {
      state.modals.myReviewList = status;
    },
    SET_WRITE_REVIEW_STATE: (state, status) => {
      state.modals.writeReview = status;
    },
    SET_REVIEW_STATE: (state, status) => {
      state.modals.myReview = status;
    },
    SET_MODIFY_REVIEW_STATE: (state, status) => {
      state.modals.modifyReview = status;
    },
    SET_REVIEW_ID: (state, id) => {
      state.reviewInfo.reviewId = id;
    },
  },

  actions: {
    setMyReviewListModalState({ commit }, status) {
      commit("SET_MY_REVIEW_LIST_STATE", status);
    },
    setWriteReviewModalState({ commit }, status) {
      commit("SET_WRITE_REVIEW_STATE", status);
    },
    setModifyReviewModalState({ commit }, status) {
      commit("SET_MODIFY_REVIEW_STATE", status);
    },
    setReviewId({ commit }, id) {
      commit("SET_REVIEW_ID", id);
    },
    setReviewModalState({ commit }, status) {
      commit("SET_REVIEW_STATE", status);
    },
  },
};

export default reviewStore;
