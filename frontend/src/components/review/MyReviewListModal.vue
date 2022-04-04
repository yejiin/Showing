<template>
  <div>
    <modal :show.sync="modals.myReviewList" modal-classes="modal-dialog" id="modal">
      <div id="list" v-show="modal1">
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
          @click="
            setMyReviewListModalState(false);
            page = Math.min(5, reviews.length);
          "
        >
          &times;
        </button>

        <br />

        <div>
          <h3 class="inline">{{ seasonShowName }}</h3>
        </div>
        <br />
        <div v-show="reviews.length === 0">내 리뷰가 없습니다</div>
        <div v-if="reviews.length !== 0">
          <div v-for="(n, index) in page" :key="index">
            <div id="reviewHeader" class="review">
              <div class="inreview">
                <div class="left">
                  <h5 class="bold mb-1" @click="showDetailModal(reviews[index].reviewId)">
                    {{ reviews[index].viewDate }}
                  </h5>
                </div>
                <div>
                  <span href="" class="updatedelete" @click="modifyModal(reviews[index].reviewId)">
                    <i class="fa fa-pencil"></i>
                    수정
                  </span>

                  <span href="" class="updatedelete" @click="deleteReview(reviews[index].reviewId)">
                    <i class="fa fa-trash"></i>
                    삭제 &nbsp;| &nbsp;
                  </span>
                </div>
                <div class="title" style="clear: both" @click="showDetailModal(reviews[index].reviewId)">
                  <img :src="userInfo.userImage" alt="profile image" class="profile inline" />
                  <p class="username inline">{{ userInfo.nickName }}</p>
                </div>
                <div class="mb-2">
                  <label for="casing" class="bold rightmargin inline"
                    ><h6 class="bold rightmargin inline">캐스팅</h6></label
                  >
                  <b-badge
                    pill
                    variant="primary"
                    v-for="(index, key) in reviews[index].reviewActorNameList"
                    :key="key"
                    >{{ index }}</b-badge
                  >
                </div>
                <div class="">
                  <p class="content">{{ reviews[index].content }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <button class="moreReview" v-if="page !== reviews.length" @click="onScroll">
          <img class="moreReviewImg" src="./assets/pngwing.png" alt="더보기" />
        </button>
        <!-- <infinite-loading v-if="hasMore" :identifier="infiniteId" @infinite="onScroll"></infinite-loading> -->
      </div>
      <div id="detail" v-show="modal2">
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
          @click="
            modal1 = true;
            modal2 = false;
          "
        >
          &times;
        </button>
        <div class="modalHeader title2">
          <img :src="userInfo.userImage" alt="profile image" class="profile2" />
          <h6 class="inline2 title2">{{ userInfo.nickName }}</h6>
        </div>
        <div style="width: 100%; position: relative">
          <div class="showInfo2 left mb-3">
            <div>
              <h3>{{ show.performanceName }}</h3>
              <p style="font-size: 8px">{{ show.startDate }}~{{ show.endDate }}</p>
            </div>
            <label for="date">관람일정</label>
            <p class="inline2 right2" type="input">{{ show.viewDate }}</p>
            <br />
            <label for="time">관람시간</label>
            <p class="inline2" type="input">{{ show.viewTime }}</p>
            <br />
            <label for="location">관람장소</label>
            <p class="inline2" type="input">{{ show.location }}</p>
            <br />
            <label for="castingboard left2">캐스팅보드</label><br />
            <b-badge pill variant="primary" v-for="(index, key) in show.reviewActorNameList" :key="key">{{
              index
            }}</b-badge>
          </div>
          <div class="right mb-3">
            <img class="showimage2" :src="show.seasonImage" alt="show image" />
          </div>
        </div>
        <div class="content2">
          <p style="margin: 8%">{{ show.content }}</p>
        </div>
      </div>
    </modal>
    <review-modify-modal
      :key="modireview"
      :seasonShowName="seasonShowName"
      :seasonShow="seasonShow"
    ></review-modify-modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import { getMyShowReview, deleteMyReview } from "@/api/review.js";
import { mapState, mapActions } from "vuex";
import ReviewModal from "@/components/review/ReviewModal.vue";
import ReviewModifyModal from "@/components/review/ReviewModifyModal.vue";
import { getDetailReview } from "@/api/review.js";

const reviewStore = "reviewStore";
const userStore = "userStore";

export default {
  components: {
    Modal,
    ReviewModal,
    ReviewModifyModal,
  },
  props: {
    type: String,
    showModal: Boolean,
    seasonShowName: String,
    seasonShow: Object,
    previewReview: Array,
    performanceId: Number,
  },
  data() {
    return {
      modal1: true,
      modal2: false,
      show: {},
      reviews: [],
      selectedseason: 1,
      selectedreviewid: 0,
      page: 5,
      hasMore: false,
      infinitedId: +new Date(),
      modireview: 0,
    };
  },
  computed: {
    ...mapState(reviewStore, ["modals", "reviewInfo"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(reviewStore, ["setMyReviewListModalState", "setReviewId", "setModifyReviewModalState"]),
    getMyReviewList() {
      getMyShowReview(
        this.performanceId,
        this.userInfo.userId,
        (response) => {
          this.reviews = response.data.data;
          this.hasMore = response.data.data.length > 0 ? true : false;
          this.page = Math.min(this.page, response.data.data.length);
          console.log(response.data.data);
        },
        (fail) => {
          console.log(fail);
        }
      );
    },
    showDetailModal(id) {
      this.selectedreviewid = id;
      this.modal1 = false;
      this.modal2 = true;
      getDetailReview(id, (response) => {
        this.show = response.data.data;
        this.show.viewTime = response.data.data.viewTime.substring(0, 5);
      });
    },
    getNewMyShowReview() {
      getMyShowReview(
        this.performanceId,
        this.userInfo.id,
        (response) => {
          this.reviews = response.data.data;
        },
        (fail) => {
          console.log(fail);
        }
      );
    },
    deleteReview(id) {
      deleteMyReview(
        id,
        (response) => {
          this.showToast("success", response.data.message);
          this.getMyReviewList();
          this.$emit("myReviewList");
        },
        (fail) => {
          console.log(fail);
          this.showToast("error", "리뷰 삭제 실패");
        }
      );
    },
    modifyModal(id) {
      this.setReviewId(id);
      this.modireview = id;
      this.setMyReviewListModalState(false);
      this.setModifyReviewModalState(true);
    },
    onScroll($state) {
      console.log("onScroll");
      console.log(this.page);
      this.page = Math.min(this.page + 5, this.reviews.length);
      console.log("after : " + this.page);
    },
    // confirm 메시지 표시
    showToast(typeName, message) {
      this.$toast(message, {
        type: typeName,
      });
    },
  },
  created() {
    this.getMyReviewList();
  },
  mounted() {
    this.setModifyReviewModalState(false);
  },
};
</script>

<style scoped>
* {
  font-family: "Noto Sans KR", sans-serif;
}
.inline {
  display: inline;
  margin-right: 10px;
}
select#season {
  border: 0px;
}
.left {
  width: 70%;
  float: left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
.right {
  width: 30%;
  float: right;
  box-sizing: border-box;
}
.leftn {
  width: 85%;
  float: left;
  /* box-sizing: border-box; */
}
.rightn {
  width: 15%;
  float: right;
  /* box-sizing: border-box; */
  /* font-size: 14px; */
}
.profile {
  clear: both;
  width: 4%;
  height: 4%;
  object-fit: cover;
  border-radius: 70%;
}
.showimage {
  width: 95%;
  float: right;
  margin-right: 5%;
  border-radius: 5%;
}
.updatedelete {
  float: right;
  color: #626262;
  font-size: 10px;
  cursor: pointer;
}
.title {
  margin-bottom: 1%;
}
.review {
  border-radius: 8px;
  border: #c4c4c4 solid 0.5px;
  margin: 1%;
}
.bold {
  font-weight: bold;
}
.rightmargin {
  margin-right: 3%;
}
.content {
  clear: both;
  height: 70%;
  background-color: #f8f8f8;
  border-radius: 5%;
  margin-bottom: 3%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.username {
  font-size: 12px;
}
.inreview {
  margin-top: 2%;
  margin-left: 4%;
  margin-right: 4%;
  margin-bottom: 1%;
}
.modalbutton {
  width: 20%;
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
.inline2 {
  display: inline;
  margin-left: 10px;
}
.profile2 {
  width: 5%;
  height: 5%;
  object-fit: cover;
  border-radius: 70%;
}
.title2 {
  margin-bottom: 4%;
}
.showimage2 {
  width: 95%;
  float: right;
  margin-right: 5%;
  border-radius: 5%;
}
div.left2 {
  width: 60%;
  float: left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
div.right2 {
  width: 40%;
  float: right;
  box-sizing: border-box;
}
.content2 {
  clear: both;
  height: 70%;
  background-color: #f8f8f8;
  border-radius: 5%;
}
.backArrow2 {
  clear: both;
  float: left;
  display: block;
  background-color: transparent;
  border: 0;
}
.backArrow2:not(:disabled):not(.disabled) {
  cursor: pointer;
}
label,
h3 {
  font-weight: bold;
}
.moreReview {
  border: 0px;
  background-color: transparent;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
.moreReviewImg {
  width: 8%;
}
</style>
