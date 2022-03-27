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
          <a class="dropdown-item">시즌3</a>
          <a class="dropdown-item">시즌2</a>
          <a class="dropdown-item">시즌1</a>
        </div>
      </div>
      <br />
      <br />
      <span class="badge badge-pill badge-warning">15세 이상 관람가</span>
      <br /><br />
      <b-card-text>일정</b-card-text>
      <b-card-text class="location">장소</b-card-text>
      <b-card-text>공연시간</b-card-text>
      <actor-list></actor-list>
    </b-card>
    <br />
    <br />
  </div>
</template>

<script>
import ActorList from "@/components/show/ActorList";

export default {
  name: "ShowInfo",
  components: {
    ActorList,
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

.location {
  float: left;
  margin-right: 50%;
}
</style>
