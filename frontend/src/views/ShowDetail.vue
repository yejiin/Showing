<template>
  <div class="header">
    <show-header></show-header>
    <my-review></my-review>
    <show-info></show-info>
    <word-cloud></word-cloud>
    <comment></comment>
    <h5 class="main_title">비슷한 공연</h5>
    <br />
    <show :similarRecommend="similarRecommend" :type="type"></show>
  </div>
</template>
<script>
import ShowHeader from "@/components/show/ShowHeader";
import MyReview from "@/components/show/MyReview";
import ShowInfo from "@/components/show/ShowInfo";
import WordCloud from "@/components/show/WordCloud";
import Comment from "@/components/show/Comment";
import Show from "@/components/recommend/Show";

import { getSimilarRecommend } from "@/api/recommend.js";

export default {
  name: "ShowDetail",
  components: {
    ShowHeader,
    MyReview,
    ShowInfo,
    WordCloud,
    Comment,
    Show,
  },
  data() {
    return {
      type: "",
      similarRecommend: [],
    };
  },
  async created() {
    await getSimilarRecommend(
      "1",
      (response) => {
        this.similarRecommend = response.data.data;
        this.type = "similar";
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped></style>
