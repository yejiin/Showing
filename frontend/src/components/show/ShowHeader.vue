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
          평균 <b-icon icon="star-fill" scale="0.8"></b-icon>{{ heading.starPointAverage / 2 }} ({{
            heading.ratingCount
          }}명)
        </h6>
      </div>
      <hr />
      <star-rating
        class="star"
        id="starRating"
        :increment="0.5"
        :star-size="45"
        :show-rating="false"
        v-model="this.heading.rating"
        :read-only="false"
        clearable
      ></star-rating>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import StarRating from "vue-star-rating";
import { addRating, deleteRating, modifyRating } from "@/api/rating.js";

const userStore = "userStore";

export default {
  name: "ShowHeader",
  components: {
    StarRating,
  },
  props: {
    heading: Object,
  },
  data() {
    return {
      // starId: 0,
      storeValue: this.heading.rating,
      // ratingCount: 0,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapGetters({
      isLogin: "userStore/isLogin",
      userInfo: "userStore/userInfo",
    }),
  },
  watch: {
    ratingCount: function () {
      if (this.storeValue == 0) {
        // add
        var req = {
          performanceId: this.heading.performanceId,
          rating: this.heading.rating * 2,
          userId: this.userInfo.userId,
        };
        addRating(
          req,
          (response) => {
            this.heading.starId = response.data.data;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        if (this.ratingCount == 0) {
          // delete
          deleteRating(
            this.heading.starId,
            (response) => {
              console.log(response);
            },
            (error) => {
              console.log(error);
            }
          );
        } else {
          // modify
          var req = {
            starId: this.heading.starId,
            rating: this.heading.rating * 2,
            userId: this.userInfo.userId,
          };
          modifyRating(
            req,
            (response) => {
              console.log(response);
            },
            (error) => {
              console.log(error);
            }
          );
        }
      }
      this.storeValue = this.heading.rating;
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
  vertical-align: middle;
}
.main_title {
  font-size: 35px;
  color: black;
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
