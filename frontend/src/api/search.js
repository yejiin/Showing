import { apiInstance2 } from "./index.js";

const api2 = apiInstance2();

// 검색
function getContentsList(keyword, success, fail) {
  api2.get(`/search?keyword=${keyword}`).then(success).catch(fail);
}

export { getContentsList };
