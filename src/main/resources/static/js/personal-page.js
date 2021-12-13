// ====================================================================
//                    後端設定 URL
// ====================================================================

let GetPersonalInfo = "/api1/custo/SelectMemberPersonalPage";
let GetPersonalPosts = "/api1/custo/SelectMemberContent";
let changeImageAndSelfIntroUrl = "/api1/custo/add";
let changeUserNameUrl = "/api1/custo/ChangeNickName";
let changePasswordUrl = "/api1/custo/ChangePassword";
let logoutUrl = "/api1/custo/LogOut";

// 按讚
const sendIsLikeDataURL = "/Like/IsLike";

// 留言
const sendAndGetNewCommentURL = "/Message/message";

// ====================================================================
//                    前端測試 URL
// ====================================================================

// let GetPersonalInfo = "http://0.0.0.0:3000/EscapeME_GetPersonalInfo";
// let GetPersonalPosts = "http://0.0.0.0:3000/EscapeME_index-test";
// let changeImageAndSelfIntroUrl = "http://0.0.0.0:3000/EscapeME_test";
// let changeUserNameUrl = "http://0.0.0.0:3000/EscapeME_test";
// let changePasswordUrl = "http://0.0.0.0:3000/EscapeME_test";
// let logoutUrl = "http://0.0.0.0:3000/EscapeME_test";
// // 按讚
// const sendIsLikeDataURL = "http://0.0.0.0:3000/EscapeME_index-test";

// // 留言
// const sendAndGetNewCommentURL = "http://0.0.0.0:3000/EscapeME_NewComments";

// ====================================================================

