import { apiInstance, apiInstance2 } from "./index.js";

const api = apiInstance();

// 공연별 리뷰 미리보기
function getAllShowReview(performanceId, success, fail) {
  api.get(`/reviews/performances/${performanceId}`).then(success).catch(fail);
}

function getMyReview(userId, success, fail) {
  apiInstance2().get(`/reviews/users/${userId}`).then(success).catch(fail);
}

function addMyReview(review, success, fail) {
  apiInstance2().post(`/reviews`, JSON.stringify(review)).then(success).catch(fail);
}

function deleteMyReview(reviewId, success, fail) {
  apiInstance2().delete(`/reviews/${reviewId}`).then(success).catch(fail);
}

function modifyMyReview(reviewId, review, success, fail) {
  apiInstance2().put(`/reviews/${reviewId}`, JSON.stringify(review)).then(success).catch(fail);
}

// 시즌별 리뷰
function getAllSeasonReview(seasonId, success, fail) {
  api.get(`/reviews/seasons/${seasonId}`).then(success).catch(fail);
}

function getMyShowReview(performanceId, userId, success, fail) {
  apiInstance2().get(`/reviews/performances/${performanceId}/${userId}`).then(success).catch(fail);
}

function getDetailReview(reviewId, success, fail) {
  api.get(`/reviews/${reviewId}`).then(success).catch(fail);
}
export {
  getAllShowReview,
  getMyReview,
  addMyReview,
  deleteMyReview,
  modifyMyReview,
  getAllSeasonReview,
  getMyShowReview,
  getDetailReview,
};
