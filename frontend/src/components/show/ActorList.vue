<template>
  <div class="mainbox mt-4">
    <div v-if="actor.length > 1">
      <!-- <span class="subTitle mt-2">캐스팅</span> -->
      <!-- carousel area -->
      <carousel :perPage="4">
        <slide class="p-1 mt-3" v-for="(item, index) in actor" :key="index">
          <div class="rounded">
            <!-- card content -->
            <div>
              <b-img class="image-box" :src="actor[index].actorImage" center />
            </div>
            <h6 class="actor_name" v-if="actor[index].role != undefined || actor[index].role != null">
              {{ actor[index].role }}
            </h6>
            <h5 class="actor_name">{{ actor[index].actorName }}</h5>
          </div>
        </slide>
      </carousel>
      <br />
    </div>
  </div>
</template>

<script>
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "ActorList",
  props: {
    actor: Array,
  },
  components: {
    Carousel,
    Slide,
  },
};
</script>

<style scoped>
.title {
  font-weight: 700;
  font-size: 23px;
}
.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}

.index {
  margin-left: 10px;
  width: 10px;
  height: 10px;
  background: #9badf6;
}

.active {
  width: 13px;
  height: 13px;
}

.arrow {
  margin-top: 10%;
  color: #9badf6;
}

.arrow_right {
  margin-left: 1%;
}

.card-text {
  color: black;
  text-align: center;
}

.actor_name {
  margin-top: 0px;
  text-align: center;
}

.profile-image {
  height: 50%;
  width: 100%;
}

.image-box {
  width: 120px;
  height: 120px;
  border-radius: 70%;
  overflow: hidden;
  object-fit: cover;
  background-color: wheat;
}

.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.subTitle {
  font-weight: 600;
  color: #525f7f;
  font-size: 23px;
}
</style>
