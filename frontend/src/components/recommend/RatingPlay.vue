<template>
  <div>
    <b-container fluid>
      <b-row>
        <b-col cols="12">
          <carousel :perPage="6">
            <slide class="p-1" v-for="(item, index) in playList" :key="index">
              <b-card
                class="mr-0 mb-2"
                :img-src="playList[index].lastSeasonImage"
                img-alt="Image"
                img-top
                tag="article"
                @click="detailShow(index)"
                style="cursor: pointer"
              >
                <!-- card content -->
                <div class="name">
                  <strong>{{ playList[index].performanceName }}</strong>
                </div>
                <div class="tags">
                  <b-badge class="mr-1" pill variant="light"
                    ><b-icon icon="star-fill" scale="0.8"></b-icon>
                    {{ (playList[index].starPointAverage / 2).toFixed(2) }}</b-badge
                  >&nbsp;
                  <b-badge v-if="playList[index].lastSeasonProceedFlag == 0" pill variant="danger">공연완료</b-badge>
                  <b-badge v-if="playList[index].lastSeasonProceedFlag == 1" pill variant="primary">공연중</b-badge>
                  <b-badge v-if="playList[index].lastSeasonProceedFlag == 2" pill variant="warning">예정</b-badge>
                </div>
                {{ playList[index].lastSeasonStartDate }} ~ {{ playList[index].lastSeasonEndDate }}
              </b-card>
            </slide>
          </carousel>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "RatingPlay",
  props: {
    playList: Array,
  },
  components: {
    Carousel,
    Slide,
  },

  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },

  computed: {},

  methods: {
    detailShow(index) {
      this.$router.push({
        name: "ShowDetail",
        params: { showId: this.playList[index].performanceId },
      });
    },
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
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

.name {
  display: inline-block;
  width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.tags {
  margin-bottom: 5px;
}
</style>
