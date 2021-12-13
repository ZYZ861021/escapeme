// ====================================================================
//                          後端設定 URL
// ====================================================================
// 搜尋使用者
const GetSearchMembers = "/Task/findByNickname";
// 搜尋主題貼文
const GetSearchPosts = "/Task/findByType";
// 按讚
const sendIsLikeDataURL = "/Like/IsLike";
// 導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// 留言
const sendAndGetNewCommentURL = "/Message/message";

// ====================================================================
//                            前端測試 URL
// ====================================================================
// // 搜尋使用者
// const GetSearchMembers = "http://0.0.0.0:3000/EscapeME_ranking";
// // 搜尋主題貼文
// const GetSearchPosts = "http://0.0.0.0:3000/EscapeME_index-test";
// // 按讚
// const sendIsLikeDataURL = "http://0.0.0.0:3000/EscapeME_index-test";
// // 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";
// // 留言
// const sendAndGetNewCommentURL = "http://0.0.0.0:3000/EscapeME_NewComments";

// ====================================================================

let vm = new Vue({
  el: "#app",
  data: {
    userImgSrc: "",
    searchPost: false,
    searchId: false,
    posts: [],
    members: [],
    postModalDisplay: [],
    newComments: [],
    showTypingStyle: [],
    searchInput: "",
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

    // 搜尋使用者
    SearchUserName() {
      this.searchPost = false;
      this.searchId = true;

      let Data = {
        nickName: this.searchInput,
      };
      console.log(Data);
      fetch(GetSearchMembers, {
        method: "POST",
        redirect: "manual",
        body: JSON.stringify(Data),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then((response) => {
          console.log("Success:", response);
          this.members = response;
        });
    },

    // 搜尋任務主題
    SearchTaskType(taskType) {
      this.searchPost = true;
      this.searchId = false;

      let Data = {
        taskType: taskType,
      };
      console.log(Data);
      fetch(GetSearchPosts, {
        method: "POST",
        redirect: "manual",
        body: JSON.stringify(Data),
        headers: new Headers({
          "Content-Type": "application/json",
        }),
      })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then((response) => {
          console.log("Success:", response);
          this.posts = response;
        });
    },
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
