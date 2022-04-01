<template>
  <div>
    <modal :show.sync="modals.myReviewList" modal-classes="modal-dialog" id="modal">
      <div id="list" v-show="modal1">
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
          @click="setMyReviewListModalState(false)"
        >
          &times;
        </button>

        <br />

        <div>
          <h3 class="inline">{{ seasonShowName }}</h3>
        </div>
        <br />
        <div v-show="reviews.length === 0">내 리뷰가 없습니다</div>
        <div v-for="review in reviews" :key="review.reviewId">
          <div id="reviewHeader" class="review">
            <div class="inreview">
              <div class="left">
                <h5 class="bold mb-1" @click="showDetailModal(review.reviewId)">{{ review.viewDate }}</h5>
              </div>
              <div>
                <a href="" class="udpatedelete">
                  <i class="fa fa-pencil"></i>
                  수정
                </a>

                <a href="" class="udpatedelete" @click.prevent="deleteReview(review.reviewId)">
                  <i class="fa fa-trash"></i>
                  삭제 &nbsp;| &nbsp;
                </a>
              </div>
              <div class="title" style="clear: both" @click="showDetailModal(review.reviewId)">
                <img :src="userInfo.userImage" alt="profile image" class="profile inline" />
                <p class="username inline">{{ userInfo.nickName }}</p>
              </div>
              <div class="mb-2">
                <label for="casing" class="bold rightmargin inline"
                  ><h6 class="bold rightmargin inline">캐스팅</h6></label
                >
                <b-badge pill variant="primary" v-for="(index, key) in review.reviewActorNameList" :key="key">{{
                  index
                }}</b-badge>
              </div>
              <div class="">
                <p class="content">{{ review.content }}</p>
              </div>
            </div>
          </div>
        </div>
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
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import { getMyShowReview, deleteMyReview } from "@/api/review.js";
import { detailSeasonShow } from "@/api/show.js";
import { mapState, mapActions } from "vuex";
import ReviewModal from "@/components/review/ReviewModal.vue";
import { getDetailReview } from "@/api/review.js";

const reviewStore = "reviewStore";
const userStore = "userStore";

export default {
  components: {
    Modal,
    ReviewModal,
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
    };
  },
  computed: {
    ...mapState(reviewStore, ["modals"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(reviewStore, ["setMyReviewListModalState"]),
    showDetailModal(id) {
      this.selectedreviewid = id;
      this.modal1 = false;
      this.modal2 = true;
      getDetailReview(id, (response) => {
        this.show = response.data.data;
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
          alert("게시물이 삭제되었습니다");
        },
        (fail) => {
          console.log(fail);
        }
      );
    },
  },
  created() {
    getMyShowReview(
      this.performanceId,
      this.userInfo.userId,
      (response) => {
        this.reviews = response.data.data;
      },
      (fail) => {
        console.log(fail);
      }
    );
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
.udpatedelete {
  float: right;
  color: #626262;
  font-size: 10px;
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
</style>
