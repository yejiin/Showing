<template>
  <div v-if="description.length > 1">
    <b-card>
      <span class="main_title">작품설명</span>
      <read-more
        class="my-3 script reflect_enter"
        more-str="더보기"
        :text="description"
        link="#"
        less-str="접기"
        :max-chars="250"
      ></read-more>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
export default {
  name: "Story",
  props: {
    description: String,
  },
};
</script>

<style scoped>
.main_title {
  font-size: 23px;
}

.script {
  color: black;
  font-weight: bold;
}

.card-text {
  margin-bottom: 0px;
  color: black;
}

.more {
  color: #3f46d8;
  float: right;
}

.reflect_enter {
  white-space: pre-line;
}
</style>
