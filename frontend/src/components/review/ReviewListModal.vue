<template>
  <div>
    <base-button size="sm" type="primary" class="float-right" @click="[(modals.modal = true), (modals.modal1 = true)]">
      더보기
    </base-button>
    <modal :show.sync="modals.modal" modal-classes="modal-dialog">
      <div v-show="modals.modal1">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="modals.modal = false">
          &times;
        </button>
        <div>
          <h3 class="inline">{{ info.performanceName }}</h3>
          <select v-model="seasonId" name="season" id="season" @change="getReviews()">
            <option v-for="i in seasons" :key="i.seasonId" :value="i.seasonId">
              {{ i.startDate.substring(0, 4) }} 시즌
            </option>
          </select>
        </div>
        <br />
        <div>
          <div v-show="reviews.length === 0" class="mb-5" style="text-align: center">
            리뷰가 없습니다
            <br />
          </div>
          <div v-for="review in reviews" :key="review.reviewId">
            <div id="reviewHeader" class="review mb-4" @click="showDetailModal(review.reviewId)">
              <div class="inreview">
                <div class="title mt-1" @click="detailUser(review.userId)">
                  <img :src="review.userImage" alt="profile image" class="profile inline" />
                  <span class="username inline bold">{{ review.userName }}</span>
                  <span class="username inline">{{ review.viewDate }}</span>
                  <hr style="margin-top: 10px; width: 100%; margin-bottom: 10px" />
                </div>
                <div class="mb-3">
                  <b-badge pill variant="primary" v-for="(index, key) in review.castingActorNameList" :key="key">{{
                    index
                  }}</b-badge>
                </div>
                <div class="">
                  <pre class="content">{{ review.content }}</pre>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="detail" v-show="modals.modal2">
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
          @click="
            modals.modal1 = true;
            modals.modal2 = false;
          "
        >
          &times;
        </button>
        <div></div>
        <div class="modalHeader title2" @click="detailUser(review.userId)">
          <img :src="review.userImage" alt="profile image" class="profile2" />
          <h6 class="inline2 title2">{{ review.userName }}</h6>
        </div>
        <div style="width: 100%; position: relative">
          <div class="showInfo2 left mb-3">
            <div>
              <h3>{{ review.performanceName }}</h3>
              <p style="font-size: 8px">{{ review.startDate }}~{{ review.endDate }}</p>
            </div>
            <label for="date">관람일정</label>
            <p class="inline2 right2" type="input">{{ review.viewDate }}</p>
            <br />
            <label for="time">관람시간</label>
            <p class="inline2" type="input">{{ review.viewTime }}</p>
            <br />
            <label for="location">관람장소</label>
            <p class="inline2" type="input">{{ review.location }}</p>
            <br />
            <label for="castingboard left2">캐스팅보드</label><br />
            <b-badge pill variant="primary" v-for="(index, key) in review.reviewActorNameList" :key="key">{{
              index
            }}</b-badge>
          </div>
          <div class="right mb-3">
            <img class="showimage2" :src="review.seasonImage" alt="show image" />
          </div>
        </div>
        <div class="content">
          <pre>{{ review.content }}</pre>
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import { getAllSeasonReview, getDetailReview } from "@/api/review.js";
import { mapState, mapActions } from "vuex";

const reviewStore = "reviewStore";
export default {
  components: {
    Modal,
  },
  props: {
    type: String,
    info: Object,
    seasons: Array,
  },
  data() {
    return {
      modals: {
        modal: false,
        modal1: false,
        modal2: false,
      },
      reviews: [],
      seasonId: Number,
      selectedreviewid: 0,
      review: Object,
    };
  },
  created() {
    this.seasonId = this.info.seasonId;
    getAllSeasonReview(
      this.info.seasonId,
      (response) => {
        this.reviews = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    getReviews() {
      getAllSeasonReview(
        this.seasonId,
        (response) => {
          this.reviews = response.data.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailUser(id) {
      this.$router.push({
        name: "MyPage",
        params: { userId: id },
      });
    },
    ...mapActions(reviewStore, ["setReviewId"]),
    showDetailModal(id) {
      this.selectedreviewid = id;
      this.modals.modal1 = false;
      this.modals.modal2 = true;
      getDetailReview(id, (response) => {
        this.review = response.data.data;
      });
    },
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
  margin-top: 2px;
  margin-bottom: 2px;
  width: 6%;
  height: 6%;
  object-fit: cover;
  border-radius: 70%;
}
.modalHeader {
  cursor: pointer;
}
#reviewHeader {
  cursor: pointer;
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
  cursor: pointer;
}
.review {
  cursor: pointer;
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
  min-height: 150px;
  max-height: 150px;
  background-color: #f8f8f8;
  border-radius: 5%;
  padding: 5%;
  margin-bottom: 3%;
  overflow: hidden;
  text-overflow: ellipsis;
}
.username {
  font-size: 15px;
}
.inreview {
  margin-top: 4%;
  margin-left: 4%;
  margin-right: 4%;
  margin-bottom: 1%;
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
</style>
