<template>
  <div class="row">
    <div class="row">
      <b-img :src="heading.performanceImage" alt="profile image" class="profile shadow" rounded />
    </div>
    <div class="col show_header">
      <div class="col proceedflag">
        <div v-if="heading.proceedFlag == 0"><b-badge pill variant="danger">공연완료</b-badge></div>
        <div v-if="heading.proceedFlag == 1"><b-badge pill variant="primary">공연중</b-badge></div>
        <div v-if="heading.proceedFlag == 2"><b-badge pill variant="warning">예정</b-badge></div>
      </div>
      <div class="col main_title float-left align-middle mb-2 mt-1">{{ heading.performanceName }}</div>
      <div class="col mx-1 my-3">
        <h6>
          평균 <b-icon icon="star-fill" scale="0.8"></b-icon>{{ (heading.starPointAverage / 2).toFixed(2) }} ({{
            heading.ratingCount
          }}명)
        </h6>
      </div>
      <hr />
      <div class="col" @click="showToast">
        <star-rating
          class="star"
          id="starRating"
          :increment="0.5"
          :star-size="45"
          :show-rating="false"
          v-model="ratingCount"
          :read-only="starAccess"
          clearable
        ></star-rating>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from "vuex";
import StarRating from "vue-star-rating";
import { addRating, deleteRating, modifyRating } from "@/api/rating.js";

const userStore = "userStore";
const ratingStore = "ratingStore";

export default {
  name: "ShowHeader",
  components: {
    StarRating,
  },
  props: {
    heading: Object,
  },
  created() {
    this.ratingCount = this.rating;
    this.storeValue = this.rating;
    this.starAccess = !this.isLogin;
  },
  data() {
    return {
      ratingCount: 0,
      storeValue: 0,
      starAccess: true,
    };
  },
  methods: {
    ...mapActions(ratingStore, ["setMyStarIdState", "setMyRatingState"]),
    showToast() {
      if (!this.isLogin) {
        this.$toast("로그인 후 평가 가능합니다.", {
          type: "error",
        });
      }
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapGetters({
      isLogin: "userStore/isLogin",
      userInfo: "userStore/userInfo",
      rating: "ratingStore/rating",
      starId: "ratingStore/starId",
    }),
  },
  watch: {
    ratingCount: function () {
      if (this.isLogin) {
        if (this.storeValue == 0) {
          // add
          var req = {
            performanceId: this.heading.performanceId,
            rating: this.ratingCount * 2,
            userId: this.userInfo.userId,
          };
          addRating(
            req,
            (response) => {
              this.setMyStarIdState(response.data.data);
              this.setMyRatingState(this.ratingCount);
            },
            (error) => {
              console.log(error);
            }
          );
        } else {
          if (this.ratingCount == 0) {
            // delete
            deleteRating(
              this.starId,
              (response) => {
                this.setMyStarIdState(0);
                this.setMyRatingState(0);
              },
              (error) => {
                console.log(error);
              }
            );
          } else {
            // modify
            var req = {
              starId: this.starId,
              rating: this.ratingCount * 2,
              userId: this.userInfo.userId,
            };
            modifyRating(
              req,
              (response) => {
                this.setMyRatingState(this.ratingCount);
              },
              (error) => {
                console.log(error);
              }
            );
          }
        }
        this.storeValue = this.ratingCount;
      }
    },
  },
};
</script>

<style scoped>
.profile {
  margin-left: 40px;
  margin-right: 5px;
}
.star {
  cursor: pointer;
  vertical-align: middle;
}
.main_title {
  font-size: 35px;
}
.proceedflag {
  margin-top: 3%;
}
.show_header {
  margin-left: 35px;
  border-top: 1px solid;
  border-bottom: 1px solid;
  border-color: rgb(229, 229, 229);
}
</style>
