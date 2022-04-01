<template>
  <div>
    <div class="row">
      <b-card-text class="my_review">회원님의 리뷰는 {{ previewReview.length }}개 입니다</b-card-text>
    </div>
    <div class="col">
      <a target="_blank" class="btn btn-neutral btn-icon review button" @click="setMyReviewListModalStates(true)">
        <span class="nav-link-inner--text">리뷰 보기</span>
      </a>

      <a target="_blank" class="btn btn-neutral btn-icon button" @click="setWriteModalStates(true)">
        <span class="nav-link-inner--text">리뷰 작성</span>
      </a>
    </div>
    <br />
    <br />
    <review-list :seasonShowName="seasonShowName" :seasonShow="seasonShow" :previewReview="previewReview"></review-list>
    <review-write :seasonShowName="seasonShowName" :seasonShow="seasonShow"></review-write>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import ReviewListModalVue from "../review/MyReviewListModal.vue";
import ReviewWriteModalVue from "../review/ReviewWriteModal.vue";

const userStore = "userStore";
const reviewStore = "reviewStore";
export default {
  name: "MyReview",
  props: {
    seasonShowName: String,
    seasonShow: Object,
    previewReview: Array,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapGetters({
      isLogin: "user/isLogin",
    }),
  },
  components: {
    ReviewList: ReviewListModalVue,
    ReviewWrite: ReviewWriteModalVue,
  },
  data() {
    return {
      modals: {
        listmodal: false,
        writemodal: false,
      },
    };
  },
  methods: {
    ...mapActions(reviewStore, ["setMyReviewListModalState", "setWriteReviewModalState"]),
    setMyReviewListModalStates(status) {
      this.setMyReviewListModalState(status);
      console.log("리뷰 목록 보여줘요" + status);
    },
    setWriteModalStates(status) {
      this.setWriteReviewModalState(status);
    },
  },
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

.button {
  float: right;
}
</style>
