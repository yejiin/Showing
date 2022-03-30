import { getNaverToken, getNaverUser, getKakaoToken, getKakaoUser } from "@/api/user.js";

const userStore = {
  namespaced: true,
  state: {
    userInfo: {
      userId: 0,
      nickName: "",
      userImage: "",
    },
    isLogin: false,
  },

  getters: {},

  mutations: {
    SET_USER_INFO: (state, data) => {
      state.userInfo.id = data.id;
      state.userInfo.nickName = data.nickName;
      state.userInfo.userImage = data.userImage;
    },

    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
  },

  actions: {
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

    async findNaverUser({ commit }, naverToken) {
      await getNaverUser(
        naverToken,
        (response) => {
          if (response.data.statusCode == 201) {
            console.log("네이버 로그인 성공");

            commit("SET_USER_INFO", response.data.data);
            commit("SET_IS_LOGIN", true);

            sessionStorage.setItem("access-token", response.data.data.accessToken);
          }
        },
        (error) => {
          commit("SET_IS_LOGIN", false);
          console.log("네이버 로그인 실패");
          console.log(error);
        }
      );
    },

    async findKakaoToken({ dispatch }, code) {
      await getKakaoToken(
        code,
        (response) => {
          if (response.data.statusCode == 200) {
            console.log("카카오 Access Token 생성 성공");
            console.log(response);

            dispatch("findKakaoUser", response.data.data);
          }
        },
        (error) => {
          console.log("카카오 Access Token 생성 실패");
          console.log(error.response);
        }
      );
    },

    async findKakaoUser({ commit }, kakaoToken) {
      await getKakaoUser(
        kakaoToken,
        (response) => {
          if (response.data.statusCode == 201) {
            console.log("카카오 로그인 성공");

            commit("SET_USER_INFO", response.data.data);
            commit("SET_IS_LOGIN", true);

            sessionStorage.setItem("access-token", response.data.accessToken);
          }
        },
        (error) => {
          console.log("카카오 로그인 실패");
          console.log(error.response);

          commit("SET_IS_LOGIN", false);
        }
      );
    },
  },
};

export default userStore;
