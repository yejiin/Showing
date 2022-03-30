<template>
  <div class="header">
    <show-header :heading="heading" :performanceId="performanceId"></show-header>
    <my-review :seasonShowName="seasonShowName" :seasonShow="seasonShow" :previewReview="previewReview"></my-review>
    <show-info :info="info" :actor="actor" :description="description"></show-info>
    <word-cloud></word-cloud>
    <comment :previewReview="previewReview"></comment>
    <h5 class="main_title">비슷한 공연</h5>
    <br />
    <similar-show></similar-show>
  </div>
</template>
<script>
import ShowHeader from "@/components/show/ShowHeader";
import MyReview from "@/components/show/MyReview";
import ShowInfo from "@/components/show/ShowInfo";
import WordCloud from "@/components/show/WordCloud";
import Comment from "@/components/show/Comment";
import SimilarShow from "@/components/recommend/SimilarShow";

import { detailShow } from "@/api/show.js";
import { detailSeasonShow } from "@/api/show.js";

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
      },
      info: {},
      previewReview: [],
      actor: [],
      description: "",
      seasonShowName: "",
      seasonShow: {},
      performanceId: 0,
    };
  },
  async created() {
    await detailShow(
      "966",
      (response) => {
        this.heading.performanceId = response.data.data.performanceId;
        this.performanceId = response.data.data.performanceId;
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
      },
      (error) => {
        console.log(error);
      }
    );

    await detailSeasonShow(
      "1103",
      (response) => {
        this.seasonShow = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped></style>
