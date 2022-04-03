<template>
  <div class="header">
    <div v-if="this.$store.state.userStore.isLogin">
      <h5 class="main_title">{{ this.$store.state.userStore.userInfo.nickName }}님을 위한 추천 공연</h5>
      <br />
      <recommend-list :performance-list="mainRecommendList"></recommend-list>
    </div>
    <div v-for="(item, index) in actorRecommnedList" :key="index">
      <h5 class="main_title">선호 배우 {{ item.actorName }}의 다른 공연</h5>
      <br />
      <recommend-list :performance-list="item.performanceInfoList"></recommend-list>
    </div>
    <div>
      <h5 class="main_title">뮤지컬 평균 별점 순위</h5>
      <br />
      <recommend-list :performance-list="musicalList"></recommend-list>
    </div>
    <div>
      <h5 class="main_title">연극 평균 별점 순위</h5>
      <br />
      <recommend-list :performance-list="playList"></recommend-list>
    </div>
  </div>
</template>
<script>
import RecommendList from "@/components/recommend/RecommendList";

import { getMainRecommend } from "@/api/recommend.js";
import { getRankingShow } from "@/api/show.js";

export default {
  name: "MainPage",
  components: {
    RecommendList,
  },
  data() {
    return {
      musicalList: [],
      playList: [],
      mainRecommendList: [],
      actorRecommnedList: [],
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
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped>
.main_title {
  font-size: 1.5em;
  margin-left: 0.5em;
}
</style>
