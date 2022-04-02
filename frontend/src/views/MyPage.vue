<template>
  <div class="header">
    <profile :profile="profile"></profile>
    <user-rating :rating-info="ratingInfo"></user-rating>
    <user-favorite :favorite-actor="favoriteActorList"></user-favorite>
    <user-review :review-list="reviewList"></user-review>
  </div>
</template>
<script>
import Profile from "@/components/user/Profile";
import UserRating from "@/components/user/UserRating";
import UserFavorite from "@/components/user/UserFavorite";
import UserReview from "@/components/user/UserReview";

import { getUserInfo } from "@/api/user.js";

export default {
  name: "MyPage",
  components: {
    Profile,
    UserRating,
    UserFavorite,
    UserReview,
  },
  data() {
    return {
      profile: {
        userId: 0,
        nickName: "",
        email: "",
        introduce: "",
        userImage: "",
      },
      ratingInfo: {
        musicalCnt: 0,
        playCnt: 0,
        ratingAvg: 0.0,
        ratingCnt: 0,
        ratingMax: 0.0,
        ratingCntList: [],
      },
      favoriteActorList: [],
      reviewList: [],
    };
  },
  async created() {
    await getUserInfo(
      this.$route.params.userId,
      (response) => {
        this.profile.userId = response.data.data.userId;
        this.profile.nickName = response.data.data.nickName;
        this.profile.email = response.data.data.email;
        this.profile.introduce = response.data.data.introduce;
        this.profile.userImage = response.data.data.userImage;

        this.ratingInfo.musicalCnt = response.data.data.muscialCnt;
        this.ratingInfo.playCnt = response.data.data.playCnt;

        this.ratingInfo.ratingAvg = response.data.data.ratingAvg.toFixed(1);
        this.ratingInfo.ratingCnt = this.ratingInfo.musicalCnt + this.ratingInfo.playCnt;

        this.ratingInfo.ratingCntList = response.data.data.ratingCntList;

        let cntLsit = this.ratingInfo.ratingCntList;

        let max = Math.max.apply(null, cntLsit);
        let index = cntLsit.indexOf(max);

        if (max === 0) index = -1;

        this.ratingInfo.ratingMax = ((index + 1) / 2).toFixed(1);

        this.favoriteActorList = response.data.data.favoriteActorList;

        this.reviewList = response.data.data.reviewList;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>
<style scoped></style>
