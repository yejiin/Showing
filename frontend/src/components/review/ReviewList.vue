<template>
<div>
  <base-button block type="white" class=" mb-3" @click="modals.modal1 = true">
                리뷰 목록
            </base-button>
            <modal :show.sync="modals.modal1"
                    modal-classes="modal-dialog modal-lg">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                &times;
                </button>
                <div>
                    <h3 class="inline">{{show.title}}</h3>
                    <select name="season" id="season">
                        <option v-for="i in seasons" :key="i" :value="i.id">{{i.name}}</option>
                    </select>
                </div>
                <br>
                <div v-for="review in reviews" :key="review.id">
                    <div id="reviewHeader" class="review">
                        <div style="margin:4%">
                            <div class="left"><h5>{{review.date}}</h5></div>
                            <div>
                                <a href="">
                                    <i class="fa fa-pencil"></i>
                                    수정
                                </a>
                                
                                <a href="">
                                    <i class="fa fa-trash"></i>
                                    삭제 | &nbsp;
                                </a>
                            </div>
                            <div class="title" style="clear:both">
                                <img :src="review.author.img" alt="profile image" class="profile inline"/>
                                <p class="username inline">{{review.author.nickname}}</p>
                            </div>
                            <div>
                                <label for="casing" class="bold rightmargin">캐스팅</label>
                                <b-badge pill variant="primary" v-for="(index, key) in review.casting" :key="key">{{index}}</b-badge>
                            </div>
                            <div class="content">
                                {{review.content}}
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
    Modal
  },
  props: {
    type: String,
  },
  data() {
    return {
      modals: {
        modal1: false,
        modal2: false,
        modal3: false
      },
      user:{
        nickname : '김싸피',
        img : 'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg'
      },
      show : {
        title : '지킬 앤 하이드',
        img : 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040001/21/08/0400012108_167640_01.532.gif',
        startDate : "2022.01.01",
        endDate : "2022.05.03",
        date : '2022년 2월 2일',
        time : '17:00',
        location : '샤롯데씨어터',
        actors : ['룰루루','랄라라','아이패드','비싸다','이야호','마라탕','고양이'],
        content : '가을이 차고 내 마음도 차고 이대로 담아두기엔 너무 안타까워 너를 향해 가는데... 놓침... 지금 이순간이 바로 그 순간이야~ 제일 맘에 드는 옷을 입고 노란 꽃 한 송이를 손에 들고 널 바라보다 그만 나도모르게 웃어버렸네 이게 아닌데 내 맘은 이게 아닌데~~~ 술이 차고 밤공기도 차고 두 눈을 감아야만 네 모습이 보여'
      },
      seasons:[
          {
              id : 1,
              name : "2024.01.01~2024.03.04"
          },
          {
              id : 2,
              name : "2023.01.04~2023.03.15"
          },
          {
              id : 1,
              name : "2022.01.01~2022.03.04"
          }
      ],
      reviews:[
          {
              id : 1,
              date: "2022.03.04",
              author: {
                  nickname : '지앤하',
                  img : "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg"
              },
              casting : ['홍길동', '룰루루'],
              content : "거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 "

          },
          {
              id : 2,
              date: "2022.03.04",
              author: {
                  nickname : '지앤하',
                  img : "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg"
              },
              casting : ['홍길동', '룰루루'],
              content : "거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 "

          },
          {
              id : 3,
              date: "2022.03.04",
              author: {
                  nickname : '지앤하',
                  img : "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg"
              },
              casting : ['홍길동', '룰루루'],
              content : "거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 마싯다 거봉 "

          }
      ],
    };
  }
};
</script>

<style scoped>
.inline {
  display: inline;
  margin-right: 10px;
}
select#season {
    border: 0px;
}
.left {
  width: 70%;
  float : left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
.right {
  width: 30%;
  float : right;
  box-sizing: border-box;
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
a {
    float: right;
    color: #626262;
    font-size: 10px;
}
.title {
  margin-bottom: 1%;
}
.review {
    border-radius: 4%;
    border: #C4C4C4 solid 0.5px;
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
  background-color: #F8F8F8;
  border-radius: 5%;
  margin-bottom: 5%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.username {
    font-size: 12px;
}
</style>
