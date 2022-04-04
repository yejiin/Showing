const reviewStore = {
  namespaced: true,
  state: {
    modals: {
      myReviewList: false,
      writeReview: false,
      OtherReviews: false,
      reviewDetail: false,
      modifyReview: false,
      myReview: {
        status: false,
        reviewId: 0,
      },
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
    SET_REVIEW_STATE: (state, data) => {
      state.modals.myReview.status = data.status;
      state.modals.myReview.reviewId = data.reviewId;

      console.log("mutations ", data.status, " ", data.reviewId);

      console.log(state.modals.myReview);
    },
    SET_MODIFY_REVIEW_STATE: (state, status) => {
      state.modals.modifyReview = status;
    },
    SET_REVIEW_ID: (state, id) => {
      state.reviewInfo.reviewId = id;
    },
    SET_DETAIL_STATE: (state, status) => {
      state.modals.reviewDetail = status;
      console.log(state.modals.reviewDetail);
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
    setReviewModalState({ commit }, { status, reviewId }) {
      console.log("actions도 옴", status, " ", reviewId);
      commit("SET_REVIEW_STATE", { status, reviewId });
    },
    setReviewDetailModalState({ commit }, status) {
      commit("SET_DETAIL_STATE", status);
    },
  },
};

export default reviewStore;
