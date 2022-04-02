<template>
  <div>
    <span class="main_title float-left">다른 사람의 리뷰</span>
    <review-list-modal v-if="previewReview != 0"></review-list-modal>
    <br />
    <div class="my-2" v-if="previewReview == 0">
      <b-card-text>등록된 리뷰가 없습니다.</b-card-text>
      <br /><br />
    </div>
    <div class="my-5 mx-auto" v-else>
      <!-- carousel area -->
      <carousel :perPage="5" class="list">
        <slide class="p-1 mt-3" v-for="(item, index) in previewReview" :key="index">
          <div>
            <b-card rounded class="card">
              <div @click="detailUser(previewReview[index].userId)" style="cursor: pointer">
                <b-container>
                  <b-row>
                    <b-col>
                      <b-img :src="previewReview[index].userImage" rounded="circle" height="40px" />
                    </b-col>
                    <b-col>
                      <span class="comment_writer float-right" style="vertical-align: middle">{{
                        previewReview[index].userName
                      }}</span>
                    </b-col>
                  </b-row>
                </b-container>
              </div>
              <hr class="hr" />
              <b-card-text class="card-text">{{ previewReview[index].content.substring(0, 20) }} ...</b-card-text>
            </b-card>
          </div>
        </slide>
      </carousel>
    </div>
    <br />
  </div>
</template>

<script>
import ReviewListModal from "@/components/review/ReviewListModal.vue";
import { Carousel, Slide } from "vue-carousel";
import { mapGetters, mapState, mapActions } from "vuex";

const reviewStore = "reviewStore";

export default {
  name: "Comment",
  components: {
    ReviewListModal,
    Carousel,
    Slide,
  },
  props: {
    previewReview: Array,
  },
  data() {
    return {
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
    },

    detailUser(id) {
      this.$router.push({
        name: "MyPage",
        params: { userId: id },
      });
      console.log(id);
    },
  },
};
</script>

<style scoped>
.main_title {
  font-weight: 600;
  font-size: 23px;
}

.list {
  width: 100%;
}

.card {
  padding: 0px;
  height: 200px;
}

.hr {
  margin-top: 10px;
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
  margin-top: 0px;
  margin-bottom: 0px;
  font-size: 110%;
}

.card-text {
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>
