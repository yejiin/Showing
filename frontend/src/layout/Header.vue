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
            <div class="col-6 collapse-brand"></div>

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
          <!-- 검색 -->
          <li class="nav-item">
            <search class="mt-3"></search>
          </li>
          <li class="nav-item">
            <a class="nav-link nav-link-icon">
              <login></login>
            </a>
          </li>
        </ul>

        <!-- 로그인 했을 경우 -->
        <ul v-else class="navbar-nav ml-lg-auto">
          <!-- 검색 -->
          <li class="nav-item">
            <search></search>
          </li>
          <!-- 화면 너비 작을 때 -->
          <li class="nav-item d-lg-none">
            <a class="dropdown-item mt-4" @click="goToMyPage">
              <i class="ni ni-circle-08"></i>
              <span class="nav-link-inner--text">마이페이지</span>
            </a>
            <a class="dropdown-item" @click="HandleLogout">
              <i class="ni ni-button-power"></i>
              <span class="nav-link-inner--text">로그아웃</span>
            </a>
          </li>
          <!-- 화면 너비 넓을 때 -->
          <div class="dropdown">
            <a href="#" class="btn btn-default profile-btn" data-toggle="dropdown" id="profileDropDown">
              <img class="profile-img" :src="userInfo.userImage" />
            </a>
            <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="profileDropDown">
              <li>
                <a class="dropdown-item" @click="goToMyPage">
                  <i class="ni ni-circle-08"></i>
                  <span class="nav-link-inner--text">마이페이지</span>
                </a>
                <a class="dropdown-item" @click="HandleLogout">
                  <i class="ni ni-button-power"></i>
                  <span class="nav-link-inner--text">로그아웃</span>
                </a>
              </li>
            </ul>
          </div>
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
      if (this.$route.path !== "/") this.$router.push({ name: "MainPage" });
    },
    goToMyPage() {
      if (this.$route.path !== "/users/" + this.userInfo.userId)
        this.$router.push({ path: "/users/" + this.userInfo.userId });
    },
    HandleLogout() {
      this.logout();
      this.setMyRatingState(0);
      this.showToast("success", "로그아웃 되었습니다");
      this.goToMain();
    },
    // confirm 메시지 표시
    showToast(typeName, message) {
      this.$toast(message, {
        type: typeName,
      });
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
li {
  cursor: pointer;
}
.ni,
.profile-img {
  height: 50px;
  width: 50px;
  text-align: center;
  border-radius: 50%;
}
.profile-btn,
.profile-btn:hover,
.profile-btn:active,
.profile-btn:focus {
  padding: 0;
  margin-left: 10px;
  border: 0;
  box-shadow: none;
}
.dropdown-item {
  height: 45px;
  padding: 0;
}
@media (min-width: 1200px) {
  .container {
    max-width: 1200px;
  }
}
@media (max-width: 991.98px) {
  .profile-btn {
    display: none;
  }
}
</style>
