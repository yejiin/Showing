<template>
  <div>
    <b-card class="profile" v-if="this.$store.state.userStore.userInfo.userId == this.profile.userId">
      <div v-if="!edit">
        <div class="profile-image">
          <div class="image-box" style="background: #bdbdbd">
            <img class="image" :src="profile.userImage" />
          </div>
        </div>
        <div class="profile-introduce">
          <b-card-text class="profile-el"
            ><b-icon-person-circle class="mr-3"></b-icon-person-circle> {{ profile.nickName }}</b-card-text
          >
          <b-card-text class="profile-el"
            ><b-icon-envelope class="mr-3"></b-icon-envelope>{{ profile.email }}</b-card-text
          >

          <b-card-text class="profile-el"><b-icon-tag class="mr-3"></b-icon-tag>{{ profile.introduce }}</b-card-text>
        </div>
        <div class="profile-edit" @click="editProfile(true)">edit</div>
      </div>
      <div v-else>
        <div class="profile-image">
          <div class="image-box" style="background: #bdbdbd">
            <img class="image" :src="profile.userImage" />
          </div>
        </div>
        <div class="profile-introduce">
          <b-card-text class="profile-el">
            <div class="introduce-tag"><b-icon-person-circle class="mr-3"></b-icon-person-circle></div>
            <div class="introduce-content"><base-input v-model="profile.nickName"></base-input></div>
          </b-card-text>
          <b-card-text class="profile-el">
            <div class="introduce-tag"><b-icon-envelope class="mr-3"></b-icon-envelope></div>
            <div class="introduce-content"><base-input v-model="profile.email" disabled></base-input></div>
          </b-card-text>
          <b-card-text class="profile-el">
            <div class="introduce-tag"><b-icon-tag class="mr-3"></b-icon-tag></div>
            <div class="introduce-content">
              <textarea class="form-control" v-model="profile.introduce" rows="3"></textarea>
            </div>
          </b-card-text>
        </div>
        <div class="profile-edit" @click="editProfile(false)">save</div>
      </div>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
import { modifyUser } from "@/api/user.js";

export default {
  name: "Profile",
  components: {},
  props: {
    profile: Object,
  },
  data() {
    return {
      edit: false,
    };
  },
  methods: {
    editProfile(status) {
      this.edit = status;

      if (!status) {
        modifyUser(
          this.profile,
          (response) => {
            console.log(response);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.profile {
  height: 260px;
  background: #dce4fd;
}

.profile-image {
  float: left;
  height: 100%;
  width: 20%;
}

.image-box {
  width: 200px;
  height: 200px;
  border-radius: 70%;
  overflow: hidden;
  float: right;
  margin-right: 20px;
  margin-top: 8px;
}
.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.profile-introduce {
  float: left;
  height: 100%;
  width: 70%;
  vertical-align: middle;
  padding-top: 25px;
  margin-left: 20px;
}
.profile-el {
  margin: 10px;
  font-size: 25px;
}
.profile-edit {
  cursor: pointer;
  float: right;
  font-weight: bold;
  margin: 7px;
}
.introduce {
  width: 100%;
}
.introduce-tag {
  float: left;
}
.introduce-content {
  padding-left: 45px;
  width: 600px;
}
</style>
