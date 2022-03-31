<template>
  <div class="row">
    <div>
      <img :src="heading.performanceImage" alt="profile image" class="profile" />
    </div>
    <div class="col show_header">
      <br />
      <div v-if="heading.proceedFlag == 0"><b-badge pill variant="danger">공연완료</b-badge></div>
      <div v-if="heading.proceedFlag == 1"><b-badge pill variant="primary">공연중</b-badge></div>
      <div v-if="heading.proceedFlag == 2"><b-badge pill variant="warning">예정</b-badge></div>
      <br /><br />
      <h2 class="main_title">{{ heading.performanceName }}</h2>
      <br />
      <h6>
        평균 <b-icon icon="star-fill" scale="0.8"></b-icon>{{ heading.starPointAverage / 2 }} ({{
          heading.ratingCount
        }}명)
      </h6>
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
      ></star-rating>
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
  props: {
    heading: Object,
  },
  data() {
    return {
      value: null,
    };
  },
  // watch: {
  //   value: function () {
  //     if (this.storeValue == 0) {
  //       console.log("add");
  //       // add
  //     } else {
  //       if (this.storeValue == this.value) {
  //         console.log("delete");
  //         // delete
  //       } else {
  //         console.log("modify");
  //         // modify
  //       }
  //     }
  //   },
  // },
};
</script>

<style scoped>
.show_header {
  margin-left: 5%;
  border-top: 1px solid;
  border-bottom: 1px solid;
  border-color: rgb(229, 229, 229);
}
</style>
