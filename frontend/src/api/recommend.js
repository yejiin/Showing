import { apiInstance2 } from "./index.js";

const api = apiInstance2();

function getMainRecommend(userId, success, fail) {
  api.get(`/recommend/users/${userId}`).then(success).catch(fail);
}

export { getMainRecommend };
