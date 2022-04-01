<template>
  <Bar
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
</template>

<script>
import { Bar } from "vue-chartjs/legacy";
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 300,
    },
    height: {
      type: Number,
      default: 100,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Array,
      default: () => [],
    },
    chartData: {
      type: Object,
    },
  },
  data() {
    return {
      // chartData: {
      //   labels: ["0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5"],
      //   datasets: [
      //     {
      //       backgroundColor: "#FCD34D",
      //       data: [40, 20, 12, 12, 12],
      //     },
      //   ],
      // },
      chartOptions: {
        responsive: true,
        plugins: {
          legend: {
            display: false,
          },
        },

        scales: {
          x: {
            grid: {
              display: false,
            },
            ticks: {
              display: false,
            },
          },
          y: {
            display: false,
            // grid: {
            //   display: false,
            // },
            // ticks: {
            //   display: false,
            // },
          },
        },
      },
    };
  },

  mounted() {
    // this.renderChart(this.chartdata, this.chartOptions);/
  },
};
</script>
<style lang=""></style>
