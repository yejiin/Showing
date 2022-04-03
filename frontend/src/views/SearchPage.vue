<template>
  <div class="header">
    <div>
      <h5 class="main_title">'{{ $route.params.keyword }}' 에 대한 검색 결과입니다.</h5>
      <br />
      <search-list :performance-list="performanceList"></search-list>
    </div>
  </div>
</template>
<script>
import SearchList from "@/components/search/SearchList";

import { getContentsList } from "@/api/search.js";

export default {
  name: "SearchPage",
  components: {
    SearchList,
  },
  data() {
    return {
      performanceList: [],
    };
  },
  async created() {
    // 검색 결과 가져오기
    await getContentsList(
      this.$route.params.keyword,
      (response) => {
        this.performanceList = response.data.data;
        console.log(this.performanceList);
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped></style>
