<template>
  <div class="header">
    <show></show>
    <actor></actor>
    <ranking-musical></ranking-musical>
    <ranking-theater></ranking-theater>
    <rating-musical></rating-musical>
    <rating-theater></rating-theater>
  </div>
</template>
<script>
import Show from "@/components/recommend/show";
import Actor from "@/components/recommend/actor";
import RankingMusical from "@/components/show/rankingMusical";
import RankingTheater from "@/components/show/rankingTheater";
import RatingMusical from "@/components/show/ratingMusical";
import RatingTheater from "@/components/show/ratingTheater";

export default {
  name: "components",
  components: {
    Show,
    Actor,
    RankingMusical,
    RankingTheater,
    RatingMusical,
    RatingTheater,
  },
};
</script>

<style scoped>
.header {
  padding-top: 10%;
  padding-left: 5%;
  padding-right: 5%;
}
</style>
