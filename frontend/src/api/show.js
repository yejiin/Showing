import { apiInstance } from "./index.js";

const api = apiInstance();

function getSeasonShow(performanceId, success, fail) {
  api.get(`/performances/seasons/${performanceId}`).then(success).catch(fail);
}

function detailSeasonShow(seasonId, success, fail) {
  api.get(`/performances/seasons/list/${seasonId}`).then(success).catch(fail);
}

function getShow(success, fail) {
  api.get(`/performances`).then(success).catch(fail);
}

function detailShow(performanceId, success, fail) {
  api.get(`/performances/${performanceId}`).then(success).catch(fail);
}

export { getSeasonShow, detailSeasonShow, getShow, detailShow };
