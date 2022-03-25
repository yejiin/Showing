<template>
  <div>
    <div>
      <b-img style="width: 250px; float: left; clear: both" src="https://picsum.photos/300/400/?image=25"></b-img>
    </div>
    <div style="margin-left: 25%">
      <hr />
      <b-badge pill variant="primary">공연중</b-badge>
      <br /><br /><br />
      <h5 class="main_title">영화 제목</h5>
      <br />
      <h6>평균 <b-icon icon="star-fill" scale="0.8"></b-icon>평점</h6>
      <hr />
      <b-form-rating id="rating-md-no-border" v-model="value" color="#ff8800" no-border></b-form-rating>
      <hr />
    </div>
    <br /><br />
  </div>
</template>

<script>
export default {
  name: "ShowHeader",
  data() {
    return {
      value: null,
    };
  },
};
</script>

<style scoped></style>
