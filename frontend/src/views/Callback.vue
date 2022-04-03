<template>
  <div class="container">
    <div class="loading">
      <span></span>
      <span></span>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      state: {
        kakaoCode: "",
        naverCode: "",
        naverState: "",
      },
    };
  },

  computed: {
    ...mapState(userStore, ["kakaoToken", "naverToken", "isLogin"]),
  },

  mounted() {
    if (this.$route.query.state) {
      this.state.naverCode = this.$route.query.code;
      this.state.naverState = this.$route.query.state;
      this.naverLogin(this.state.naverCode, this.state.naverState);
    } else {
      this.state.kakaoCode = this.$route.query.code;
      this.kakaoLogin(this.state.kakaoCode);
    }
  },

  methods: {
    ...mapActions(userStore, ["findNaverToken", "findNaverUser", "findKakaoToken", "findKakaoUser"]),

    /* 네이버 로그인  */
    async naverLogin(code, state) {
      try {
        let token = await this.findNaverToken(code, state);
        await this.findNaverUser(token);

        await this.$router.go(-1);
      } catch (error) {
        console.log(error);
      }
    },

    /* 카카오 로그인 */
    async kakaoLogin(code) {
      try {
        let token = await this.findKakaoToken(code);
        await this.findKakaoUser(token);

        await this.$router.go(-1);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
<style scoped>
.container {
  line-height: 1.5em;
  margin: 0;
  font-weight: 300;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

a {
  text-decoration: none;
}

.loading {
  /* border : 1px solid red; */
  width: 30px;
  height: 30px;
  position: relative;
}
.loading span {
  position: absolute;
  width: 10px;
  height: 10px;
  background-color: gray;
  top: 0;
  left: 0;
  animation: loading 1.5s infinite;
}
.loading span:nth-child(1) {
  background-color: crimson;
}
.loading span:nth-child(2) {
  animation-delay: 0.8s;
}

@keyframes loading {
  0% {
    top: 0;
    left: 0;
  }
  25% {
    top: 0;
    left: calc(100% - 10px);
    background-color: dodgerblue;
  }
  50% {
    top: calc(100% - 10px);
    left: calc(100% - 10px);
    background-color: orange;
  }
  75% {
    top: calc(100% - 10px);
    left: 0;
    background-color: yellowgreen;
  }
  100% {
    top: 0;
    left: 0;
  }
}
</style>
