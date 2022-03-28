import { apiInstance } from "./index.js";

const api = apiInstance();

function listSeasonShow(performanceId, success, fail) {
  api.get(`/performances/seasons/list/${performanceId}`).then(success).catch(fail);
}

function detailSeasonShow(seasonId, success, fail) {
  api.get(`/performances/seasons/${seasonId}`).then(success).catch(fail);
}

function listShow(success, fail) {
  api.get(`/performances`).then(success).catch(fail);
}

function detailShow(performanceId, success, fail) {
  api.get(`/performances/${performanceId}`).then(success).catch(fail);
}

export { listSeasonShow, detailSeasonShow, listShow, detailShow };
