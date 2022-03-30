<template>
  <div>
    <b-card>
      <h5 class="main_title">선호태그</h5>
      <word-cloud></word-cloud>
      <hr />
      <h5 class="main_title">선호배우</h5>
      <actor-list></actor-list>
    </b-card>
  </div>
</template>
<script>
import WordCloud from "@/components/show/WordCloud";
import ActorList from "@/components/show/ActorList";
export default {
  name: "UserFavorite",
  components: {
    ActorList,
    WordCloud,
  },
};
</script>
<style></style>
