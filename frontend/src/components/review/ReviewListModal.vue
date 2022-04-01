<template>
  <div>
    <base-button size="sm" type="primary" class="float-right" @click="modals.modal1 = true"> 더보기 </base-button>
    <modal :show.sync="modals.modal1" modal-classes="modal-dialog modal-lg">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">&times;</button>

      <br />
      <div>
        <h3 class="inline">{{ show.title }}</h3>
        <select name="season" id="season">
          <option v-for="i in seasons" :key="i" :value="i.id">{{ i.name }}</option>
        </select>
      </div>
      <br />
      <div v-for="review in reviews" :key="review.id">
        <div id="reviewHeader" class="review">
          <div class="inreview">
            <div class="left">
              <h5 class="bold mb-1">{{ review.date }}</h5>
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
              <img :src="review.author.img" alt="profile image" class="profile inline" />
              <p class="username inline">{{ review.author.nickname }}</p>
            </div>
            <div class="mb-2">
              <label for="casing" class="bold rightmargin inline"
                ><h6 class="bold rightmargin inline">캐스팅</h6></label
              >
              <b-badge pill variant="primary" v-for="(index, key) in review.casting" :key="key">{{ index }}</b-badge>
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
export default {
  components: {
    Modal,
  },
  props: {
    type: String,
  },
  data() {
    return {
      modals: {
        modal1: false,
      },
      show: {
        title: "지킬 앤 하이드",
        img: "http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040001/21/08/0400012108_167640_01.532.gif",
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
      reviews: [
        {
          id: 1,
          date: "2022.03.04",
          author: {
            nickname: "지앤하",
            img: "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg",
          },
          casting: ["홍길동", "룰루루"],
          content: "거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 ",
        },
        {
          id: 2,
          date: "2022.03.04",
          author: {
            nickname: "지앤하",
            img: "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg",
          },
          casting: ["홍길동", "룰루루"],
          content: "거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 ",
        },
        {
          id: 3,
          date: "2022.03.04",
          author: {
            nickname: "지앤하",
            img: "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg",
          },
          casting: ["홍길동", "룰루루"],
          content: "거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 ",
        },
      ],
    };
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
</style>
