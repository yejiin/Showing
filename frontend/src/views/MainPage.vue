<template>
  <div class="header">
    <div v-if="this.$store.state.userStore.isLogin">
      <h5 class="main_title">{{ this.$store.state.userStore.userInfo.nickName }}님을 위한 추천 공연</h5>
      <br />
      <show :mainRecommendList="mainRecommendList"></show>
    </div>
    <div v-for="(item, index) in actorRecommnedList" :key="index">
      <h5 class="main_title">선호 배우 {{ item.actorName }}의 다른 공연</h5>
      <br />
      <actor :actorPerformanceList="item"></actor>
    </div>
    <div>
      <h5 class="main_title">뮤지컬 평균 별점 순위</h5>
      <br />
      <rating-musical :musicalList="musicalList"></rating-musical>
    </div>
    <div>
      <h5 class="main_title">연극 평균 별점 순위</h5>
      <br />
      <rating-play :playList="playList"></rating-play>
    </div>
  </div>
</template>
<script>
import Actor from "@/components/recommend/Actor";
import Show from "@/components/recommend/Show";
import RatingMusical from "@/components/recommend/RatingMusical";
import RatingPlay from "@/components/recommend/RatingPlay";

import { getMainRecommend } from "@/api/recommend.js";
import { getRankingShow } from "@/api/show.js";

export default {
  name: "MainPage",
  components: {
    Show,
    Actor,
    RatingMusical,
    RatingPlay,
  },
  data() {
    return {
      musicalList: [],
      playList: [],
      mainRecommendList: [],
      actorRecommnedList: [],
      actorName: "",
    };
  },
  async created() {
    // 평균 별점 순위 리스트 불러오기
    await getRankingShow(
      (response) => {
        this.musicalList = response.data.data.musicalAvgList;
        this.playList = response.data.data.playAvgList;
      },
      (error) => {
        console.log(error);
      }
    );
    // 추천 공연 리스트 불러오기
    await getMainRecommend(
      this.$store.state.userStore.userInfo.userId,
      (response) => {
        this.mainRecommendList = response.data.data.recommendListByUser;
        this.actorRecommnedList = response.data.data.recommendListByActor;
        this.actorName = response.data.data.recommendListByActor[0].actorName;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped></style>
