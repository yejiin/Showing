<template>
  <div>
    <b-card>
      <h4 class="main_title">평가 수</h4>
      <div class="rating-musical">
        <h3>{{ ratingInfo.musicalCnt }}</h3>
        <div>뮤지컬</div>
      </div>
      <div class="rating-theather">
        <h3>{{ ratingInfo.playCnt }}</h3>
        <div>연극</div>
      </div>
      <hr />
      <h4 class="main_title">별점 분포</h4>
      <div class="rating-chart">
        <bar-chart id="chart" :chart-data="chartData"></bar-chart>
        <!-- <bar-chart id="chart" :rating-data="ratingData"></bar-chart> -->
      </div>
      <div class="rating-el">
        <h2>{{ ratingInfo.ratingAvg }}</h2>
        <div>별점평균</div>
      </div>
      <div class="rating-el">
        <h3>{{ ratingInfo.ratingCnt }}</h3>
        <div>별점 개수</div>
      </div>
      <div class="rating-el">
        <h3>{{ ratingInfo.ratingMax }}</h3>
        <div>많이 준 별점</div>
      </div>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
import BarChart from "@/components/user/BarChart";
export default {
  name: "UserRating",
  components: {
    BarChart,
  },
  props: {
    ratingInfo: Object,
  },
  data() {
    return {
      // ratingData: null,
      chart: {},
    };
  },
  computed: {
    chartData: function () {
      this.chart = {
        labels: ["", "1", "", "2", "", "3", "", "4", "", "5"],
        datasets: [
          {
            backgroundColor: "#FCD34D",
            data: this.ratingInfo.ratingCntList,
          },
        ],
      };
      return this.chart;
    },
  },
};
</script>

<style scoped>
.card-text {
  margin-bottom: 0px;
  color: black;
}

.rating-musical {
  float: left;
  width: 50%;
  text-align: center;
}
.rating-theather {
  text-align: center;
}

.rating-el {
  width: 30%;
  float: left;
  text-align: center;
  margin-top: 30px;
}

.rating-chart {
  height: 100%;
  width: 100%;
  text-align: center;
}
#chart {
  width: 1000px;
  height: 380px;
  display: inline-block;
  text-align: center;
  margin-top: 20px;
}
</style>
