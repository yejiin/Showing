<template>
  <div id="app">
    <router-view name="header"></router-view>
    <main>
      <fade-transition origin="center" mode="out-in" :duration="250">
        <router-view :key="$route.fullPath" />
      </fade-transition>
    </main>
    <router-view name="footer"></router-view>
  </div>
</template>
<script>
import { FadeTransition } from "vue2-transitions";

export default {
  components: {
    FadeTransition,
  },
};
</script>

<style>
.click_img {
  cursor: pointer;
}

.main_title {
  font-weight: bold;
}

.header {
  margin-top: 50px;
  padding-left: 5%;
  padding-right: 5%;
}
</style>
