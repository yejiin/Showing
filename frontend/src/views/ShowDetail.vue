<template>
  <div class="header">
    <show-header></show-header>
    <my-review></my-review>
    <show-info></show-info>
    <story></story>
    <word-cloud></word-cloud>
    <comment></comment>
    <h5 class="main_title">비슷한 공연</h5>
    <br />
    <show></show>
  </div>
</template>
<script>
import ShowHeader from "@/components/show/ShowHeader";
import MyReview from "@/components/show/MyReview";
import ShowInfo from "@/components/show/ShowInfo";
import Story from "@/components/show/Story";
import WordCloud from "@/components/show/WordCloud";
import Comment from "@/components/show/Comment";
import Show from "@/components/recommend/Show";

export default {
  name: "ShowDetail",
  components: {
    ShowHeader,
    MyReview,
    ShowInfo,
    Story,
    WordCloud,
    Comment,
    Show,
  },
};
</script>

<style scoped></style>
