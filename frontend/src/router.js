import Vue from "vue";
import Router from "vue-router";

import AppFooter from "./layout/AppFooter";
import Header from "./layout/Header";

import MainPage from "./views/MainPage.vue";
import ShowDetail from "./views/ShowDetail.vue";
import Callback from "./views/Callback.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      name: "MainPage",
      components: {
        header: Header,
        default: MainPage,
        footer: AppFooter,
      },
    },
    {
      path: "/show",
      name: "ShowDetail",
      components: {
        header: Header,
        default: ShowDetail,
        footer: AppFooter,
      },
    },
    {
      path: "/callback",
      name: "Callback",
      component: Callback,
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
