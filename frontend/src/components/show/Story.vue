<template>
  <div>
    <b-card>
      <h5 class="main_title">작품설명</h5>
      <b-card-text v-if="description != ''">{{ description }}</b-card-text>
      <b-card-text v-if="otherSeasonDescription != ''">{{ otherSeasonDescription }}</b-card-text>
      <h6 class="more">더보기</h6>
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
    otherSeasonDescription: String,
  },
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.more {
  color: #3f46d8;
  float: right;
}
</style>
