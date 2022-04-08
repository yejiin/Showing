<template>
  <div>
    <base-input
      alternative
      class="searchInput"
      addon-left-icon="ni ni-zoom-split-in"
      placeholder="공연을 검색해보세요"
      v-model="keyword"
      @keyup.enter="getContentsList"
    >
    </base-input>
  </div>
</template>

<script>
export default {
  name: "Search",
  components: {},

  data() {
    return {
      keyword: "",
    };
  },

  methods: {
    getContentsList() {
      if (this.keyword == "" || this.keyword.length < 2) {
        this.showToast("error", "검색어는 2글자 이상 입력해주세요.");
        return;
      }
      this.$router.replace({
        name: "SearchPage",
        params: { keyword: this.keyword },
      });
      this.keyword = "";
    },
    // confirm 메시지 표시
    showToast(typeName, message) {
      this.$toast(message, {
        type: typeName,
      });
    },
  },
};
</script>

<style scoped>
.searchInput {
  margin: 0px;
}
</style>
