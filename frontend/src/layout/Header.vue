<template>
  <b-navbar toggleable="lg" type="white" variant="white" class="fixed-top custnavi" v-cloak>
    <b-navbar-brand class="logo_position">
      <h3 class="logo" @click="goToMain()">A301</h3>
    </b-navbar-brand>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <search></search>
      <login></login>
    </b-collapse>
  </b-navbar>
</template>

<script>
import Search from "@/components/search/Search.vue";
import Login from "@/components/user/Login.vue";

export default {
  name: "Header",
  components: {
    Search,
    Login,
  },
  methods: {
    goToMain() {
      this.$router.push({ name: "MainPage" });
    },
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
  margin-right: 60%;
}
.custnavi {
  border-bottom: solid 0.5px #e5e8eb;
}
</style>
