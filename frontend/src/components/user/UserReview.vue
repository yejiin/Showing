<template>
  <div>
    <h4 class="main_title mt-5">내 리뷰</h4>
    <b-card-group deck class="mb-0">
      <b-card
        id="itemList"
        v-for="item in createPages()"
        :key="item.index"
        class="mr-0 mb-2"
        :img-src="item.performanceImage"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem"
        @click="setReviewModal(true, item.reviewId)"
      >
        <!-- card content -->
        <div>
          <h5 class="review-name">{{ item.performanceName }}</h5>
          <div class="review-date">{{ item.viewDate }}</div>
        </div>
        <br />
      </b-card>
      <review-modal :detail-review="detailReview" :user-id="userId"></review-modal>
    </b-card-group>
    <!-- pagination area -->
    <div>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="itemList"
        align="center"
        first-number
        last-number
      ></b-pagination>
    </div>
    <br />
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import { getDetailReview } from "@/api/review.js";

import ReviewModal from "../review/ReviewModal.vue";

const reviewStore = "reviewStore";

export default {
  name: "UserReview",
  components: {
    ReviewModal,
  },

  data() {
    return {
      currentPage: 1,
      pageCount: 0,
      reviewsPerPage: 5,
      perPage: 5,
      detailReview: {},
    };
  },
  props: {
    reviewList: Array,
    userId: Number,
  },
  computed: {
    ...mapState(reviewStore, ["modals"]),

    rows: function () {
      return this.reviewList.length;
    },
  },

  methods: {
    ...mapActions(reviewStore, ["setReviewModalState"]),

    setReviewModal(status, reviewId) {
      this.reviewId = reviewId;
      this.setReviewModalState({ status, reviewId });

      getDetailReview(
        this.modals.myReview.reviewId,
        (response) => {
          this.detailReview = response.data.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },

    createPages() {
      return this.reviewList.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
    },
  },
};
</script>
<style scoped>
.index {
  margin-left: 10px;
  width: 10px;
  height: 10px;
  background: #9badf6;
}

.card {
  border: 0px;
}

.card-img-top {
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
}

.card-body {
  padding-left: 0px;
  padding-right: 0px;
}

.review-name {
  float: left;
}

.review-date {
  float: right;
}
</style>
