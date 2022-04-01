import { getNaverToken, getNaverUser, getKakaoToken, getKakaoUser } from "@/api/user.js";

const reviewStore = {
  namespaced: true,
  state: {
      modals:{
          myReviewList : false,
          writeReview : false,
          OtherReviews : false,
          reviewDetail : false,
          modifyReview : false,
      },
      reviewInfo:{
        reviewId : 0,
      }
  },

  getters: {
      
  },

  mutations: {
    SET_MY_REVIEW_LIST_STATE : (state, status) => {
        state.modals.myReviewList = status
        console.log("mutations "+status)
        console.log(state.modals.myReviewList)
    },
    SET_WRITE_REVIEW_STATE : (state, status) =>{
      state.modals.writeReview = status
      console.log("mutations "+status)
      console.log(state.modals.writeReview)
    },
    SET_MODIFY_REVIEW_STATE : (state, status) =>{
      state.modals.modifyReview = status
      console.log("mutations "+status)
      console.log(state.modals.modifyReview)
    },
    SET_REVIEW_ID : (state, id) =>{
      state.reviewInfo.reviewId = id
      console.log("mutations "+id)
      console.log(state.reviewInfo.reviewId)
    }

  },

  actions: {
    setMyReviewListModalState({ commit }, status){
        console.log("actions도 옴" + status)
        commit("SET_MY_REVIEW_LIST_STATE", status);
    },
    setWriteReviewModalState({commit}, status){
      console.log("actions도 옴" + status)
        commit("SET_WRITE_REVIEW_STATE", status);
    },
    setModifyReviewModalState({commit}, status){
      console.log("actions도 옴" + status)
        commit("SET_MODIFY_REVIEW_STATE", status);
    },
    setReviewId({commit}, id){
      console.log("actions도 옴" + id)
        commit("SET_REVIEW_ID", id);
    },
    async findNaverToken({ dispatch }, code, state) {
      await getNaverToken(
        code,
        state,
        (response) => {
          if (response.data.statusCode == 200) {
            console.log("네이버 Access Token 생성 성공");
            console.log(response);

            dispatch("findNaverUser", response.data.data);
          }
        },
        (error) => {
          console.log("네이버 Access Token 생성 실패");
          console.log(error);
        }
      );
    },

  },
};

export default reviewStore;
