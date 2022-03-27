import { apiInstance } from "./index.js";

const api = apiInstance();

function listMainRecommend(userId, success, fail) {
  api.get(`/recommend/user/${userId}`).then(success).catch(fail);
}

function listSimilarRecommend(showId, success, fail) {
  api.get(`/recommend/${showId}`).then(success).catch(fail);
}

export { listMainRecommend, listSimilarRecommend };
