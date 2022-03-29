import { apiInstance, apiInstance2 } from "./index.js";

const api = apiInstance();
const api2 = apiInstance2();

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

export { getAllShowReview, getMyReview, addMyReview, deleteMyReview, modifyMyReview };
