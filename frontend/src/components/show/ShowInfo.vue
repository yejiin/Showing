<template>
  <div>
    <div v-if="this.isLogin">
      <br /><br />
      <b-card>
        <div class="box">
          <span class="my_review" v-if="reviewList != null && reviewList.length != 0"
            >{{ userInfo.nickName }}님의 리뷰는 {{ reviewList.length }}개 입니다</span
          >
          <span v-else>{{ userInfo.nickName }}님이 등록한 리뷰가 없습니다.</span>
        </div>
        <div class="float-right" style="display: inline-block">
          <a target="_blank" class="btn btn-neutral btn-icon review button" @click="setMyReviewListModalStates(true)">
            <span class="nav-link-inner--text">내 리뷰 보기</span>
          </a>
          <a target="_blank" class="btn btn-neutral btn-icon button mr-2" @click="setWriteModalStates(true)">
            <span class="nav-link-inner--text">리뷰 작성</span>
          </a>
        </div>
      </b-card>
      <review-list
        @myReviewList="myReviewList"
        :key="setReview"
        :seasonShowName="seasonShowName"
        :seasonShow="showInformation"
        :performanceId="performanceId"
      ></review-list>
      <review-write
        @myReviewList="myReviewList"
        @setWrite="setReviewCount"
        :setwrite="setReview"
        :seasonShowName="seasonShowName"
        :seasonShow="showInformation"
      ></review-write>
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
          <a
            @click="otherSeason(index)"
            v-for="(seasonDate, index) in seasons"
            :key="index"
            class="dropdown-item"
            style="cursor: pointer"
          >
            {{ seasonDate.startDate }} ~ {{ seasonDate.endDate }}
          </a>
        </div>
      </div>
      <span class="badge badge-pill badge-success ml-4">{{ showInformation.detailType }}</span
      >&nbsp;
      <span class="badge badge-pill badge-warning">{{ showInformation.performanceAge }} 관람가</span>
      <br /><br />
      <b-row
        ><b-col class="ml-2 my-2" cols="3">일정 </b-col>
        <b-col cols="6">{{ showInformation.startDate }} ~ {{ showInformation.endDate }}</b-col>
      </b-row>
      <b-row v-if="showInformation.location != null"
        ><b-col class="ml-2 my-2" cols="3">장소 </b-col>
        <b-col>{{ showInformation.location }}</b-col>
      </b-row>
      <b-row>
        <b-col class="ml-2 my-2" cols="3"> 공연시간 </b-col>
        <b-col>{{ showInformation.runingTime }}</b-col>
      </b-row>
      <div class="subTitle mt-3">캐스팅</div>
      <actor-list :actor="showActor"></actor-list>
    </b-card>
    <br />
    <br />
    <story :description="showDescription"></story>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import ActorList from "@/components/show/ActorList";
import Story from "@/components/show/Story";

import ReviewListModalVue from "../review/MyReviewListModal.vue";
import ReviewWriteModalVue from "../review/ReviewWriteModal.vue";
import { detailSeasonShow } from "@/api/show.js";
import { getMyShowReview } from "@/api/review.js";

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
    showInformation : function() {
      if(this.clickOtherSeason===true){
        return this.OtherShowInfo
      }else{
        return this.info
      }
    },
    showActor : function() {
      if(this.clickOtherSeason===true){
        return this.OtherActor
      }else{
        return this.actor
      }
    },
    showDescription : function() {
      if(this.clickOtherSeason===true){
        return this.OtherDesciption
      }else{
        return this.description
      }
    }
    
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
      setReview: 0,
      reviewList: [],
      OtherShowInfo: {},
      OtherActor:[],
      OtherDesciption:'',
    };
  },
  created() {
    getMyShowReview(
      this.performanceId,
      this.userInfo.userId,
      (response) => {
        this.reviewList = response.data.data;
      },
      (fail) => {
        console.log(fail);
      }
    );
  },
  methods: {
    // 한 시즌 클릭
    otherSeason(index) {
      this.clickOtherSeason = true;

      detailSeasonShow(
        this.seasons[index].seasonId,
        (response) => {
          this.OtherShowInfo = response.data.data;
          this.OtherDesciption = response.data.data.description;
          this.OtherActor = response.data.data.actorList;
        },
        (error) => {
          console.log(error);
        }
      );
    },

    myReviewList() {
      getMyShowReview(
        this.performanceId,
        this.userInfo.userId,
        (response) => {
          this.reviewList = response.data.data;
        },
        (fail) => {
          console.log(fail);
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
    setReviewCount(value) {
      this.setReview = value;
    },
  },
  mounted() {
    this.setMyReviewListModalState(false);
    this.setWriteReviewModalState(false);
  },
};
</script>

<style scoped>
.my_review {
  vertical-align: middle;
  min-height: 100%;
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

.box {
  min-height: 100%;
  display: inline-block;
  vertical-align: middle;
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
