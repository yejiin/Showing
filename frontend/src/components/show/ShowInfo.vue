<template>
  <div>
    <b-card>
      <!-- <h5 class="main_title">공연정보</h5> -->
      <b-dropdown variant="secondary" id="dropdown-1" text="공연정보" class="m-md-0">
        <b-dropdown-item>시즌3</b-dropdown-item>
        <b-dropdown-item>시즌2</b-dropdown-item>
        <b-dropdown-item>시즌1</b-dropdown-item>
      </b-dropdown>
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

.btn-secondary {
  color: #212529;
  background-color: white !important;
  border-color: white !important;
  -webkit-box-shadow: 0 4px 6px rgb(50 50 93 / 11%), 0 1px 3px rgb(0 0 0 / 8%);
  box-shadow: 0 4px 6px rgb(50 50 93 / 11%), 0 1px 3px rgb(0 0 0 / 8%);
}

.location {
  float: left;
  margin-right: 50%;
}
</style>
