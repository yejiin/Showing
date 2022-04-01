<template>
  <div>
    <h4 class="main_title">내 리뷰</h4>
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
      >
        <a @click="setReviewModal(true)">
          <review-modal></review-modal>
          <!-- card content -->
          <div>
            <h5 class="review-name">{{ item.performanceName }}</h5>
            <div class="review-date">{{ item.viewDate }}</div>
          </div>
          <br />
        </a>
      </b-card>
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
import { mapActions } from "vuex";
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
    };
  },
  props: {
    reviewList: Array,
  },
  computed: {
    rows: function () {
      return this.reviewList.length;
    },
  },

  methods: {
    ...mapActions(reviewStore, ["setReviewModalState"]),
    setReviewModal(status) {
      console.log(status);
      this.setReviewModalState(status);
    },

    detailReview(index) {
      this.$router.push({
        name: "ShowDetail",
        params: { showId: this.musicalList[index].performanceId },
      });
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
