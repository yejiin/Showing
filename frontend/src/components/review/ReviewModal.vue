<template>
  <div>
    <modal :show.sync="modals.myReview">
      <!-- <div class="backArrow"> -->
      <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="setReviewModalStates(false)">
        <!-- <i class="ni ni-bold-left"></i> -->
        &times;
      </button>
      <!-- </div> -->
      <div>
        <div class="modalHeader title">
          <!-- <img :src="user.img" alt="profile image" class="profile" /> -->
          <!-- <h6 class="inline title">{{ user.nickname }}</h6> -->
        </div>
        <div style="width: 100%; position: relative">
          <div class="showInfo left mb-3">
            <div>
              <!-- <h3>{{ show.title }}</h3> -->
              <!-- <p style="font-size: 8px">{{ show.startDate }}~{{ show.endDate }}</p> -->
            </div>
            <label for="date">관람일정</label>
            <!-- <p class="inline right" type="input">{{ show.date }}</p> -->
            <br />
            <label for="time">관람시간</label>
            <!-- <p class="inline" type="input">{{ show.time }}</p> -->
            <br />
            <label for="location">관람장소</label>
            <!-- <p class="inline" type="input">{{ show.location }}</p> -->
            <br />
            <label for="castingboard left">캐스팅보드</label><br />
            <!-- <b-badge pill variant="primary" v-for="(index, key) in show.actors" :key="key">{{ index }}</b-badge> -->
          </div>
          <div class="right mb-3">
            <!-- <img class="showimage" :src="show.img" alt="show image" /> -->
          </div>
        </div>
        <div class="content">
          <!-- <p style="margin: 8%">{{ show.content }}</p> -->
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import { getDetailReview } from "@/api/review.js";
import { mapActions, mapState } from "vuex";

const reviewStore = "reviewStore";

export default {
  name: "ReviewModal",
  components: {
    Modal,
  },
  props: {
    type: String,
    reviewId: Number,
    showModal: Boolean,
  },
  computed: {
    ...mapState(reviewStore, ["modals"]),
  },
  data() {
    return {
      // modals: {
      //   modal1: false,
      //   modal2: false,
      //   modal3: false,
      // },
      user: {
        nickname: "김싸피",
        img: "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg",
      },
      show: {
        title: "지킬 앤 하이드",
        img: "http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040001/21/08/0400012108_167640_01.532.gif",
        startDate: "2022.01.01",
        endDate: "2022.05.03",
        date: "2022년 2월 2일",
        time: "17:00",
        location: "샤롯데씨어터",
        actors: ["룰루루", "랄라라", "아이패드", "비싸다", "이야호", "마라탕", "고양이"],
        content:
          "가을이 차고 내 마음도 차고 이대로 담아두기엔 너무 안타까워 너를 향해 가는데... 놓침... 지금 이순간이 바로 그 순간이야~ 제일 맘에 드는 옷을 입고 노란 꽃 한 송이를 손에 들고 널 바라보다 그만 나도모르게 웃어버렸네 이게 아닌데 내 맘은 이게 아닌데~~~ 술이 차고 밤공기도 차고 두 눈을 감아야만 네 모습이 보여",
      },
    };
  },

  destroyed() {
    // this.setReviewModalState(false);
  },
  created() {
    // getDetailReview(20, (response) => {
    //   console.log(response.data);
    //   this.show = response.data.data;
    // });
  },

  methods: {
    ...mapActions(reviewStore, ["setReviewModalState"]),
    setReviewModalStates(status) {
      // alert(this.modals.myReview);
      console.log(status);
      this.setReviewModalState(status);
    },
  },
};
</script>

<style scoped>
/* * {
  font-family: "Noto Sans" !important;
   font-size: 15px;
} */
.inline {
  display: inline;
  margin-left: 10px;
}
.profile {
  width: 5%;
  height: 5%;
  object-fit: cover;
  border-radius: 70%;
}
.title {
  margin-bottom: 4%;
}
.showimage {
  width: 95%;
  float: right;
  margin-right: 5%;
  border-radius: 5%;
}
div.left {
  width: 60%;
  float: left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
div.right {
  width: 40%;
  float: right;
  box-sizing: border-box;
}
.content {
  clear: both;
  height: 70%;
  background-color: #f8f8f8;
  border-radius: 5%;
}
.backArrow {
  clear: both;
  float: left;
  display: block;
  background-color: transparent;
  border: 0;
}
.backArrow:not(:disabled):not(.disabled) {
  cursor: pointer;
}
label,
h3 {
  font-weight: bold;
}
</style>
