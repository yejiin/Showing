import { apiInstance } from "./index.js";

const api = apiInstance();

function getMainRecommend(userId, success, fail) {
  api.get(`/recommend/users/${userId}`).then(success).catch(fail);
}

function getSimilarRecommend(performanceId, success, fail) {
  api.get(`/recommend/${performanceId}`).then(success).catch(fail);
}

export { getMainRecommend, getSimilarRecommend };
