<template>
  <div>
    <!-- carousel area -->
    <b-card-group deck class="mb-0">
      <!-- 화살표 아이콘을 통해 슬라이딩 할 경우 -->
      <i class="ni ni-bold-left arrow"></i>
      <b-card
        v-for="(item, index) in currentPageCards"
        :key="index"
        class="mr-0 mb-2"
        img-src="https://picsum.photos/300/400/?image=25"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem"
      >
        <!-- card content -->
        {{ musicalList[index].performanceName }}&nbsp;
        <b-badge class="mr-1" pill variant="light"
          ><b-icon icon="star-fill" scale="0.8"></b-icon> {{ musicalList[index].starPointAverage }}</b-badge
        >
        <b-badge v-if="musicalList[index].lastSeasonProceedFlag == 0" pill variant="danger">공연완료</b-badge>
        <b-badge v-if="musicalList[index].lastSeasonProceedFlag == 1" pill variant="primary">공연중</b-badge>
        <b-badge v-if="musicalList[index].lastSeasonProceedFlag == 2" pill variant="warning">예정</b-badge>
        <br />
        {{ musicalList[index].lastSeasonStartDate }} ~ {{ musicalList[index].lastSeasonEndDate }}
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
  name: "Actor",
  props: {
    musicalList: Array,
    playList: Array,
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
      cardsPerPage: 6,
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
