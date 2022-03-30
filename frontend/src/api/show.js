import { apiInstance } from "./index.js";

const api = apiInstance();

function getSeasonShow(performanceId, success, fail) {
  api.get(`/performances/seasons/${performanceId}`).then(success).catch(fail);
}

function detailSeasonShow(seasonId, success, fail) {
  api.get(`/performances/seasons/list/${seasonId}`).then(success).catch(fail);
}

function getRankingShow(success, fail) {
  api.get(`/performances/ranking`).then(success).catch(fail);
}

// 공연 상세 정보 api
function detailShow(performanceId, success, fail) {
  api.get(`/performances/${performanceId}`).then(success).catch(fail);
}

export { getSeasonShow, detailSeasonShow, getRankingShow, detailShow };
