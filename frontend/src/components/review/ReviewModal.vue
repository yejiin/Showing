<template>
  <div>
    <modal :show.sync="modals.myReview.status">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="setReviewModalStates(false)">
        &times;
      </button>

      <div>
        <div class="modalHeader title">
          <!-- <img :src="user.img" alt="profile image" class="profile" /> -->
          <!-- <h6 class="inline title">{{ user.nickname }}</h6> -->
        </div>
        <div v-if="this.$store.state.userStore.userInfo.userId == this.userId">
          <a class="updatedelete" @click="modifyModal(detailReview.reviewId)">
            <i class="fa fa-pencil"></i>
            수정
          </a>

          <a class="updatedelete" @click="deleteReview(detailReview.reviewId)">
            <i class="fa fa-trash"></i>
            삭제 &nbsp;| &nbsp;
          </a>
        </div>
        <div style="width: 100%; position: relative">
          <div class="showInfo left mb-3">
            <div>
              <h3>{{ detailReview.performanceName }}</h3>
              <p style="font-size: 8px">{{ detailReview.startDate }}~{{ detailReview.endDate }}</p>
            </div>
            <label for="date">관람일정</label>
            <p class="inline right" type="input">{{ detailReview.viewDate }}</p>
            <br />
            <label for="time">관람시간</label>
            <p class="inline" type="input">{{ detailReview.viewTime }}</p>
            <br />
            <label for="location">관람장소</label>
            <p class="inline" type="input">{{ detailReview.location }}</p>
            <br />
            <label for="castingboard left">캐스팅보드</label><br />
            <b-badge pill variant="primary" v-for="(index, key) in detailReview.reviewActorNameList" :key="key">{{
              index
            }}</b-badge>
          </div>
          <div class="right mb-3">
            <img class="showimage" :src="detailReview.seasonImage" alt="show image" />
          </div>
        </div>
        <div class="content">
          <p style="margin: 8%">{{ detailReview.content }}</p>
        </div>
      </div>
    </modal>
    <review-modify-modal
      :seasonShowName="detailReview.performanceName"
      :seasonShow="detailReview"
    ></review-modify-modal>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { deleteMyReview } from "@/api/review.js";
import Modal from "@/components/Modal.vue";
import ReviewModifyModal from "@/components/review/ReviewModifyModal.vue";

const reviewStore = "reviewStore";

export default {
  name: "ReviewModal",
  components: {
    Modal,
    ReviewModifyModal,
  },
  props: {
    detailReview: Object,
    userId: Number,
  },
  computed: {
    ...mapState(reviewStore, ["modals"]),
  },
  methods: {
    ...mapActions(reviewStore, ["setReviewModalState", "setReviewId", "setModifyReviewModalState"]),
    setReviewModalStates(status) {
      let reviewId = 0;
      this.setReviewModalState({ status, reviewId });
    },

    deleteReview(reviewId) {
      console.log("deleteReview");
      let status = false;
      this.setReviewModalState({ status, reviewId });
      deleteMyReview(
        reviewId,
        (response) => {
          alert("게시물이 삭제되었습니다");
          console.log("success : " + response);

          this.$router.go();
        },
        (fail) => {
          console.log("fail 온 거임");
          console.log(fail);
        }
      );
    },

    modifyModal(reviewId) {
      let status = false;
      let id = reviewId;
      this.setReviewModalState({ status, reviewId });
      this.setModifyReviewModalState(true);
      this.setReviewId(id);
    },
  },
};
</script>

<style scoped>
/* * {
  font-family: "Noto Sans" !important;
   font-size: 15px;
} */
.inline {
  display: inline;
  margin-left: 10px;
}
.profile {
  width: 5%;
  height: 5%;
  object-fit: cover;
  border-radius: 70%;
}
.title {
  margin-bottom: 4%;
}
.showimage {
  width: 95%;
  float: right;
  margin-right: 5%;
  border-radius: 5%;
}
div.left {
  width: 60%;
  float: left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
div.right {
  width: 40%;
  float: right;
  box-sizing: border-box;
}
.content {
  clear: both;
  height: 70%;
  background-color: #f8f8f8;
  border-radius: 5%;
}
.backArrow {
  clear: both;
  float: left;
  display: block;
  background-color: transparent;
  border: 0;
}
.backArrow:not(:disabled):not(.disabled) {
  cursor: pointer;
}
label,
h3 {
  font-weight: bold;
}
</style>
