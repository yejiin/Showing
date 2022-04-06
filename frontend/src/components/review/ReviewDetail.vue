<template>
  <div>
    <modal :show.sync="modals.reviewDetail" modal-classes="modal-dialog">
      <div id="detail">
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
          @click="setReviewDetailModalState(false)"
        >
          &times;
        </button>
        <div class="modalHeader title2" @click="detailUser(review.userId)">
          <img :src="review.userImage" alt="profile image" class="profile2" />
          <h6 class="inline2 title2">{{ review.userName }}</h6>
        </div>
        <div style="width: 100%; position: relative">
          <div class="showInfo2 left mb-3">
            <div>
              <h3>{{ review.performanceName }}</h3>
              <p style="font-size: 13px">{{ review.startDate }}&nbsp;~&nbsp;{{ review.endDate }}</p>
            </div>
            <label for="date">관람일정</label>
            <p class="inline2 right2" type="input">{{ review.viewDate }}</p>
            <br />
            <label for="time">관람시간</label>
            <p class="inline2" type="input" v-if="review.viewTime">{{ review.viewTime.substring(0, 5) }}</p>
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
          <pre class="reflect_enter">{{ review.content }}</pre>
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import { mapState, mapActions } from "vuex";

const reviewStore = "reviewStore";
export default {
  components: {
    Modal,
  },
  props: {
    review: Object,
    flag: Boolean,
  },
  data() {
    return {
      seasonId: Number,
      selectedreviewid: 0,
    };
  },
  computed: {
    ...mapState(reviewStore, ["modals"]),
  },
  methods: {
    detailUser(id) {
      this.$router.push({
        name: "MyPage",
        params: { userId: id },
      });
      this.$router.go();
    },
    ...mapActions(reviewStore, ["setReviewDetailModalState"]),
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
label,
h3 {
  font-weight: bold;
}
.reflect_enter {
  white-space: pre-line;
}
</style>
