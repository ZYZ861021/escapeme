// ==========================================================
//                      後端設定 URL
// ==========================================================
// // 獲取目前任務狀態
 const GetMissionStatus = "/api/rand/look";

// // 抽新任務
 const GetNewMissionUrl = "/api/rand/ExtractionTask";

// // 發文
 const UploadPostContentUrl = "/api/rand/true";

// // 獲取成就任務
 const GetLifeMissionsUrl = "/Task/Achievement";
 
 // 導覽列頭像
 const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// ==========================================================
//                      前端測試 URL
// ==========================================================
// 獲取目前任務狀態
//const GetMissionStatus = "http://0.0.0.0:3000/EscapeME_MissionStatus";

// 抽新任務
//const GetNewMissionUrl = "http://0.0.0.0:3000/EscapeME_NewMission";

// 發文
//const UploadPostContentUrl = "http://0.0.0.0:3000/EscapeME_test";

// 獲取成就任務
//const GetLifeMissionsUrl = "http://0.0.0.0:3000/EscapeME_GetLifeMissions";

// 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";

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

// ==========================================================
//                    Vue 每日任務
// ==========================================================

var DMvm = new Vue({
  el: "#missionVue",
  data: {
    isShow: false,
    missionLeft: null,
    styleDisplay: false,
    previewImgSrc: null,
    postContent: "",
    mission: {},
  },
  methods: {
    cgDisplay() {
      this.styleDisplay = true;
    },
    close() {
      this.styleDisplay = false;
    },

    // 抽新任務
    getNewMission() {
      // 如果原本有任務（抽新任務前會提示）
      if (this.isShow === true) {
        Swal.fire({
          icon: "warning",
          title: "確定要放棄任務？",
          text: "目前任務將被清空",
          footer: "放棄的任務短時間內將不再出現",
          confirmButtonText: "再想想",
          showDenyButton: true,
          denyButtonText: "放棄任務",
        }).then((result) => {
          if (result.isDenied) {
            // 這邊看後端怎麼判斷要拿新任務，可以自己修改
            const missionRequestData = {
              newMission: 1,
            };

            fetch(GetNewMissionUrl, {
              method: "POST",
              body: JSON.stringify(missionRequestData),
              headers: new Headers({
                "Content-Type": "application/json",
              }),
            })
              .then((res) => res.json())
              .catch((error) => console.error("Error:", error))
              .then((response) => {
                console.log("Success:", response);
                this.isShow = response.isShow;
                this.missionLeft = response.missionLeft;
                this.mission = response.mission;
              });
          }
        });
        // 如果原本本有任務可以直接抽任務
      } else if (this.isShow === false) {
        const missionRequestData = {
          newMission: 1,
        };

        fetch(GetNewMissionUrl, {
          method: "POST",
          body: JSON.stringify(missionRequestData),
          headers: new Headers({
            "Content-Type": "application/json",
          }),
        })
          .then((res) => res.json())
          .catch((error) => console.error("Error:", error))
          .then((response) => {
            console.log("Success:", response);
            this.isShow = response.isShow;
            this.missionLeft = response.missionLeft;
            this.mission = response.mission;
          });
      }
    },

    // 上傳圖片轉 base64
    uploadPreviewImg(e) {
      console.log(e.target);
      if (e.target.files[0].size > 2097152) {
        Swal.fire({
          icon: "error",
          title: "Oops！檔案太大了",
          text: "檔案需 < 2MB",
        });
      } else {
        let readFile = new FileReader();
        readFile.readAsDataURL(e.target.files[0]);
        readFile.addEventListener("load", function () {
          DMvm.previewImgSrc = readFile.result;
        });
      }
    },

    // 上傳貼文
    uploadPostContent() {
      let postContentData = {
        postImage: DMvm.previewImgSrc,
        postContent: DMvm.postContent,
        missionId: DMvm.mission.missionId,
      };
      // console.log(postContentData);
      fetch(UploadPostContentUrl, {
        method: "POST",
        body: JSON.stringify(postContentData),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then(function (response) {
          console.log("Success:", response);
          if (response.code == 200) {
            Swal.fire({
              icon: "success",
              title: "上傳成功",
              showConfirmButton: false,
              text: "即將重整頁面",
              timer: 3000,
              timerProgressBar: true,
              didClose: () => {
                // 關閉後重整頁面
                location = "./mission.html";
              },
            });
          }
          if (response.code == 400) {
            Swal.fire({
              icon: "error",
              title: "上傳失敗",
              text: response.message,
            });
          }
        });
    },
  },
  mounted() {
    fetch(GetMissionStatus)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.isShow = response.isShow;
        this.missionLeft = response.missionLeft;
        this.mission = response.mission;
      });
  },
});
// ==========================================================
//                    Vue 人生成就任務
// ==========================================================

var LMvm = new Vue({
  el: "#LifeMissionsApp",
  data: {
    lifeMissionModalDisplay: [],
    showImg: false,
    previewImgSrc: null,
    postContent: "",
    lifeMissions: [],
  },
  methods: {
    postModalDisplay(index) {
      this.$set(this.lifeMissionModalDisplay, index, true);
    },
    // 關掉編輯貼文視窗（成就任務）
    closePostModal(index) {
      Swal.fire({
        icon: "warning",
        title: "確定要捨棄貼文？",
        text: "捨棄貼文將刪除目前編輯的東西",
        confirmButtonText: "再想想",
        showDenyButton: true,
        denyButtonText: "放棄任務",
      }).then((result) => {
        if (result.isDenied) {
          this.$set(this.lifeMissionModalDisplay, index, false);
          this.previewImgSrc = null;
          this.postContent = "";
          this.showImg = false;
        }
      });
    },
    // 上傳圖片轉 base64
    uploadPreviewImg(event) {
      LMvm.showImg = true;

      if (event.target.files[0].size > 2097152) {
        Swal.fire({
          icon: "error",
          title: "Oops！檔案太大了",
          text: "檔案需 < 2MB",
        });
      } else {
        let readFile = new FileReader();
        readFile.readAsDataURL(event.target.files[0]);
        readFile.addEventListener("load", function () {
          LMvm.previewImgSrc = readFile.result;
        });
      }
    },

    // 上傳貼文
    uploadPostContent(missionId) {
      // console.log(missionId);

      let postContentData = {
        postImage: LMvm.previewImgSrc,
        postContent: LMvm.postContent,
        missionId: missionId,
      };

      console.log(postContentData);
      fetch(UploadPostContentUrl, {
        method: "POST",
        body: JSON.stringify(postContentData),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then(function (response) {
          console.log("Success:", response);
          if (response.code == 200) {
            Swal.fire({
              icon: "success",
              title: "上傳成功",
              showConfirmButton: false,
              text: "即將重整頁面",
              timer: 3000,
              timerProgressBar: true,
              didClose: () => {
                // 關閉後重整頁面
                location = "./mission.html";
              },
            });
          }
          if (response.code == 400) {
            Swal.fire({
              icon: "error",
              title: "上傳失敗",
              text: response.message,
            });
          }
        });
    },
  },
  watch: {
    postImgSrc: {
      handler() {
        console.log("postImgSrc 被改變了");
      },
    },
    postContent: {
      handler() {
        console.log("postContent 被改變了");
      },
    },
  },
  mounted() {
    fetch(GetLifeMissionsUrl)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.lifeMissions = response;
      });
  },
});
