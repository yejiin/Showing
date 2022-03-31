import { apiInstance, apiInstance2 } from "./index.js";

const api = apiInstance();
const api2 = apiInstance2();

// 공연별 리뷰 미리보기
function getAllShowReview(performanceId, success, fail) {
  api.get(`/reviews/performances/${performanceId}`).then(success).catch(fail);
}

function getMyReview(userId, success, fail) {
  api2.get(`/reviews/users/${userId}`).then(success).catch(fail);
}

function addMyReview(review, success, fail) {
  api2.post(`/reviews`, JSON.stringify(review)).then(success).catch(fail);
}

function deleteMyReview(reviewId, success, fail) {
  api2.delete(`/reviews/${reviewId}`).then(success).catch(fail);
}

function modifyMyReview(reviewId, review, success, fail) {
  api2.put(`/reviews/${reviewId}`, JSON.stringify(review)).then(success).catch(fail);
}

function getAllSeasonReview(seasonId, success, fail){
  api.get(`/reviews/seasons/${seasonId}`).then(success).catch(fail);
}

function getMyShowReview(performanceId, userId, success, fail){
  api2.get(`/reviews/performances/${performanceId}/${userId}`).then(success).catch(fail);
}
export { getAllShowReview, getMyReview, addMyReview, deleteMyReview, modifyMyReview, getAllSeasonReview, getMyShowReview };
