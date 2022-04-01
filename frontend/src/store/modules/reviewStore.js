const reviewStore = {
  namespaced: true,
  state: {
    modals: {
      myReviewList: false,
      writeReview: false,
      OtherReviews: false,
      reviewDetail: false,
<<<<<<< Updated upstream
      modifyReview: false,
      myReview: false,
    },
    reviewInfo: {
      reviewId: 0,
    },
=======
      myReview: false,
    },
>>>>>>> Stashed changes
  },

  getters: {},

  mutations: {
    SET_MY_REVIEW_LIST_STATE: (state, status) => {
      state.modals.myReviewList = status;
      console.log("mutations " + status);
      console.log(state.modals.myReviewList);
    },
    SET_WRITE_REVIEW_STATE: (state, status) => {
      state.modals.writeReview = status;
      console.log("mutations " + status);
      console.log(state.modals.writeReview);
    },
    SET_REVIEW_STATE: (state, status) => {
      state.modals.myReview = status;
      console.log("mutations " + status);
      console.log(state.modals.myReview);
    },
<<<<<<< Updated upstream
    SET_MODIFY_REVIEW_STATE: (state, status) => {
      state.modals.modifyReview = status;
      console.log("mutations " + status);
      console.log(state.modals.modifyReview);
    },
    SET_REVIEW_ID: (state, id) => {
      state.reviewInfo.reviewId = id;
      console.log("mutations " + id);
      console.log(state.reviewInfo.reviewId);
    },
=======
>>>>>>> Stashed changes
  },

  actions: {
    setMyReviewListModalState({ commit }, status) {
      console.log("actions도 옴" + status);
      commit("SET_MY_REVIEW_LIST_STATE", status);
    },
    setWriteReviewModalState({ commit }, status) {
      console.log("actions도 옴" + status);
      commit("SET_WRITE_REVIEW_STATE", status);
    },
<<<<<<< Updated upstream
    setModifyReviewModalState({ commit }, status) {
      console.log("actions도 옴" + status);
      commit("SET_MODIFY_REVIEW_STATE", status);
    },
    setReviewId({ commit }, id) {
      console.log("actions도 옴" + id);
      commit("SET_REVIEW_ID", id);
    },
=======
>>>>>>> Stashed changes
    setReviewModalState({ commit }, status) {
      console.log("actions도 옴" + status);
      commit("SET_REVIEW_STATE", status);
    },
  },
};

export default reviewStore;
