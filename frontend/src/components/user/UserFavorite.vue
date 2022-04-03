<template>
  <div>
    <b-card>
      <h4 class="main_title">선호태그</h4>
      <word-cloud :wordcount="wordcount"></word-cloud>
      <hr />
      <div>
        <h4 class="main_title">선호배우</h4>
        <actor-list :actor="actor"></actor-list>
      </div>
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
  props: {
    favoriteActor: Array,
    tagWordcount: Array,
  },
  computed: {
    actor: function () {
      return this.favoriteActor;
    },
    wordcount: function () {
      return this.tagWordcount;
    },
  },
};
</script>
<style></style>
