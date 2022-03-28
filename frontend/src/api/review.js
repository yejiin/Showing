import { apiInstance } from "./index.js";

const api = apiInstance();

function getAllShowReview(performanceId, success, fail) {
  api.get(`/reviews/performances/${performanceId}`).then(success).catch(fail);
}

function getMyReview(userId, success, fail) {
  api.get(`/reviews/users/${userId}`).then(success).catch(fail);
}

function addMyReview(review, success, fail) {
  api.post(`/reviews`, JSON.stringify(review)).then(success).catch(fail);
}

function deleteMyReview(reviewId, success, fail) {
  api.delete(`/reviews/${reviewId}`).then(success).catch(fail);
}

function modifyMyReview(reviewId, review, success, fail) {
  api.put(`/reviews/${reviewId}`, JSON.stringify(review)).then(success).catch(fail);
}

export { getAllShowReview, getMyReview, addMyReview, deleteMyReview, modifyMyReview };
