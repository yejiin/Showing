import { apiInstance2 } from "./index.js";

function getMainRecommend(userId, success, fail) {
  apiInstance2().get(`/recommend/users/${userId}`).then(success).catch(fail);
}

export { getMainRecommend };
