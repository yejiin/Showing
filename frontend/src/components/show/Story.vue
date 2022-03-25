<template>
  <div>
    <b-card>
      <h5 class="main_title">줄거리</h5>
      <b-card-text>줄거리 내용</b-card-text>
      <h6 class="more">더보기</h6>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
export default {
  name: "Story",
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.more {
  color: #3f46d8;
  float: right;
}
</style>
