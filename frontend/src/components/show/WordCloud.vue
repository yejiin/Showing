<template>
  <div>
    <b-card v-if="wordcount.length != 0">
      <wordcloud
        :rotate="{ from: 0, to: 0, numOfOrientation: 0 }"
        :data="wordcount"
        nameKey="name"
        valueKey="value"
        :showTooltip="false"
        :color="myColors"
        :fontSize="[30, 100]"
      >
      </wordcloud>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
import wordcloud from "vue-wordcloud";

export default {
  name: "WordCloud",
  props: {
    wordcount: Array,
  },
  components: {
    wordcloud,
  },
  data() {
    return {
      // Red
      // myColors: ["#fca5a5", "#f87171", "#ef4444", "#dc2626", "#b91c1c"],
      // Orange
      // myColors: ["#fdba74", "#fb923c", "#f97316", "#ea580c", "#c2410c"],
      // Amber
      myColors: ["#fcd34d", "#fbbf24", "#f59e0b", "#d97706", "#b45309"],
      // Purple
      // myColors: ["#d8b4fe", "#c084fc", "#a855f7", "#9333ea", "#7e22ce"],
    };
  },
};
</script>

<style scoped>
.card-title {
  margin-bottom: 0px;
  text-align: center;
}
</style>
