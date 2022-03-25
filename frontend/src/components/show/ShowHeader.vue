<template>
  <div class="row">
    <div>
      <img src="https://picsum.photos/300/400/?image=25" />
    </div>
    <div
      style="margin-left: 5%; border-top: 1px solid; border-bottom: 1px solid; border-color: rgb(229, 229, 229)"
      class="col"
    >
      <br />
      <b-badge pill variant="primary">공연중</b-badge>
      <br /><br /><br />
      <h2 class="main_title row">영화 제목</h2>
      <br />
      <h6>평균 <b-icon icon="star-fill" scale="0.8"></b-icon>평점</h6>
      <hr />
      <br />
      <star-rating
        class="row justify-content-left"
        id="starRating"
        :increment="0.5"
        :star-size="45"
        :show-rating="false"
        v-model="value"
        :read-only="false"
        padding="1"
      ></star-rating>
      <!-- <hr /> -->
    </div>
  </div>
</template>

<script>
import StarRating from "vue-star-rating";

export default {
  name: "ShowHeader",
  components: {
    StarRating,
  },
  data() {
    return {
      value: null,
    };
  },
};
</script>

<style scoped></style>
