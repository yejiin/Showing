<template>
  <div>
    <modal :show.sync="modals.myReviewList" modal-classes="modal-dialog" id="modal">
      <button
        type="button"
        class="close"
        data-dismiss="modal"
        aria-label="Close"
        @click="setMyReviewListModalState(false)"
      >
        &times;
      </button>

      <br />
      <div>
        <h3 class="inline">{{ seasonShowName }}</h3>
        <select name="season" id="season" @change="getSeasonReview($event)">
          <option v-for="i in seasons" :key="i" :value="i.seasonId" :selected="i.seasonId === selectedseason">
            {{ i.startDate }}~{{ i.endDate }}
          </option>
        </select>
      </div>
      <br />
      <div v-for="review in seasonShow" :key="review.reviewId">
        <div id="reviewHeader" class="review">
          <div class="inreview">
            <div class="left">
              <h5 class="bold mb-1">{{ review.viewDate }}</h5>
            </div>
            <div>
              <a href="" class="udpatedelete">
                <i class="fa fa-pencil"></i>
                수정
              </a>

              <a href="" class="udpatedelete">
                <i class="fa fa-trash"></i>
                삭제 | &nbsp;
              </a>
            </div>
            <div class="title" style="clear: both">
              <img :src="review.userImage" alt="profile image" class="profile inline" />
              <p class="username inline">{{ review.userName }}</p>
            </div>
            <div class="mb-2">
              <label for="casing" class="bold rightmargin inline"
                ><h6 class="bold rightmargin inline">캐스팅</h6></label
              >
              <b-badge pill variant="primary" v-for="(index, key) in review.castingActorNameList" :key="key">{{
                index
              }}</b-badge>
            </div>
            <div class="">
              <p class="content">{{ review.content }}</p>
            </div>
          </div>
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import { getAllSeasonReview } from "@/api/review.js";
import { detailSeasonShow } from "@/api/show.js";
import { mapState, mapActions } from "vuex";

const reviewStore = "reviewStore";

export default {
  components: {
    Modal,
  },
  props: {
    type: String,
    showModal: Boolean,
    seasonShowName: String,
    seasonShow: Object,
  },
  data() {
    return {
      show: {
        id: "",
        title: "지킬 앤 하이드",
        startDate: "2022.01.01",
        endDate: "2022.05.03",
      },
      seasons: [
        {
          id: 1,
          name: "2024.01.01~2024.03.04",
        },
        {
          id: 2,
          name: "2023.01.04~2023.03.15",
        },
        {
          id: 1,
          name: "2022.01.01~2022.03.04",
        },
      ],
      reviews: [],
      selectedseason: 1,
    };
  },
  computed: {
    ...mapState(reviewStore, ["modals"]),
  },
  methods: {
    ...mapActions(reviewStore, ["setMyReviewListModalState"]),
  },
  created() {
    getAllSeasonReview(
      seasonShow.seasonId,
      (response) => {
        console.log(response.data);
        this.reviews = response.data.data;
        this.show.title = response.data.data[0].performanceName;
        this.show.id = response.data.data[0].performanceId;
        detailSeasonShow(
          response.data.data[0].performanceId,
          (response) => {
            this.seasons = response.data.data.reverse();
            console.log(response.data);
          },
          (fail) => {
            console.log(fail);
          }
        );
        console.log(this.seasons);
      },
      (fail) => {
        console.log(fail);
      }
    );
    console.log("이거슨 저장된 리뷰");
    console.log(this.reviews);
  },
};
</script>

<style scoped>
* {
  font-family: "Noto Sans KR", sans-serif;
}
.inline {
  display: inline;
  margin-right: 10px;
}
select#season {
  border: 0px;
}
.left {
  width: 70%;
  float: left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
.right {
  width: 30%;
  float: right;
  box-sizing: border-box;
}
.leftn {
  width: 85%;
  float: left;
  /* box-sizing: border-box; */
}
.rightn {
  width: 15%;
  float: right;
  /* box-sizing: border-box; */
  /* font-size: 14px; */
}
.profile {
  clear: both;
  width: 4%;
  height: 4%;
  object-fit: cover;
  border-radius: 70%;
}
.showimage {
  width: 95%;
  float: right;
  margin-right: 5%;
  border-radius: 5%;
}
.udpatedelete {
  float: right;
  color: #626262;
  font-size: 10px;
}
.title {
  margin-bottom: 1%;
}
.review {
  border-radius: 8px;
  border: #c4c4c4 solid 0.5px;
  margin: 1%;
}
.bold {
  font-weight: bold;
}
.rightmargin {
  margin-right: 3%;
}
.content {
  clear: both;
  height: 70%;
  background-color: #f8f8f8;
  border-radius: 5%;
  margin-bottom: 3%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.username {
  font-size: 12px;
}
.inreview {
  margin-top: 2%;
  margin-left: 4%;
  margin-right: 4%;
  margin-bottom: 1%;
}
.modalbutton {
  width: 20%;
}
</style>
