import { apiInstance } from "./index.js";

const api = apiInstance();

function getMainRecommend(userId, success, fail) {
  api.get(`/recommend/user/${userId}`).then(success).catch(fail);
}

function getSimilarRecommend(showId, success, fail) {
  api.get(`/recommend/${showId}`).then(success).catch(fail);
}

export { getMainRecommend, getSimilarRecommend };
