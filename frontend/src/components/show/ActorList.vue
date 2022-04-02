<template>
  <div class="mainbox mt-4">
    <div v-if="actor != null">
      <!-- carousel area -->
      <carousel :perPage="4">
        <slide class="p-1 mt-3" v-for="(item, index) in actor" :key="index">
          <div class="rounded">
            <!-- card content -->
            <div>
              <b-img class="image-box" :src="actor[index].actorImage" center />
            </div>
            <h6 class="actor_name" v-if="actor[index].role != undefined || actor[index].role != null">
              {{ actor[index].role }}
            </h6>
            <h5 class="actor_name">{{ actor[index].actorName }}</h5>
          </div>
        </slide>
      </carousel>
      <!-- pagination area -->
      <br />
    </div>
  </div>
</template>

<script>
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "ActorList",
  props: {
    actor: Array,
  },
  components: {
    Carousel,
    Slide,
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
      cardsPerPage: 5,
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
      let cardsLength = this.actor.length;
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
        this.paginatedCards[0] = this.actor;
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

.profile-image {
  height: 50%;
  width: 100%;
}

.image-box {
  width: 120px;
  height: 120px;
  border-radius: 70%;
  overflow: hidden;
  object-fit: cover;
  background-color: wheat;
}

.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
