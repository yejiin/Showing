<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-default">
    <div class="container">
      <!-- 메인 로고 -->
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img class="logo" src="@/assets/img/showing-logo.png" alt="logo" />
      </router-link>

      <!-- Navbar 토글 버튼 -->
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbar"
        aria-controls="navbar"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbar">
        <div class="navbar-collapse-header">
          <div class="row">
            <!-- 검색 -->
            <div class="col-6 collapse-brand">
              <search></search>
            </div>

            <!-- 닫기 버튼 -->
            <div class="col-6 collapse-close">
              <button
                type="button"
                class="navbar-toggler"
                data-toggle="collapse"
                data-target="#navbar"
                aria-controls="navbar"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span></span>
                <span></span>
              </button>
            </div>
          </div>
        </div>

        <!-- 비로그인의 경우 -->
        <ul v-if="userInfo.userId == ''" class="navbar-nav ml-lg-auto">
          <li class="nav-item">
            <a class="nav-link nav-link-icon">
              <login></login>
            </a>
          </li>
        </ul>

        <!-- 로그인 했을 경우 -->
        <ul v-else class="navbar-nav ml-lg-auto">
          <li class="nav-item">
            <a class="nav-link nav-link-icon" href="#">
              <img class="profile-img" :src="userInfo.userImage" alt="" />
              <span class="nav-link-inner--text d-lg-none">마이페이지</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link nav-link-icon" @click="HandleLogout">
              <i class="ni ni-button-power"></i>
              <span class="nav-link-inner--text">로그아웃</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Search from "@/components/search/Search.vue";
import Login from "@/components/user/LoginModal.vue";

const userStore = "userStore";
const ratingStore = "ratingStore";

export default {
  name: "Header",
  components: {
    Search,
    Login,
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(userStore, ["logout"]),
    ...mapActions(ratingStore, ["setMyRatingState"]),
    goToMain() {
      this.$router.push({ name: "MainPage" });
    },
    goToMyPage() {
      this.$router.push({ path: "/users/" + this.userInfo.userId });
    },
    HandleLogout() {
      this.logout();
      this.setMyRatingState(0);
      alert("로그아웃 되었습니다");
      this.goToMain();
    },
  },
};
</script>

<style scoped>
.navbar {
  height: 80px;
}
.logo {
  cursor: pointer;
  height: 65px;
}
.nav-item {
  cursor: pointer;
}
.ni,
.profile-img {
  height: 30px;
  width: 30px;
  text-align: center;
  margin-right: 10px;
  border-radius: 50%;
}
</style>
