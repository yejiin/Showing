<template>
  <vue-simple-suggest
    placeholder="공연과 배우를 검색해보세요"
    class="searchInput"
    min-length="0"
    v-model="chosen"
    :list="showAndActorList"
    :filter-by-query="true"
  >
  </vue-simple-suggest>
</template>

<script>
import VueSimpleSuggest from "vue-simple-suggest";
import "vue-simple-suggest/dist/styles.css"; // Optional CSS

export default {
  components: {
    VueSimpleSuggest,
  },
  data() {
    return {
      chosen: "",
    };
  },
  methods: {
    showAndActorList() {
      return ["지킬 앤 하이드", "지킬 앤 하이도", "자킬 앤 하이드"];
    },
  },
};
</script>

<style scoped>
.searchInput {
  margin-bottom: 0px !important;
  margin-right: 5%;
  width: 120%;
}
</style>
