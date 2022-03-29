<template>
  <div class="header">
    <h5 class="main_title">00님을 위한 추천 공연</h5>
    <br />
    <show :mainRecommendList="mainRecommendList"></show>
    <h5 class="main_title">선호 배우 000의 다른 공연</h5>
    <br />
    <actor></actor>
    <br />
    <h5 class="main_title">뮤지컬 평균 별점 순위</h5>
    <br />
    <rating-musical :musicalList="musicalList"></rating-musical>
    <h5 class="main_title">연극 평균 별점 순위</h5>
    <br />
    <rating-play :playList="playList"></rating-play>
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
    };
  },
  async created() {
    await getRankingShow(
      (response) => {
        this.musicalList = response.data.data.musicalAvgList;
        this.playList = response.data.data.playAvgList;
        // console.log(response.data.data);
      },
      (error) => {
        console.log(error);
      }
    );

    await getMainRecommend(
      "1",
      (response) => {
        this.mainRecommendList = response.data.data;
        // console.log(response.data.data);
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped></style>
