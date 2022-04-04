import { apiInstance, apiInstance2 } from "./index.js";

function getMainRecommend(userId, success, fail) {
  api.get(`/recommend/user/${userId}`).then(success).catch(fail);
}

function getPerformingRecommend(success, fail) {
  apiInstance().get("/recommend").then(success).catch(fail);
}

export { getMainRecommend, getPerformingRecommend };
