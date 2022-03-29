<template>
<div>
  <base-button block type="white" class=" mb-3" @click="modals.modal1 = true">
                리뷰 작성하기
            </base-button>
            <modal :show.sync="modals.modal1">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="modals.modal1 = false">
                    &times;
                  </button>
                <br/>
                <br/>
                <div>
                  
                  <div style="width:100%; position:relative">
                    
                    <div class="showInfo left mb-3">
                      <div>
                        <h3>{{show.title}}</h3>
                        <p style = "font-size:8px;">{{show.startDate}}~{{show.endDate}}</p>
                      </div>
                      <div class="form-group picker" >
                          <div class="input-group" >
                              <label for="date" class="bold pickerlabel">관람일정</label>
                              <div class="input-group-prepend" >
                                  <span class="input-group-text"><i class="ni ni-calendar-grid-58"></i></span>
                                  
                              </div>
                              <datepicker class="form-control"
                                          input-class="smaller"
                              >
                              </datepicker>
                          </div>
                      </div>
                      <div class="form-group picker">
                            <div class="input-group">
                                <label for="time" class="bold pickerlabel">관람시간</label>
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-clock-o"></i></span>
                                </div>
                                <input type="time" name="" id="" class="form-control">
                            </div>
                        </div>
                      <label for="location" class="bold">관람장소</label><p class="inline" type="input">{{show.location}}</p><br>
                    </div>
                    
                    <div class="right mb-3">
                      <img class="showimage" :src="show.img" alt="show image"/>
                    </div>
                  </div>
                  <div class="cast">
                        <label class="bold left">캐스팅</label>
                        <label style="font-size:6px;margin-left:10px">관람한 공연의 캐스트를 선택해주세요</label><br>
                      <span  class="badge badge-pill casting badge-primary"
                               v-for="(index, key) in show.actors" :key="key" :id="index.id"
                               @click="selectactors(index.id)"
                      >
                        {{index.name}}
                      </span>
                    </div>
                  <div class="content">
                      <p style="margin:8%;" >
                          <b-textarea  v-model="show.content" id="showcontent"></b-textarea>
                          
                      </p>
                  </div>
                </div>
                <template slot="footer">
                    <base-button type="white">Save</base-button>
                    <base-button type="link" class="ml-auto" @click="modals.modal1 = false">Cancle
                    </base-button>
                </template>
            </modal>
</div>
</template>


<script>

import Modal from "@/components/Modal.vue";
import Datepicker from "vuejs-datepicker";

export default {
  components: {
    Modal,
    Datepicker,
  },
  props: {
    type: String,
    showId : Number,
    userId : Number,

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
        actors : [
            {
            id : 1,
            name :'룰루루'
            },
            {
            id : 2,
            name :'랄라라'
            },
            {
            id : 3,
            name :'아이패드'
            },
            {
            id : 4,
            name :'비싸다'
            },
            {
            id : 5,
            name :'이야호'
            },
            {
            id : 6,
            name :'마라탕'
            },
            {
            id : 7,
            name :'고양이'
            }
            ],
        content : '가을이 차고 내 마음도 차고 이대로 담아두기엔 너무 안타까워 너를 향해 가는데... 놓침... 지금 이순간이 바로 그 순간이야~ 제일 맘에 드는 옷을 입고 노란 꽃 한 송이를 손에 들고 널 바라보다 그만 나도모르게 웃어버렸네 이게 아닌데 내 맘은 이게 아닌데~~~ 술이 차고 밤공기도 차고 두 눈을 감아야만 네 모습이 보여'
      },
      review :{
          actors:[],
      },
    }
  },
  methods :{
      selectactors(id){
        // 클릭된 블록
        let cur = document.getElementById(id)
        // 해당 블록이 이미 선택되었으면(warning으로 변해 있으면)
        if(cur.className=="badge badge-pill casting badge-warning"){
          // 다시 primary로 변경하고 선택된 actors에서 뺌
          cur.className = "badge badge-pill casting badge-primary"
          for(var i = 0; i<this.review.actors.length;i++){
            if(this.review.actors[i]===id){
              this.review.actors.splice(i, 1)
              i--
            }
          }
        }
        else { // 선택 안된 블록이라면 warning으로 변경하고 목록에 저장
          cur.className = "badge badge-pill casting badge-warning"
           this.review.actors.push(id)
        }
        console.log(this.review.actors)
      }
  }
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
  float : left;
  box-sizing: border-box;
  /* margin-left: 5%; */
}
div.right {
  width: 40%;
  float : right;
  box-sizing: border-box;
}
.content {
  clear: both;
  height: 70%;
  background-color: #F8F8F8;
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
.bold, h3 {
  font-weight: bold;
}
#showcontent {
    border: 0px ;
    width: 100%;
    height: 200px;
    resize : none;
    background-color: transparent;
    outline-color: transparent;
}
.picker {
    width: 90%;
}
.pickerlabel {
    margin-top : 5%;
    margin-right: 10px;
}
.cast {
    clear :both;
}
input[type="time"]::-webkit-calendar-picker-indicator {
    background-image: url("./assets/pngwing.png");
    background-size: 12px 12px;
    border-color: transparent;
}


</style>

<style>
    .smaller {
        height : 50% !important;
        width : 100% !important;
        border: 0px !important;
        color : darkgrey
    }
</style>