// ==========================================================
//                      後端設定 URL
// ==========================================================
// 獲取個人資訊
let GetPersonalInfoURL = "/api1/custo/findMember";

// 獲取貼文
let GetPersonalPostsURL = "/api1/custo/findContent";

// 按讚
const sendIsLikeDataURL = "/Like/IsLike";

// 導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// 留言
const sendAndGetNewCommentURL = "/Message/message";

// ==========================================================
//                      前端測試 URL
// ==========================================================
// // 獲取個人資訊
// let GetPersonalInfoURL = "http://0.0.0.0:3000/EscapeME_GetPersonalInfo";

// // 獲取貼文
// let GetPersonalPostsURL = "http://0.0.0.0:3000/EscapeME_index-test";

// // 按讚
// const sendIsLikeDataURL = "http://0.0.0.0:3000/EscapeME_index-test";

// // 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";

// // 留言
// const sendAndGetNewCommentURL = "http://0.0.0.0:3000/EscapeME_NewComments";
// ==========================================================

// ==========================================================
//     下面這塊不要動！！！！會把參數加到請求的 api
// ==========================================================

// console.log(location.search);
GetPersonalInfoURL = GetPersonalInfoURL + location.search;
// console.log(GetPersonalInfoURL);

GetPersonalPostsURL = GetPersonalPostsURL + location.search;
// console.log(GetPersonalPostsURL);

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
//                Vue   GetPersonalInfo（取得個人檔案）
// ====================================================================
let personalInfoVue = new Vue({
  el: "#personalInfoApp",
  data: {
    // modal
    ChangeInfoM_IsShow: false,
    SetupM_IsShow: false,
    // =============
    memberId: "",
    nickName: "",
    userImgSrc: "",
    introduction: "",
    missionCount: "",
    starCount: "",
    fansCount: "",
    followCount: "",
  },
  methods: {
    // 個人頁面連結
    personalPageUrl(id) {
      let memberId = id;
      let personalPageUrl = "./personal_page_others.html";
      let newPersonalPageUrl = personalPageUrl + `?id=${memberId}`;
      return newPersonalPageUrl;
    },
    // 任務總數頁面連結
    missionCountUrl(id) {
      let memberId = id;
      let missionCountUrl = "./missionCount.html";
      let newMissionCountUrl = missionCountUrl + `?id=${memberId}`;
      return newMissionCountUrl;
    },
  },
  mounted() {
    //follow 按鈕
    let follow = document.getElementById("follow");
    let unfollow = document.getElementById("unfollow");

    follow.onclick = function () {
      unfollow.style.display = "block";
      follow.style.display = "none";
    };
    unfollow.onclick = function () {
      follow.style.display = "block";
      unfollow.style.display = "none";
    };
    // ===================================================

    fetch(GetPersonalInfoURL)
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
      });
  },
});

// ==========================================================
//                   Vue 2 personalPostApp
// ==========================================================

let personalPostVue = new Vue({
  el: "#personalPostApp",
  data: {
    posts: [],
    postModalDisplay: [],
    newComments: [],
    showTypingStyle: [],
  },
  methods: {
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
    fetch(GetPersonalPostsURL)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.posts = response;
      });
  },
});
