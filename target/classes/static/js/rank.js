// ==========================================================
//                      後端設定 URL
// ==========================================================
// 貼文排名
const GetRankingPosts = "/PostingSystem/PostRank";
// 星星排名
const GetRankingUrl = "/api1/custo/queryMemberRank";
// 按讚
const sendIsLikeDataURL = "/Like/IsLike";
// 導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// 留言
const sendAndGetNewCommentURL = "/Message/message";

// ==========================================================
//                      前端測試 URL
// ==========================================================
// // 貼文排名
// const GetRankingPosts = "http://0.0.0.0:3000/EscapeME_index-test";
// // 星星排名
// const GetRankingUrl = "http://0.0.0.0:3000/EscapeME_ranking";
// // 按讚
// const sendIsLikeDataURL = "http://0.0.0.0:3000/EscapeME_index-test";
// // 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";
// // 留言
// const sendAndGetNewCommentURL = "http://0.0.0.0:3000/EscapeME_NewComments";

// ==========================================================

new Vue({
  el: "#app",
  data: {
    userImgSrc: "",
    posts: [],
    postModalDisplay: [],
    newComments: [],
    showTypingStyle: [],
    users: [
      { userImgSrc: null, memberId: null },
      { userImgSrc: null, memberId: null },
      { userImgSrc: null, memberId: null },
    ],
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
    showImg(index) {
      return this.users[index].userImgSrc;
    },
  },
  mounted() {
    // 獲取導覽列頭像
    fetch(GetNavUserImgUrl)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.userImgSrc = response.userImgSrc;
      });

    // 獲取按讚數最高貼文
    fetch(GetRankingPosts)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.posts = response;
      });

    // 獲取星星數最高使用者
    fetch(GetRankingUrl)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.users = response;
      });
  },
});
