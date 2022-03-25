<template>
  <div class="header">
    <h5 class="main_title">00님을 위한 추천 공연</h5>
    <br />
    <show></show>
    <h5 class="main_title">선호 배우 000의 다른 공연</h5>
    <br />
    <actor></actor>
    <h5 class="main_title">현재 뮤지컬 예매율 순위</h5>
    <br />
    <ranking-musical></ranking-musical>
    <h5 class="main_title">현재 연극 예매율 순위</h5>
    <br />
    <ranking-theater></ranking-theater>
    <h5 class="main_title">뮤지컬 평균 별점 순위</h5>
    <br />
    <rating-musical></rating-musical>
    <h5 class="main_title">연극 평균 별점 순위</h5>
    <br />
    <rating-theater></rating-theater>
  </div>
</template>
<script>
import Show from "@/components/recommend/Show";

export default {
  name: "MainPage",
  components: {
    Show: Show,
    Actor: Show,
    RankingMusical: Show,
    RankingTheater: Show,
    RatingMusical: Show,
    RatingTheater: Show,
  },
};
</script>

<style scoped></style>
