// ====================================================================
//                   後端設定 URL
// ====================================================================
// 導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// 獲得貼文
const GetPostsUrl = "/index/test";

// 獲得更新貼文
const GetPostsUrl2 = "/index/test1";

// 按讚
const sendIsLikeDataURL = "/Like/IsLike";

// 留言
const sendAndGetNewCommentURL = "/Message/message";

// ====================================================================
//                    前端測試 URL
// ====================================================================
// // 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";

// // 獲得貼文
// const GetPostsUrl = "http://0.0.0.0:3000/EscapeME_index-test";
// // 獲得更新貼文
// const GetPostsUrl2 = "http://0.0.0.0:3000/EscapeME_index-test";

// // 按讚
// const sendIsLikeDataURL = "http://0.0.0.0:3000/EscapeME_index-test";

// // 留言
// const sendAndGetNewCommentURL = "http://0.0.0.0:3000/EscapeME_NewComments";

// ====================================================================

let vm = new Vue({
  el: "#app",
  data: {
    userImgSrc: "",
    posts: [],
    postsMore: null,
    newComments: [],
    showTypingStyle: [],
    scroll: "0",
  },
  methods: {
    // 個人頁面連結
    personalPageUrl(id) {
      let memberId = id;
      let personalPageUrl = "./personal_page_others.html";
      let newPersonalPageUrl = personalPageUrl + `?id=${memberId}`;
      return newPersonalPageUrl;
    },

    // 下滑新增貼文
    scorllEvent(e) {
      if (window.pageYOffset >= this.scrollY) {
        this.scrollY = this.scrollY + this.singlePostHeight * 5;
        this.postsNumber += 1;
        console.log({ postsNumber: this.postsNumber, scrollY: this.scrollY });

        const postNumData = {
          postNumber: this.postsNumber,
        };
        fetch(GetPostsUrl2, {
          method: "POST",
          body: JSON.stringify(postNumData),
          headers: new Headers({
            "Content-Type": "application/json",
          }),
        })
          .then((res) => res.json())
          .catch((error) => console.error("Error:", error))
          .then((response) => {
            console.log("Success:", response);
            this.postsMore = response;
            // console.log(this.postsMore)
            this.posts = this.posts.concat(this.postsMore);
            // console.log(this.posts)
          });
      }
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
    window.addEventListener("scroll", this.scorllEvent, true);

    // 獲取使用者資訊
    fetch(GetNavUserImgUrl)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        this.userImgSrc = response.userImgSrc;
        this.nickName = response.nickName;
      });
    // 獲取貼文
    fetch(GetPostsUrl)
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then((response) => {
        console.log("Success:", response);
        // 裝資料
        this.posts = response;
        // 裝完後抓一個單篇貼文的高度（為了等第一張圖片抓好，延遲300豪秒執行）
        setTimeout(() => {
          this.singlePostHeight =
            document.querySelectorAll(".single-post-with-comment")[0]
              .offsetHeight + 50;
          this.userWindowHeight = window.innerHeight;
          this.scrollY = this.singlePostHeight * 4 - this.userWindowHeight;
          this.postsNumber = 1;
          console.log({
            singlePostHeight: this.singlePostHeight,
            pageYOffset: window.pageYOffset,
            scrollY: this.scrollY,
          });
        }, 300);
      });
  },
});