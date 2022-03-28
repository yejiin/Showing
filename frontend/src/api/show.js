import { apiInstance } from "./index.js";

const api = apiInstance();

function getSeasonShow(performanceId, success, fail) {
  api.get(`/performances/seasons/list/${performanceId}`).then(success).catch(fail);
}

function detailSeasonShow(seasonId, success, fail) {
  api.get(`/performances/seasons/${seasonId}`).then(success).catch(fail);
}

function getShow(success, fail) {
  api.get(`/performances`).then(success).catch(fail);
}

function detailShow(userId, param, success, fail) {
  api.get(`/performances/${userId}`, { params: param }).then(success).catch(fail);
}

export { getSeasonShow, detailSeasonShow, getShow, detailShow };
