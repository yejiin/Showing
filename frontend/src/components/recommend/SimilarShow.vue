<template>
  <div>
    <span class="main_title comment_title">비슷한 공연</span>
    <br />
    <div style="text-align: center" v-if="similarList == 0">
      <b-card-text>비슷한 공연이 없습니다.</b-card-text>
    </div>
    <div v-else>
      <!-- carousel area -->
      <carousel :perPage="4">
        <slide class="p-1 mt-3" v-for="(item, index) in similarList" :key="index">
          <b-card
            class="mr-0 mb-2 rounded card-lift--hover"
            @click="detailShow(similarList[index].performanceId)"
            style="cursor: pointer"
          >
            <!-- card content -->
            <img :src="similarList[index].lastSeasonImage" class="mb-3 mr-3 rounded" />
            <div class="name">
              <strong>{{ similarList[index].performanceName }}</strong>
            </div>
            <div class="tags">
              <b-badge class="mr-1" pill variant="light"
                ><b-icon icon="star-fill" scale="0.8"></b-icon>
                {{ (similarList[index].starPointAverage / 2).toFixed(2) }}</b-badge
              >&nbsp;
              <b-badge v-if="similarList[index].lastSeasonProceedFlag == 0" pill variant="danger">공연완료</b-badge>
              <b-badge v-if="similarList[index].lastSeasonProceedFlag == 1" pill variant="primary">공연중</b-badge>
              <b-badge v-if="similarList[index].lastSeasonProceedFlag == 2" pill variant="warning">예정</b-badge>
              &nbsp;
              <b-badge v-if="similarList[index].performanceType == 1" pill variant="info">뮤지컬</b-badge>
              <b-badge v-if="similarList[index].performanceType == 2" pill variant="success">연극</b-badge>
            </div>
            {{ similarList[index].lastSeasonStartDate }} ~ {{ similarList[index].lastSeasonEndDate }}
          </b-card>
        </slide>
      </carousel>
    </div>

    <br />
  </div>
</template>

<script>
import { Carousel, Slide } from "vue-carousel";

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
      ],
      paginatedCards: [],
      pageCount: 0,
      cardsPerPage: 3,
      currentPageIndex: 0,
    };
  },
  components: {
    Carousel,
    Slide,
  },
  computed: {
    currentPageCards() {
      this.createPages();

      return this.paginatedCards[this.currentPageIndex];
    },
  },
  methods: {
    detailShow(performanceId) {
      console.log(performanceId);
      this.$router.push({
        name: "ShowDetail",
        params: { showId: performanceId, key: "a" },
      });
      this.$router.go(this.$router.currentRoute);
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
.main_title {
  font-weight: bold;
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