// ====================================================================
//                Vue   GetPersonalInfo（取得個人檔案）
// ====================================================================
let personalInfoVue = new Vue({
  el: "#app",
  data: {
    memberId: "",
    nickName: "",
    userImgSrc: "",
    introduction: "",
    missionCount: "",
    starCount: "",
    fansCount: "",
    followCount: "",
    phone: "",
    email: "",
    // =============
    // modal 開關
    settingValue: false,
    personalValue: false,
    // =============
    // 編輯
    previewImgSrc: null,
    // =============
    posts: [],
    postModalDisplay: [],
    newComments: [],
    showTypingStyle: [],
  },
  methods: {
    settingBtn() {
      this.settingValue = true;
    },
    // 關閉變更頭像自介視窗
    settingClose() {
      Swal.fire({
        icon: "warning",
        title: "確定要捨棄變更？",
        text: "捨棄變更將刪除目前編輯的東西",
        confirmButtonText: "再想想",
        showDenyButton: true,
        denyButtonText: "捨棄變更",
      }).then((result) => {
        if (result.isDenied) {
          this.settingValue = false;
          location = "./personal_page.html";
        }
      });
    },
    // 任務總數頁面連結
    missionCountUrl(id) {
      let memberId = id;
      let missionCountUrl = "./missionCount.html";
      let newMissionCountUrl = missionCountUrl + `?id=${memberId}`;
      return newMissionCountUrl;
    },
    personalBtn() {
      this.personalValue = true;
    },
    personalClose() {
      this.personalValue = false;
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
          personalInfoVue.previewImgSrc = readFile.result;
        });
      }
    },

    // 更新並上傳大頭貼及個介
    uploadNewUserInfo() {
      const personalFileData = {
        selfIntro: personalInfoVue.introduction,
        src: personalInfoVue.previewImgSrc,
      };
      // console.log(personalFileData);

      fetch(changeImageAndSelfIntroUrl, {
        method: "POST",
        body: JSON.stringify(personalFileData),
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
              title: "變更成功",
              text: "即將重整頁面",
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didClose: () => {
                // 關閉後重整頁面
                location = "./personal_page.html";
              },
            });
          }
          if (response.code == 400) {
            Swal.fire({
              icon: "error",
              title: "變更失敗",
              text: response.message,
            });
          }
        });
    },
    // 個人頁面連結
    personalPageUrl(id) {
      let memberId = id;
      let personalPageUrl = "./personal_page_others.html";
      let newPersonalPageUrl = personalPageUrl + `?id=${memberId}`;
      return newPersonalPageUrl;
    },

    // 顯示單篇貼文 modal
    showModal(index) {
      this.$set(this.postModalDisplay, index, true);
    },

    // 關閉單篇貼文 modal
    closeModal(index) {
      this.$set(this.postModalDisplay, index, false);
    },

    // 按讚
    changeIsLike(p, postId) {
      p.isLike = !p.isLike;
      if (p.isLike) {
        p.likeCount += 1;
      } else {
        p.likeCount -= 1;
      }

      const isLikeData = {
        postId: postId,
        isLike: p.isLike,
      };

      fetch(sendIsLikeDataURL, {
        method: "POST",
        body: JSON.stringify(isLikeData),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then((response) => {
          // console.log("Success:", response);
        });
    },
    // 留言中樣式
    showTyping(index, div) {
      // 呈現留言中字樣
      this.$set(this.showTypingStyle, index, true);
      // 滑至最底
      let container = document.getElementById(div);
      setTimeout(function () {
        container.scrollTop = container.scrollHeight;
      }, 50);
    },
    notTyping(index) {
      this.$set(this.showTypingStyle, index, false);
    },

    // 送出留言
    sendNewComment(newComment, postId, index) {
      //判斷是否為 空字串 或 undefined，如果是就不傳送
      if (newComment !== undefined && newComment !== "") {
        const NewCommentData = {
          postId: postId,
          commentContent: newComment,
        };
        this.newComments = [];
        console.log(NewCommentData);

        fetch(sendAndGetNewCommentURL, {
          method: "POST",
          body: JSON.stringify(NewCommentData),
          headers: new Headers({
            "Content-Type": "application/json",
          }),
        })
          .then((res) => res.json())
          .catch((error) => console.error("Error:", error))
          .then((response) => {
            console.log("Success:", response);

            // 裝新資料
            this.$set(this.posts, index, response);

            // 滑到最底
            let container = document.getElementById(postId);
            setTimeout(function () {
              container.scrollTop = container.scrollHeight;
            }, 50);
          });
      }
    },
  },
  mounted() {
    // ====================================================================
    //              personal-setup（更換個人設定：名稱、密碼 ＆ 登出）
    // ====================================================================
    // ===============更換名稱================
    let changeUserNameBtn = document.getElementById("change-userName-btn");
    changeUserNameBtn.onclick = function () {
      let userName = document.getElementById("userName").value;
      const userNameData = {
        nickName: userName,
      };
      //   console.log(userNameData);
      fetch(changeUserNameUrl, {
        method: "POST",
        body: JSON.stringify(userNameData),
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
              title: "變更成功",
              text: "即將重整頁面",
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didClose: () => {
                // 關閉後重整頁面
                location = "./personal_page.html";
              },
            });
          }
          if (response.code == 400) {
            Swal.fire({
              icon: "error",
              title: "變更失敗",
              text: response.message,
            });
          }
        });
    };

    // ===============修改密碼================

    let changePasswordBtn = document.getElementById("change-password-btn");

    changePasswordBtn.onclick = function () {
      let password = document.getElementById("password").value;
      let newPassword = document.getElementById("new-password").value;
      let confirmNewPassword = document.getElementById(
        "confirm-new-password"
      ).value;

      const newPasswordData = {
        password: password,
        newPassword: newPassword,
        confirmNewPassword: confirmNewPassword,
      };
      console.log(newPasswordData);
      fetch(changePasswordUrl, {
        method: "POST",
        body: JSON.stringify(newPasswordData),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then(function (response) {
          console.log("Success:", response);

          if (response.code == 155) {
            Swal.fire({
              icon: "error",
              title: "確認密碼錯誤",
              text: "眼睛睜大點好嗎",
            });
          }
          if (response.code == 150) {
            Swal.fire({
              icon: "error",
              title: "個人密碼錯誤",
              text: "重新輸入個人密碼",
            });
          }

          if (response.code == 200) {
            Swal.fire({
              icon: "success",
              title: "成功變更密碼",
              text: "請妥善保存密碼",
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didClose: () => {
                // 關閉後重整頁面
                location = "./personal_page.html";
              },
            });
          }
          if (response.code == 400) {
            Swal.fire({
              icon: "error",
              title: "密碼變更失敗",
              text: response.message,
            });
          }
        });
    };

    // ===============登出================

    let logoutBtn = document.getElementById("logout-btn");
    logoutBtn.onclick = function () {
      const logoutData = {
        logout: 1,
      };
      //   console.log(userNameData);
      fetch(logoutUrl, {
        method: "POST",
        body: JSON.stringify(logoutData),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then(function (response) {
          //   console.log("Success:", response);
          location = response.data;
        });
    };

    // ==========================================================
    // 獲取個人資訊
    fetch(GetPersonalInfo)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.userImgSrc = response.userImgSrc;
        this.nickName = response.nickName;
        this.memberId = response.memberId;
        this.introduction = response.introduction;
        this.missionCount = response.missionCount;
        this.starCount = response.starCount;
        this.fansCount = response.fansCount;
        this.followCount = response.followCount;
        this.phone = response.phone;
        this.email = response.email;
        this.previewImgSrc = this.userImgSrc;
      });

    // 獲取貼文
    fetch(GetPersonalPosts)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.posts = response;
      });
  },
});
