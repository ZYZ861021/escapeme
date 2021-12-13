//搜尋商品
const GetShoppingUrl = "/shopping/findCommodity";

//購買商品
const GetBuyUrl = "/shopping/buyCommodity";

// 導覽列頭像
const GetNavUserImgUrl = "/api1/custo/findMemberImage";

// ====================================
//          前端測試 URL
// ====================================
// // 獲取商品
// const GetShoppingUrl = "http://0.0.0.0:3000/shopping";
// // 導覽列頭像
// const GetNavUserImgUrl = "http://0.0.0.0:3000/EscapeME_userImg";
// ====================================

var shopVm = new Vue({
    el: "#app",
    data: {
        shoppingList: [],
        req: {
            star: null,
            starTop: null,
            type: null,
            title: null,
        },
        // 導覽列頭像
        userImgSrc: "",
    },
    methods: {
        //BUY觸發事件
        buyBtn(id, title) {
            Swal.fire({
                title: '確定要購買嗎?',
                showCancelButton: true,
                cancelButtonText: "取消",
                confirmButtonText: '確定',
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                    Swal.fire({
                        html:
                            '<h1>請輸入收件人</h1>' +
                            '姓名: <input id="input_name" class="mt-3"></input><br>' +
                            '電話: <input id="input_phone" class="mt-1"></input><br>' +
                            '地址: <input id="input_address" class="mt-1"></input>',
                        inputAttributes: {
                            autocapitalize: 'off'
                        },
                        showCancelButton: true,
                        confirmButtonText: '購買',
                        cancelButtonText: '取消',
                        showLoaderOnConfirm: true,
                        preConfirm: (login) => {
                            return fetch(GetBuyUrl, {
                                method: "POST",
                                body: JSON.stringify({
                                    "orderName" : document.getElementById("input_name").value,
                                    "phone" : document.getElementById("input_phone").value,
                                    "address" : document.getElementById("input_address").value,
                                    "id": id
                                }),
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
                                            icon: 'success',
                                            title: '恭喜' + response.nickName,
                                            text:'購買成功\n您的商品為: ' + title + '\n您的剩餘星星數為: ' + response.star,
                                            showConfirmButton: false
                                        })
                                    }
                                    if (response.code == 400) {
                                        Swal.fire({
                                            icon: "error",
                                            title: "購買失敗",
                                            text: response.message,
                                        });
                                    }
                                })
                        },
                        allowOutsideClick: () => !Swal.isLoading()
                    })
                }
            })
        },
        typeClick(type) {
            this.req.type = type;
            // console.log(this.req.type)
            fetch(GetShoppingUrl, {
                method: "POST",
                body: JSON.stringify(this.req),
                headers: new Headers({
                    "Content-Type": "application/json",
                }),
            })
                .then((res) => res.json())
                .catch((error) => console.error("Error:", error))
                .then((response) => {
                    console.log("Success:", response);
                    this.shoppingList = response;
                });
        },
        starClick(star, starTop) {
            this.req.star = star;
            this.req.starTop = starTop;
            fetch(GetShoppingUrl, {
                method: "POST",
                body: JSON.stringify(this.req),
                headers: new Headers({
                    "Content-Type": "application/json",
                }),
            })
                .then((res) => res.json())
                .catch((error) => console.error("Error:", error))
                .then((response) => {
                    console.log("Success:", response);
                    this.shoppingList = response;
                });
        },
        allClick(star, starTop, title, type) {
            this.req.star = star;
            this.req.starTop = starTop;
            this.req.title = title;
            this.req.type = type;
            fetch(GetShoppingUrl, {
                method: "POST",
                body: JSON.stringify(this.req),
                headers: new Headers({
                    "Content-Type": "application/json",
                }),
            })
                .then((res) => res.json())
                .catch((error) => console.error("Error:", error))
                .then((response) => {
                    console.log("Success:", response);
                    this.shoppingList = response;
                });
        },
    },
    mounted() {
        fetch(GetShoppingUrl, {
            method: "POST",
            body: JSON.stringify(this.req),
            headers: new Headers({
                "Content-Type": "application/json",
            }),
        })
            .then((res) => res.json())
            .catch((error) => console.error("Error:", error))
            .then((response) => {
                console.log("Success:", response);
                this.shoppingList = response;
            });

        // 導覽列頭像
        fetch(GetNavUserImgUrl)
            .then((res) => res.json())
            .catch((error) => console.error("Error:", error))
            .then((response) => {
                console.log("Success:", response);
                this.userImgSrc = response.userImgSrc;
            });
    },
});
