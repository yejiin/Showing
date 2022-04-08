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
          <img class="logo" src="@/assets/img/showing-logo.png" alt="logo" />
        </div>

        <div style="text-align: center">
          <br />
          <br />
          <div id="naver">
            <a
              href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=w0aqGwanb1NwL6aMLQHo&redirect_uri=https://j6a301.p.ssafy.io/callback&state=1"
            >
              <img class="loginBtn" src="../../../public/img/brand/naver_login.png" />
            </a>
          </div>
          <br />
          <div id="kakao">
            <a
              href="https://kauth.kakao.com/oauth/authorize?client_id=f5d578b0e180cc11c0c265cd6fe7a65a&redirect_uri=https://j6a301.p.ssafy.io/callback&response_type=code"
            >
              <img class="loginBtn" src="../../../public/img/brand/kakao_login.png" />
            </a>
          </div>
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
  max-width: 57%;
}

.logo {
  height: 90px;
}

.login_title {
  color: #3439bb;
}
</style>
