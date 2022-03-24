<template>
  <b-navbar toggleable="lg" type="white" variant="white" class="fixed-top custnavi" v-cloak>
    <b-navbar-brand class="logo_position">
      <h3 class="logo">A301</h3>
    </b-navbar-brand>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <base-input class="searchInput" placeholder="공연과 배우를 검색해보세요" addon-left-icon="ni ni-zoom-split-in">
      </base-input>
      <a target="_blank" class="btn btn-neutral btn-icon login">
        <span class="nav-link-inner--text">로그인</span>
      </a>
    </b-collapse>
  </b-navbar>
</template>

<script>
export default {
  name: "Header",
};
</script>

<style scoped>
.navbar.navbar-dark.bg-dark {
  background-color: white !important;
}
.logo {
  color: #5461e6;
}
.logo_position {
  margin-left: 5% !important;
  margin-right: 60%;
}
.custnavi {
  border-bottom: solid 0.5px #e5e8eb;
}
.searchInput {
  margin-bottom: 0px !important;
  margin-right: 5%;
}
.nav-link-inner--text {
  margin-right: 30%;
}
.login {
  margin-right: 10%;
}
</style>
