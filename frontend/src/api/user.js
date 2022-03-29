import { apiInstance } from "./index.js";

const api = apiInstance();

async function getUser(userId, success, fail) {
  await api.get(`/users/${userId}`).then(success).catch(fail);
}

async function modifyUser(user, success, fail) {
  await api.put(`/users`, JSON.stringify(user)).then(success).catch(fail);
}

async function getNaverToken(code, state, success, fail) {
  await api.get(`/users/naver/token?code=${code}&state=${state}`).then(success).catch(fail);
}

async function getNaverUser(naverToken, success, fail) {
  await api.get(`/users/naver/login?accessToken=${naverToken}`).then(success).catch(fail);
}

async function getKakaoToken(code, success, fail) {
  await api.get(`/users/kakao/token?code=${code}`).then(success).catch(fail);
}

async function getKakaoUser(kakaoToken, success, fail) {
  await api.get(`/users/kakao/login?accessToken=${kakaoToken}`).then(success).catch(fail);
}

export { getUser, modifyUser, getNaverToken, getNaverUser, getKakaoToken, getKakaoUser };
