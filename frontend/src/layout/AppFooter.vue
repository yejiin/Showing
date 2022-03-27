<template>
  <div class="containter">
    <hr />
    <div class="copyright">
      &copy; A301
      <br />
      서울 특별시 강남구 역삼동 멀티캠퍼스 SSAFY
    </div>
    <br /><br />
  </div>
</template>
<script>
export default {
  name: "app-footer",
  data() {
    return {};
  },
};
</script>
<style scoped>
.footer.has-cards:before {
  content: "";
  position: absolute;
  left: 0;
  right: 0;
  top: 600px;
  height: 2000px;
  background: transparent !important;
  -webkit-transform: skew(0, -8deg);
  transform: skew(0, -8deg);
}

.container {
  margin-left: 5% !important;
  margin-right: 5% !important;
}

.copyright {
  text-align: center;
}
</style>
