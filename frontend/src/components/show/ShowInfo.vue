<template>
  <div>
    <div>
      <br /><br />
      <b-card>
        <b-card-text class="my_review">회원님의 리뷰는 개 입니다</b-card-text>
        <a target="_blank" class="btn btn-neutral btn-icon review" @click="setMyReviewListModalStates(true)">
          <span class="nav-link-inner--text">내 리뷰 보기</span>
        </a>

        <a target="_blank" class="btn btn-neutral btn-icon" @click="setWriteModalStates(true)">
          <span class="nav-link-inner--text">리뷰 작성</span>
        </a>
      </b-card>
      <br />
      <br />
      <review-list :seasonShowName="seasonShowName" :seasonShow="info"></review-list>
      <review-write :seasonShowName="seasonShowName" :seasonShow="info"></review-write>
    </div>
    <b-card>
      <div class="dropdown">
        <button
          class="btn p-0 btn-white dropdown-toggle"
          type="button"
          id="dropdownMenuButton"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          공연정보
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a @click="otherSeason(index)" v-for="(seasonDate, index) in seasons" :key="index" class="dropdown-item">
            {{ seasonDate.startDate }} ~ {{ seasonDate.endDate }}
          </a>
        </div>
      </div>
      <br />
      <br />

      <span class="badge badge-pill badge-success">{{ info.detailType }}</span
      >&nbsp;
      <span class="badge badge-pill badge-warning">{{ info.performanceAge }} 관람가</span>
      <br /><br />
      <b-row
        ><b-col
          ><b-card-text>일정 {{ info.startDate }} ~ {{ info.endDate }}</b-card-text></b-col
        ></b-row
      >
      <b-row
        ><b-col cols="6"
          ><b-card-text class="location">장소 {{ info.location }}</b-card-text></b-col
        >
        <b-col cols="6">
          <b-card-text>공연시간 {{ info.runingTime }}</b-card-text></b-col
        ></b-row
      >
      <actor-list :actor="actor"></actor-list>
    </b-card>
    <br />
    <br />
    <story :description="description"></story>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import ActorList from "@/components/show/ActorList";
import Story from "@/components/show/Story";

import ReviewListModalVue from "../review/MyReviewListModal.vue";
import ReviewWriteModalVue from "../review/ReviewWriteModal.vue";
import { detailSeasonShow } from "@/api/show.js";

const userStore = "userStore";
const reviewStore = "reviewStore";

export default {
  name: "ShowInfo",
  components: {
    ActorList,
    Story,
  },
  props: {
    info: Object,
    actor: Array,
    description: String,
    seasons: Array,
    seasonShowName: String,
    seasonShow: Object,
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
      showInfo: {},
      clickOtherSeason: false,
      modals: {
        listmodal: false,
        writemodal: false,
      },
    };
  },
  methods: {
    // 한 시즌 클릭
    otherSeason(index) {
      this.clickOtherSeason = true;

      detailSeasonShow(
        this.seasons[index].seasonId,
        (response) => {
          this.info = response.data.data;
          this.description = response.data.data.description;
          this.actor = response.data.data.actorList;
          console.log(this.actor);
        },
        (error) => {
          console.log(error);
        }
      );
    },
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

.more {
  color: #3f46d8;
  float: right;
}

.btn-white {
  color: #212529;
  background-color: #fff;
  border-color: #fff;
  -webkit-box-shadow: 0 0px 0px, 0 0px 0px;
  box-shadow: 0 0px 0px, 0 0px 0px;
  font-size: 20px;
}

.btn-white:hover {
  -webkit-box-shadow: 0 0px 0px, 0 0px 0px;
  box-shadow: 0 0px 0px, 0 0px 0px;
}
</style>
