<template>
  <div>
    <div>
      <base-button block type="white" @click="modals.modal1 = true"> 로그인 </base-button>
      <modal :show.sync="modals.modal1" modal-classes="modal-sm">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="modals.modal1 = false">
          &times;
        </button>
        <br /><br />
        <div style="text-align: center">
          <h2 class="main_title login_title">SHOWING</h2>
          <br />
          <h4 class="main_title">로그인</h4>
          <br /><br />
          <img class="loginBtn" src="../../../public/img/brand/naver_login.png" />
          <br /><br />
          <img class="loginBtn" src="../../../public/img/brand/kakao_login.png" />
        </div>
        <br /><br />
      </modal>
    </div>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";

export default {
  name: "Login",
  components: {
    Modal,
  },
  data() {
    return {
      modals: {
        modal1: false,
      },
    };
  },
};
</script>

<style scoped>
.loginBtn {
  max-width: 60%;
}

.login_title {
  color: #3439bb;
}
</style>
