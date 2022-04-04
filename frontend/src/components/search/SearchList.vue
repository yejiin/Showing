<template>
  <div>
    <b-container fluid v-if="performanceList.length > 0">
      <b-row cols="6">
        <div id="deck" v-for="(item, index) in performanceList" :key="index">
          <b-col>
            <b-card
              id="my-card"
              class="mr-0 mb-2"
              :img-src="performanceList[index].lastSeasonImage"
              img-alt="Image"
              img-top
              tag="article"
              @click="detailShow(index)"
              style="cursor: pointer; width: 250px"
            >
              <div class="name">
                <strong>{{ performanceList[index].performanceName }}</strong>
              </div>
              <br />
              {{ performanceList[index].lastSeasonStartDate }} ~ {{ performanceList[index].lastSeasonEndDate }}
            </b-card>
          </b-col>
        </div>
      </b-row>
    </b-container>
    <div v-else>
      <h5 class="main_title">검색 결과가 없습니다.</h5>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchList",
  props: {
    performanceList: Array,
  },

  data() {
    return {};
  },

  methods: {
    detailShow(index) {
      this.$router.push({
        name: "ShowDetail",
        params: { showId: this.performanceList[index].performanceId },
      });
    },
  },
};
</script>

<style scoped>
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

.card {
  border: 0px;
}

.card-img-top {
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
}

.card-body {
  padding-left: 0px;
  padding-right: 0px;
}

.name {
  display: inline-block;
  width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.tags {
  margin-bottom: 5px;
}
</style>
