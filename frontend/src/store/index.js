import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import ratingStore from "@/store/modules/ratingStore.js";
import userStore from "@/store/modules/userStore.js";
import reviewStore from "@/store/modules/reviewStore.js";
Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    userStore,
    reviewStore,
    ratingStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;
