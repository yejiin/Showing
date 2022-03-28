import axios from "axios";
import { API_BASE_URL } from "@/config";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function apiInstance2() {
  const token = window.localStorage.getItem("access-token");
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
      Authorization: `Bearer ` + token,
    },
  });
  return instance;
}

export { apiInstance, apiInstance2 };
