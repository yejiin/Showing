<template>
  <div>
    <b-card class="profile">
      <div class="profile-image">
        <div class="image-box" style="background: #bdbdbd">
          <img class="image" :src="profile.userImage" />
        </div>
      </div>
      <div class="profile-introduce">
        <b-card-text class="profile-el"><b-icon-person-circle></b-icon-person-circle>{{ profile.nickName }}</b-card-text>
        <b-card-text class="profile-el"><b-icon-envelope></b-icon-envelope>{{ profile.email }}</b-card-text>
        <b-card-text class="profile-el"><b-icon-tag></b-icon-tag>{{ profile.introduce }}</b-card-text>
      </div>
    </b-card>

    <br />
    <br />
  </div>
</template>

<script>
export default {
  name: "Profile",
  components: {},
  props: {
    profile: Object,
  },
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.profile {
  height: 200px;
  background: #dce4fd;
}

.profile-image {
  float: left;
  height: 100%;
  width: 20%;
}

.image-box {
  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
  float: right;
  margin-right: 20px;
}
.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.profile-introduce {
  float: left;
  height: 100%;
  vertical-align: middle;
  padding-top: 25px;
}
.profile-el {
  margin: 8px;
}
</style>
