<template>
  <div>
    <h5 class="main_title comment_title">비슷한 공연</h5>
    <br />
    <!-- carousel area -->
    <b-card-group deck class="mb-0">
      <!-- 화살표 아이콘을 통해 슬라이딩 할 경우 -->
      <i class="ni ni-bold-left arrow"></i>
      <b-card
        v-for="(item, index) in currentPageCards"
        :key="index"
        class="mr-0 mb-3"
        :img-src="similarList[index].lastSeasonImage"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem"
        @click="detailShow(index)"
      >
        <!-- card content -->
        {{ similarList[index].performanceName }}&nbsp;
        <b-badge class="mr-1" pill variant="light"
          ><b-icon icon="star-fill" scale="0.8"></b-icon> {{ similarList[index].starPointAverage }}</b-badge
        >
        <b-badge pill variant="primary">공연중</b-badge>
        <br />
        {{ similarList[index].lastSeasonStartDate }} ~ {{ similarList[index].lastSeasonEndDate }}
      </b-card>
      <i class="ni ni-bold-right arrow arrow_right"></i>
    </b-card-group>
    <!-- pagination area -->
    <!-- 페이징을 사용해서 슬라이딩 할 경우 (아래 js 참고 코드 있음) -->
    <div class="pagination" v-if="cards.length > cardsPerPage">
      <div class="index" v-for="i in pageCount" :key="i" @click="next(i)" :class="{ active: currentPage(i) }"></div>
    </div>
    <br />
  </div>
</template>

<script>
export default {
  name: "SimilarShow",
  props: {
    similarList: Array,
  },
  data() {
    return {
      cards: [
        {
          //Data in the card as objects
        },
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
      ],
      paginatedCards: [],
      pageCount: 0,
      cardsPerPage: 3,
      currentPageIndex: 0,
    };
  },
  computed: {
    currentPageCards() {
      this.createPages();

      return this.paginatedCards[this.currentPageIndex];
    },
  },
  methods: {
    detailShow(index) {
      this.$router.push({
        name: "ShowDetail",
        params: { showId: this.similarList[index].performanceId },
      });
    },
    currentPage(i) {
      return i - 1 === this.currentPageIndex;
    },

    createPages() {
      let cardsLength = this.cards.length;
      let fullPagesCount = Math.floor(cardsLength / this.cardsPerPage);

      if (cardsLength > this.cardsPerPage) {
        this.pageCount = 0;
        for (let i = 0; i < fullPagesCount * this.cardsPerPage; i += this.cardsPerPage) {
          this.paginatedCards[this.pageCount] = this.cards.slice(i, i + this.cardsPerPage);
          this.pageCount++;
        }

        this.paginatedCards[this.pageCount] = this.cards.slice(cardsLength - this.cardsPerPage, cardsLength);
        this.pageCount = this.pageCount + 1;
      } else {
        this.paginatedCards[0] = this.cards;
      }
    },

    next(i) {
      this.currentPageIndex = i - 1;
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
