<template>
  <div class="header">
    <!-- 헤더 -->
    <show-header :heading="heading"></show-header>
    <!-- 공연 상세 정보 -->
    <show-info
      :info="info"
      :actor="actor"
      :description="description"
      :seasons="seasons"
      :seasonShowName="seasonShowName"
    ></show-info>
    <word-cloud></word-cloud>
    <!-- 리뷰 리스트 -->
    <comment :previewReview="previewReview"></comment>
    <!-- 비슷한 공연 -->
    <similar-show :similarList="similarList"></similar-show>
  </div>
</template>
<script>
import ShowHeader from "@/components/show/ShowHeader";
import MyReview from "@/components/show/MyReview";
import ShowInfo from "@/components/show/ShowInfo";
import WordCloud from "@/components/show/WordCloud";
import Comment from "@/components/show/Comment";
import SimilarShow from "@/components/recommend/SimilarShow";

import { getRating } from "@/api/rating.js";
import { detailShow, getSeasonShow } from "@/api/show.js";

export default {
  name: "ShowDetail",
  components: {
    ShowHeader,
    MyReview,
    ShowInfo,
    WordCloud,
    Comment,
    SimilarShow,
  },
  data() {
    return {
      heading: {
        performanceId: 0,
        performanceImage: "",
        performanceName: "",
        starPointAverage: 0,
        ratingCount: 0,
        proceedFlag: 0,
        starId: 0,
        rating: 0,
      },
      info: {},
      previewReview: [],
      actor: [],
      description: "",
      seasonShowName: "",
      seasonShow: {},
      similarList: [],
      seasons: [],
    };
  },
  async created() {
    this.heading.performanceId = this.$route.params.showId;
    // 공연 상세 정보 가져오기
    await detailShow(
      this.$route.params.showId,
      (response) => {
        this.heading.performanceId = response.data.data.performanceId;
        this.heading.performanceImage = response.data.data.performanceImage;
        this.heading.performanceName = response.data.data.performanceName;
        this.seasonShowName = response.data.data.performanceName;
        this.heading.starPointAverage = response.data.data.starPointAverage;
        this.heading.ratingCount = response.data.data.ratingCount;
        this.heading.proceedFlag = response.data.data.seasonRes.proceedFlag;

        this.info = response.data.data.seasonRes;
        this.actor = response.data.data.seasonRes.actorList;
        this.description = response.data.data.seasonRes.description;

        this.previewReview = response.data.data.previewReviewList;
        this.similarList = response.data.data.similarPerformanceList;

        console.log(this.info);
        console.log("this.info----------");

        // 로그인 시 별점 불러오기
        if (this.$store.getters["userStore/isLogin"])
          getRating(
            this.$store.getters["userStore/userInfo"].id,
            this.heading.performanceId,
            (response) => {
              this.heading.starId = response.data.data.starId;
              this.heading.rating = response.data.data.rating;
            },
            (error) => {
              console.log(error);
            }
          );
      },
      (error) => {
        console.log(error);
      }
    );
    // 퍼포먼스 별 시즌 목록 불러오기
    await getSeasonShow(
      this.$route.params.showId,
      (response) => {
        this.seasons = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped></style>
