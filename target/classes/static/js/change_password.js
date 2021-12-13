// ====================================================================
//                            後端設定
// ====================================================================
// 導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// ====================================================================
//                            前端測試
// ====================================================================
// 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";

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
