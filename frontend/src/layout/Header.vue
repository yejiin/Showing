<template>
  <b-navbar toggleable="lg" type="white" variant="white" class="fixed-top custnavi" v-cloak>
    <b-navbar-brand class="logo_position">
      <h3 class="logo" @click="goToMain()">Showing</h3>
    </b-navbar-brand>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <search></search>
      <login v-if="userInfo.userId==''" style="margin-right: 15%"></login>
      <div class="dropdown">
        <p v-if="userInfo.userId"><img class="profile" :src="userInfo.userImage" alt=""></p>
        <div class="dropdown-content">
          <p @click="HandleLogout">로그아웃</p>
          <p @click="goToMyPage">마이페이지</p>
        </div>
      </div>
    </b-collapse>
  </b-navbar>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Search from "@/components/search/Search.vue";
import Login from "@/components/user/LoginModal.vue";

const userStore = "userStore";

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
    goToMain() {
      this.$router.push({ name: "MainPage" });
    },
    goToMyPage(){
      this.$router.push({ path: '/users/'+this.userInfo.userId})
    },
    HandleLogout(){
      this.logout()
      alert("로그아웃 되었습니다")
      this.goToMain()
    }
  },
};
</script>

<style scoped>
.navbar.navbar-dark.bg-dark {
  background-color: white !important;
}

.logo {
  color: #5461e6;
  cursor: pointer;
}

.logo_position {
  margin-left: 5% !important;
  margin-right: 55%;
}

.custnavi {
  border-bottom: solid 0.5px #e5e8eb;
}
.profile {
  border-radius: 70%;
  width: 40%;
  margin-top: 20%;
  /* margin-top: 20px; */
  margin-left: 30px;
}
.dropdown { position: relative; display: inline-block; }
.dropdown-content {
  display: none;
  position: absolute;
  margin-left: 20%;
  width: 160px;
  /* background-color: #F9F9F9;
  min-width: 160px;
  padding: 8px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); */
}
.dropdown:hover .dropdown-content { display: block; }
</style>
