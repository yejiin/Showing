<template>
  <div class="header">
    <profile :profile="profile"></profile>
    <user-rating></user-rating>
    <user-favorite></user-favorite>
    <user-review></user-review>
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
        nickName: "",
        email: "",
        introduce: "",
        userImage: "",
      },
    };
  },
  created() {
    getUserInfo(
      this.$route.params.userId,
      (response) => {
        this.profile.nickName = response.data.data.nickName;
        this.profile.email = response.data.data.email;
        this.profile.introduce = response.data.data.introduce;
        this.profile.userImage = response.data.data.userImage;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>
<style scoped></style>
