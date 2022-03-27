<template>
  <div>
    <br /><br />
    <b-card>
      <b-card-text class="my_review">회원님의 리뷰는 5개 입니다</b-card-text>
      <a target="_blank" class="btn btn-neutral btn-icon review">
        <span class="nav-link-inner--text">내 리뷰 보기</span>
      </a>
      <a target="_blank" class="btn btn-neutral btn-icon">
        <span class="nav-link-inner--text">리뷰 작성</span>
      </a>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
export default {
  name: "MyReview",
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.my_review {
  float: left;
  margin-right: 65%;
  margin-top: 0.5%;
}

.review {
  margin-right: 1%;
}
</style>
