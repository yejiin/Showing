import { apiInstance } from "./index.js";

const api = apiInstance();

function addRating(rating, success, fail) {
  api.post(`/rating`, JSON.stringify(rating)).then(success).catch(fail);
}

function deleteRating(starId, success, fail) {
  api.delete(`/rating/${starId}`).then(success).catch(fail);
}

function modifyRating(rating, success, fail) {
  api.put(`/rating`, JSON.stringify(rating)).then(success).catch(fail);
}

export { addRating, deleteRating, modifyRating };
