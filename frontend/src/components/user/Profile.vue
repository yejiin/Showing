<template>
  <div>
    <b-card class="profile">
      <div class="profile-image">
        <div class="image-box" style="background: #bdbdbd">
          <img class="image" src="/images/tistory.JPG" />
        </div>
      </div>
      <div class="profile-introduce">
        <b-card-text class="profile-el"><b-icon-person-circle></b-icon-person-circle> 홍길동</b-card-text>
        <b-card-text class="profile-el"><b-icon-envelope></b-icon-envelope> hong@naver.com</b-card-text>
        <b-card-text class="profile-el"><b-icon-tag></b-icon-tag> 안녕하세요 뮤지컬을 사랑하는 홍길동입니다.</b-card-text>
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
