// ==========================================================
//                      後端設定 URL
// ==========================================================
//獲取個人資訊
let GetMissionCountURL = "/PostingSystem/countTaskType";

//導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// ==========================================================
//                      前端測試 URL
// ==========================================================
// // 獲取個人資訊
// let GetMissionCountURL = "http://0.0.0.0:3000/missionCount";

// // 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";

// ==========================================================
//     下面這塊不要動！！！！會把參數加到請求的 api
// ==========================================================

// console.log(location.search);
GetMissionCountURL = GetMissionCountURL + location.search;
// ==========================================================

// ====================================================================
//                            Vue nav userImg
// ====================================================================
new Vue({
  el: ".navigation",
  data: {
    userImgSrc: "",
  },
  mounted() {
    fetch(GetNavUserImgUrl)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.userImgSrc = response.userImgSrc;
      });
  },
});

// ====================================================================
//                            Vue missionCountApp
// ====================================================================

let Vm = new Vue({
  el: "#missionCountApp",
  data: {
    userImgSrc: "",
    countIndex: [],
  },
  mounted() {
    fetch(GetMissionCountURL)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.userImgSrc = response.src;
        this.countIndex = response;
        google.charts.load("current", { packages: ["bar"] });
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ["", "次數"],
            ["自我探索", Vm.countIndex.mission_type_1],
            ["習慣養成", Vm.countIndex.mission_type_2],
            ["學習新知", Vm.countIndex.mission_type_3],
            ["休閒娛樂", Vm.countIndex.mission_type_4],
            ["藝術", Vm.countIndex.mission_type_5],
            ["美食", Vm.countIndex.mission_type_6],
            ["運動", Vm.countIndex.mission_type_7],
            ["戶外探索", Vm.countIndex.mission_type_8],
            ["人際戶動", Vm.countIndex.mission_type_9],
            ["環境與社會", Vm.countIndex.mission_type_10],
            ["人生成就", Vm.countIndex.mission_type_11],
          ]);

          var options = {
            // series: { 0: {color: '#ef7a85'}, 1: {color: '#697268'} },
            bars: "horizontal", // Required for Material Bar Charts.
            colors: ["#fff899"],
          };

          var chart = new google.charts.Bar(
            document.getElementById("barchart_material")
          );

          chart.draw(data, google.charts.Bar.convertOptions(options));
        }
      });
  },
});
