<template>
  <div>
    <div v-if="clickOtherSeason == false">
      <b-card>
        <div class="dropdown">
          <button
            class="btn p-0 btn-white dropdown-toggle"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            공연정보
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a @click="otherSeason(index)" v-for="(seasonDate, index) in seasons" :key="index" class="dropdown-item">
              {{ seasonDate.startDate }} ~ {{ seasonDate.endDate }}
            </a>
          </div>
        </div>
        <br />
        <br />

        <span class="badge badge-pill badge-success">{{ info.detailType }}</span
        >&nbsp;
        <span class="badge badge-pill badge-warning">{{ info.performanceAge }} 관람가</span>
        <br /><br />
        <b-row
          ><b-col
            ><b-card-text>일정 {{ info.startDate }} ~ {{ info.endDate }}</b-card-text></b-col
          ></b-row
        >
        <b-row
          ><b-col cols="6"
            ><b-card-text class="location">장소 {{ info.location }}</b-card-text></b-col
          >
          <b-col cols="6">
            <b-card-text>공연시간 {{ info.runingTime }}</b-card-text></b-col
          ></b-row
        >
        <actor-list :actor="actor"></actor-list>
      </b-card>
      <br />
      <br />
      <story :description="description"></story>
    </div>

    <div v-if="clickOtherSeason == true">
      <b-card>
        <div class="dropdown">
          <button
            class="btn p-0 btn-white dropdown-toggle"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            공연정보
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a @click="otherSeason(index)" v-for="(seasonDate, index) in seasons" :key="index" class="dropdown-item">
              {{ seasonDate.startDate }} ~ {{ seasonDate.endDate }}
            </a>
          </div>
        </div>
        <br />
        <br />

        <span class="badge badge-pill badge-success">{{ otherSeasonInfo.detailType }}</span
        >&nbsp;
        <span class="badge badge-pill badge-warning">{{ otherSeasonInfo.performanceAge }} 관람가</span>
        <br /><br />
        <b-row
          ><b-col
            ><b-card-text>일정 {{ otherSeasonInfo.startDate }} ~ {{ otherSeasonInfo.endDate }}</b-card-text></b-col
          ></b-row
        >
        <b-row
          ><b-col cols="6"
            ><b-card-text class="location">장소 {{ otherSeasonInfo.location }}</b-card-text></b-col
          >
          <b-col cols="6">
            <b-card-text>공연시간 {{ otherSeasonInfo.runingTime }}</b-card-text></b-col
          ></b-row
        >
        <actor-list :otherSeasonActor="otherSeasonActor"></actor-list>
      </b-card>
      <br />
      <br />
      <story :otherSeasonDescription="otherSeasonDescription"></story>
    </div>
  </div>
</template>

<script>
import ActorList from "@/components/show/ActorList";
import Story from "@/components/show/Story";

import { detailSeasonShow } from "@/api/show.js";

export default {
  name: "ShowInfo",
  components: {
    ActorList,
    Story,
  },
  props: {
    info: Object,
    actor: Array,
    description: String,
    seasons: Array,
  },
  data() {
    return {
      showInfo: {},
      clickOtherSeason: false,
      otherSeasonInfo: {},
      otherSeasonDescription: "",
      otherSeasonActor: [],
    };
  },
  methods: {
    // 한 시즌 클릭
    otherSeason(index) {
      this.clickOtherSeason = true;

      detailSeasonShow(
        this.seasons[index].seasonId,
        (response) => {
          this.otherSeasonInfo = response.data.data;
          this.otherSeasonDescription = response.data.data.description;
          this.otherSeasonActor = response.data.data.actorList;
          // console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.more {
  color: #3f46d8;
  float: right;
}

.btn-white {
  color: #212529;
  background-color: #fff;
  border-color: #fff;
  -webkit-box-shadow: 0 0px 0px, 0 0px 0px;
  box-shadow: 0 0px 0px, 0 0px 0px;
  font-size: 20px;
}

.btn-white:hover {
  -webkit-box-shadow: 0 0px 0px, 0 0px 0px;
  box-shadow: 0 0px 0px, 0 0px 0px;
}
</style>
