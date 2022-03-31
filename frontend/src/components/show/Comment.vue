<template>
  <div>
    <h5 class="main_title comment_title">다른 사람의 리뷰</h5>
    <review-list-modal v-if="previewReview != 0"></review-list-modal>
    <br />
    <div v-if="previewReview == 0">
      <b-card-text>등록된 리뷰가 없습니다.</b-card-text>
      <br /><br />
    </div>
    <div v-else>
      <!-- carousel area -->
      <b-card-group deck class="mb-0 comment_list">
        <!-- 화살표 아이콘을 통해 슬라이딩 할 경우 -->
        <i class="ni ni-bold-left arrow"></i>
        <b-card
          v-for="(item, index) in currentPageCards"
          :key="index"
          class="mr-0 mb-2"
          tag="article"
          style="max-width: 20rem"
        >
          <i style="font-size: 40px" class="ni ni-circle-08"></i>&nbsp;
          <b-card-title class="comment_writer">{{ previewReview[index].userName }}</b-card-title>
          <hr />
          <b-card-text>{{ previewReview[index].content }}</b-card-text>
        </b-card>
        <i class="ni ni-bold-right arrow arrow_right"></i>
      </b-card-group>
      <br />
      <!-- pagination area -->
      <!-- 페이징을 사용해서 슬라이딩 할 경우 (아래 js 참고 코드 있음) -->
      <div class="pagination" v-if="cards.length > cardsPerPage">
        <div class="index" v-for="i in pageCount" :key="i" @click="next(i)" :class="{ active: currentPage(i) }"></div>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
import ReviewListModal from "@/components/review/ReviewListModal.vue";
import { mapGetters, mapState, mapActions } from "vuex";

const reviewStore = "reviewStore";

export default {
  name: "Comment",
  components: {
    ReviewListModal,
  },
  props: {
    previewReview: Array,
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
      cardsPerPage: 4,
      currentPageIndex: 0,
      review: [],
    };
  },
  computed: {
    currentPageCards() {
      this.createPages();

      return this.paginatedCards[this.currentPageIndex];
    },
  },

  methods: {
    ...mapActions(reviewStore, ["setMyReviewListModalState", "setWriteReviewModalState"]),
    setMyReviewListModalStates(status) {
      this.setMyReviewListModalState(status);
      console.log("리뷰 목록 보여줘요" + status);
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

.arrow {
  margin-top: 10%;
  color: #9badf6;
}

.arrow_right {
  margin-left: 1%;
}

.more {
  color: #3f46d8;
  float: right;
}

.comment_title {
  float: left;
}

.comment_list {
  clear: both;
}

.comment_writer {
  display: inline-block;
  margin-top: 0px;
  margin-bottom: 0px;
  font-size: 120%;
}

.card-text {
  color: black;
}
</style>
