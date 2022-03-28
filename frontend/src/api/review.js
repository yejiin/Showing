import { apiInstance } from "./index.js";

const api = apiInstance();

function listMyReview(userId, success, fail) {
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

export { listMyReview, addMyReview, deleteMyReview, modifyMyReview };
