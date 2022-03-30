<template>
  <div>
    <b-card>
      <h4 class="main_title">평가 수</h4>
      <div class="rating-musical">
        <h5>20</h5>
        <div>뮤지컬</div>
      </div>
      <div class="rating-theather">
        <h5>20</h5>
        <div>연극</div>
      </div>
      <hr />
      <h5 class="main_title">별점 분포</h5>
      <bar-chart></bar-chart>
      <div class="rating-el">
        <h6>3.4</h6>
        <div>별점평균</div>
      </div>
      <div class="rating-el">
        <h6>100</h6>
        <div>별점 개수</div>
      </div>
      <div class="rating-el">
        <h6>3.5</h6>
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
</style>
