<template>
  <div>
    <input
      class="searchInput"
      placeholder="공연을 검색해보세요"
      :value="keyword"
      @input="keyword = $event.target.value"
      @keyup.enter="getContentsList"
    />
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
        alert("검색어는 2글자 이상 입력해주세요");
        return;
      }
      this.$router.replace({
        name: "SearchPage",
        params: { keyword: this.keyword },
      });
      this.keyword = "";
    },
  },
};
</script>

<style scoped>
.searchInput {
  margin-bottom: 0px !important;
  margin-right: 5%;
  border-top: none;
  border-left: none;
  border-right: none;
}
.searchInput:focus {
  outline: none;
}
</style>
