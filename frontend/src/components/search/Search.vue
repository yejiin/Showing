<template>
  <div>
    <base-input
      class="searchInput"
      placeholder="공연을 검색해보세요"
      addon-left-icon="ni ni-zoom-split-in"
      v-model="content"
      @input="searchData"
      @change="detailShow($event)"
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
import axios from "axios";

export default {
  name: "Search",
  components: {},

  data() {
    return {
      content: "",
      contentId: "",
      performanceList: [],
      performancdIndex: null,
      searchId: null,
    };
  },
  methods: {
    searchData() {
      axios
        .get("https://j6a301.p.ssafy.io/api/v1/search?keyword=" + this.content)
        .then((res) => {
          this.performanceList = res.data.data;
        })
        .catch((err) => {
          console.log(err);
        });
      console.log(this.performanceList);
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
