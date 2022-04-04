<template>
  <div>
    <h4 class="main_title mt-5">내 리뷰</h4>
    <div v-if="reviewList != 0">
      <b-card-group deck class="mb-0">
        <b-card
          id="itemList"
          v-for="item in createPages()"
          :key="item.index"
          class="review mr-0 mb-2 card-lift--hover"
          :img-src="item.performanceImage"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          @click="setReviewModal(true, item.reviewId)"
        >
          <!-- card content -->
          <div>
            <h6 class="review-name">{{ item.performanceName }}</h6>
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
    <div class="my-5 mx-auto" style="text-align: center" v-else>
      <b-card-text>등록된 리뷰가 없습니다.</b-card-text>
      <br /><br />
    </div>
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
      emptyReview: [],
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
      if (reviewId == undefined) return;

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
      let newList = this.reviewList.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
      this.emptyReview = [];

      if (newList.length < 5) {
        let cnt = 5 - newList.length;

        const temp = {
          reveiwId: "",
        };

        for (let i = 0; i < cnt; i++) {
          newList.push(temp);
        }
      }
      return newList;
    },

    // createPages() {
    //   return this.reviewList.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
    // },
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
  /* float: left; */
  font-weight: 600;
}

.review-date {
  /* float: right; */
  font-size: 10px;
}
.review {
  cursor: pointer;
}
</style>
