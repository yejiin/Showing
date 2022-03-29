<template>
  <div>
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
          <!-- <a v-for="(seasonDate, index) in seasonDates" :key="index" class="dropdown-item">
            {{ seasonDate[index].startDate }} ~ {{ seasonDate[index].endDate }}
          </a> -->
          <a class="dropdown-item"> {{ seasonDates[0].startDate }} ~ {{ seasonDates[0].endDate }} </a>
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
</template>

<script>
import ActorList from "@/components/show/ActorList";
import Story from "@/components/show/Story";
import { getSeasonShow } from "@/api/show.js";

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
  },
  data() {
    return {
      showInfo: {},
      seasonDates: [],
    };
  },
  async created() {
    await getSeasonShow(
      "911",
      (response) => {
        this.seasonDates = response.data.data;
        // console.log(this.seasonDates);
      },
      (error) => {
        console.log(error);
      }
    );
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
