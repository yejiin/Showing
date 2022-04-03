<template>
  <div>
    <base-input
      class="searchInput"
      placeholder="공연을 검색해보세요"
      addon-left-icon="ni ni-zoom-split-in"
      v-model="keyword"
      @input="searchData"
      @enter="detailShow($event)"
      list="showList"
    >
    </base-input>
    <datalist id="showList">
      <option v-for="(item, index) in performanceList" :key="index" :value="item.performanceId">
        {{ item.performanceName }}
      </option>
    </datalist>
  </div>
</template>

<script>
import { getContentsList } from "@/api/search.js";

export default {
  name: "Search",
  components: {},

  data() {
    return {
      keyword: "",
      contentId: "",
      performanceList: [],
      performancdIndex: null,
      searchId: null,
    };
  },
  methods: {
    searchData() {
      getContentsList(
        this.keyword,
        (response) => {
          console.log(this.keyword);
          this.performanceList = response.data.data;
          console.log(response.data.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    detailShow(event) {
      console.log("이거어떻게?");
      console.log(event.target.value);
      this.$router.push({
        name: "ShowDetail",
        params: { showId: event.target.value },
      });
    },
  },
};
</script>

<style scoped>
.searchInput {
  margin-bottom: 0px !important;
  margin-right: 5%;
}
</style>
