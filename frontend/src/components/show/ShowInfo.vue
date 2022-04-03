<template>
  <div>
    <div v-if="this.isLogin">
      <br /><br />
      <b-card>
        <span class="my_review">{{ userInfo.nickName }}님의 리뷰는 개 입니다</span>
        <a target="_blank" class="btn btn-neutral btn-icon review button" @click="setMyReviewListModalStates(true)">
          <span class="nav-link-inner--text">내 리뷰 보기</span>
        </a>
        <a target="_blank" class="btn btn-neutral btn-icon button mr-2" @click="setWriteModalStates(true)">
          <span class="nav-link-inner--text">리뷰 작성</span>
        </a>
      </b-card>
      <review-list :seasonShowName="seasonShowName" :seasonShow="info" :performanceId="performanceId"></review-list>
      <review-write :seasonShowName="seasonShowName" :seasonShow="info"></review-write>
    </div>
    <br /><br />
    <b-card>
      <div class="dropdown">
        <button
          class="btn p-0 btn-white dropdown-toggle subTitle"
          type="button"
          id="dropdownMenuButton"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          공연정보&nbsp;&nbsp;
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a @click="otherSeason(index)" v-for="(seasonDate, index) in seasons" :key="index" class="dropdown-item">
            {{ seasonDate.startDate }} ~ {{ seasonDate.endDate }}
          </a>
        </div>
      </div>
      <span class="badge badge-pill badge-success ml-4">{{ info.detailType }}</span
      >&nbsp;
      <span class="badge badge-pill badge-warning">{{ info.performanceAge }} 관람가</span>
      <br /><br />
      <b-row
        ><b-col class="ml-2 my-2" cols="3">일정 </b-col>
        <b-col cols="6">{{ info.startDate }} ~ {{ info.endDate }}</b-col>
      </b-row>
      <b-row v-if="info.location != null"
        ><b-col class="ml-2 my-2" cols="3">장소 </b-col>
        <b-col>{{ info.location }}</b-col>
      </b-row>
      <b-row>
        <b-col class="ml-2 my-2" cols="3"> 공연시간 </b-col>
        <b-col>{{ info.runingTime }}</b-col>
      </b-row>
      <span class="subTitle mt-2">캐스팅</span>

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
  props: {
    info: Object,
    actor: Array,
    description: String,
    seasons: Array,
    seasonShowName: String,
    seasonShow: Object,
    performanceId: Number,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapGetters({
      isLogin: "userStore/isLogin",
    }),
  },
  components: {
    ActorList,
    Story,
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
        },
        (error) => {
          console.log(error);
        }
      );
    },
    ...mapActions(reviewStore, ["setMyReviewListModalState", "setWriteReviewModalState"]),
    setMyReviewListModalStates(status) {
      this.setMyReviewListModalState(status);
    },
    setWriteModalStates(status) {
      this.setWriteReviewModalState(status);
    },
  },
};
</script>

<style scoped>
.my_review {
  vertical-align: middle;
}
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

.button {
  float: right;
}

.subTitle {
  font-weight: 600;
  color: #525f7f;
  font-size: 23px;
}
</style>
