<template>
  <div>
    <b-card>
      <h4 class="main_title">선호 태그</h4>
      <word-cloud :wordcount="wordcount"></word-cloud>
      <div>
        <div class="my-5 mx-auto" style="text-align: center" v-if="wordcount == 0">
          <b-card-text>선호 태그가 없습니다.</b-card-text>
          <br /><br />
        </div>
      </div>
      <hr />
      <div>
        <h4 class="main_title">선호 배우</h4>
        <actor-list :actor="actor"></actor-list>
        <div class="my-5 mx-auto" style="text-align: center" v-if="actor == 0">
          <b-card-text>선호 배우가 없습니다.</b-card-text>
          <br /><br />
        </div>
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
