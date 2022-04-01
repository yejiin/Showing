<template>
  <div class="mainbox mt-4">
    <span class="title">캐스팅</span>
    <div v-if="actor != null">
      <!-- carousel area -->
      <b-card-group deck class="mb-0 comment_list">
        <!-- 화살표 아이콘을 통해 슬라이딩 할 경우 -->
        <i class="ni ni-bold-left arrow"></i>
        <b-card
          v-for="(item, index) in currentPageCards"
          :key="index"
          class="mr-0 mb-2"
          tag="article"
          style="max-width: 20rem; border: 0px"
        >
          <b-img :src="actor[index].actorImage" rounded="circle" height="150px" />
          <br />
          <b-card-text v-if="actor[index].role != null">{{ actor[index].role }}</b-card-text>
          <b-card-text v-else><br /></b-card-text>
          <h5 class="actor_name">{{ actor[index].actorName }}</h5>
        </b-card>
        <i class="ni ni-bold-right arrow arrow_right"></i>
      </b-card-group>
      <br />
      <!-- pagination area -->
      <!-- 페이징을 사용해서 슬라이딩 할 경우 (아래 js 참고 코드 있음) -->
      <div class="pagination" v-if="cards.length > cardsPerPage">
        <div class="index" v-for="i in pageCount" :key="i" @click="next(i)" :class="{ active: currentPage(i) }"></div>
      </div>
      <br />
    </div>
  </div>
</template>

<script>
export default {
  name: "ActorList",
  props: {
    actor: Array,
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
.mainbox {
}
.title {
  font-weight: 700;
  font-size: 23px;
}
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

.arrow {
  margin-top: 10%;
  color: #9badf6;
}

.arrow_right {
  margin-left: 1%;
}

.card-text {
  color: black;
  text-align: center;
}

.actor_name {
  margin-top: 0px;
  text-align: center;
}
</style>
