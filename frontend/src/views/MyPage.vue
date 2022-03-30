<template>
  <div class="header">
    <profile></profile>
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

export default {
  name: "MyPage",
  components: {
    Profile,
    UserRating,
    UserFavorite,
    UserReview,
  },
};
</script>
<style scoped></style>
