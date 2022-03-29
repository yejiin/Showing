import { apiInstance } from "./index.js";

const api = apiInstance();

async function getUser(userId, success, fail) {
  await api.get(`/users/${userId}`).then(success).catch(fail);
}

async function modifyUser(user, success, fail) {
  await api.put(`/users`, JSON.stringify(user)).then(success).catch(fail);
}

export { getUser, modifyUser };
