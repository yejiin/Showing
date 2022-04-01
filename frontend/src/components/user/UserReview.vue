<template>
  <div>
    <h5 class="main_title">내 리뷰</h5>
    <b-card-group deck class="mb-0">
      <b-card
        id="itemList"
        v-for="item in createPages()"
        :key="item.index"
        class="mr-0 mb-2"
        :img-src="item.performanceImage"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem"
      >
        <!-- card content -->
        {{ item.performanceName }}&nbsp;{{ item.viewDate }}
        <br />
      </b-card>
    </b-card-group>
    <!-- pagination area -->
    <div>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="itemList"
        align="center"
      ></b-pagination>
    </div>
    <br />
  </div>
</template>
<script>
export default {
  name: "UserReview",
  data() {
    return {
      currentPage: 1,
      pageCount: 0,
      reviewsPerPage: 5,
      perPage: 5,
    };
  },
  props: {
    reviewList: Array,
  },
  computed: {
    rows: function () {
      return this.reviewList.length;
    },
  },

  methods: {
    createPages() {
      return this.reviewList.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
    },
  },
};
</script>
<style scoped>
.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}

.index {
  margin-left: 10px;
  width: 10px;
  height: 10px;
  background: #9badf6;
}

.active {
  width: 13px;
  height: 13px;
}

.card {
  border: 0px;
}

.card-img-top {
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
}

.card-body {
  padding-left: 0px;
  padding-right: 0px;
}

.arrow {
  margin-top: 10%;
  color: #9badf6;
}

.arrow_right {
  margin-left: 1%;
}
</style>
