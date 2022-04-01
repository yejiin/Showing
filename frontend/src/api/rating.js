import { apiInstance2 } from "./index.js";

const api2 = apiInstance2();

// 별점 조회
function getRating(userId, performanceId, success, fail) {
  api2.get(`/rating/${userId}?performanceId=${performanceId}`).then(success).catch(fail);
}

// 별점 추가
function addRating(rating, success, fail) {
  api2.post(`/rating`, JSON.stringify(rating)).then(success).catch(fail);
}

// 별점 삭제
function deleteRating(starId, success, fail) {
  api2.delete(`/rating/${starId}`).then(success).catch(fail);
}

// 별점 수정
function modifyRating(rating, success, fail) {
  api2.put(`/rating`, JSON.stringify(rating)).then(success).catch(fail);
}

export { getRating, addRating, deleteRating, modifyRating };
