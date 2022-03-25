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
import Show from "@/components/recommend/Show";
import Actor from "@/components/recommend/Actor";
import RankingMusical from "@/components/show/RankingMusical";
import RankingTheater from "@/components/show/RankingTheater";
import RatingMusical from "@/components/show/RatingMusical";
import RatingTheater from "@/components/show/RatingTheater";

export default {
  name: "Main",
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
