import { apiInstance2 } from "./index.js";

const api2 = apiInstance2();

// 별점 조회
function getRating(userId, performanceId, success, fail) {
  console.log("getRating");
  api2.get(`/rating/${userId}?performanceId=${performanceId}`).then(success).catch(fail);
}

function addRating(rating, success, fail) {
  console.log("addRating");
  api2.post(`/rating`, JSON.stringify(rating)).then(success).catch(fail);
}

function deleteRating(starId, success, fail) {
  console.log("deleteRating");
  api2.delete(`/rating/${starId}`).then(success).catch(fail);
}

function modifyRating(rating, success, fail) {
  console.log("modifyRating");
  api2.put(`/rating`, JSON.stringify(rating)).then(success).catch(fail);
}

export { getRating, addRating, deleteRating, modifyRating };
