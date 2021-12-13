// ===========================================================
//                      設定 url (後端api)
// ===========================================================
// 忘記密碼輸入信箱
let forgotPwdUrl_email = "/ForgerThePassword/sendEmail";
// 認證 eamil api
let forgotPwdUrl_verify = "/ForgerThePassword/check";
// 更改密碼 api
let newPwdUrl = "/ForgerThePassword/checkpassword";

// ===========================================================
//                      獲取本頁全部 Modal
// ===========================================================
let verifyModal = document.getElementById("verifyModal");
let newPasswordModal = document.getElementById("newPasswordModal");

// ===========================================================
//                        忘記密碼輸入信箱
// ===========================================================

let btnForgetPassword = document.getElementById("forget-password-submit");
let email = document.getElementById("forget-password-email");

btnForgetPassword.onclick = function () {

  let forgetPasswordData = {
    email: email.value,
  };
  fetch(forgotPwdUrl_email, {
    method: "POST",
    body: JSON.stringify(forgetPasswordData),
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
          title: "請至 Email 收取驗證信!",
          confirmButtonText: "下一步",
        }).then((result) => {
          if (result.isConfirmed) {
            // 開啟輸入驗證碼 Modal
            verifyModal.style.display = "block";
          }
        });
      }
      if (response.code == 400) {
        Swal.fire({
          icon: "error",
          title: "Oops!",
          showConfirmButton: false,
          showCloseButton: true,
          footer: `可能的註冊失敗原因：${response.message}`,
        });
      }
    });
};

// ===========================================================
//                        輸入Email驗證碼
// ===========================================================

// 獲取驗證按鈕及驗證碼
let verifyBtn = document.getElementById("verify-submit");
let v1 = document.getElementById("v1");
let v2 = document.getElementById("v2");
let v3 = document.getElementById("v3");
let v4 = document.getElementById("v4");
let v5 = document.getElementById("v5");
let v6 = document.getElementById("v6");

verifyBtn.onclick = function () {
  let mail =
    v1.value + v2.value + v3.value + v4.value + v5.value + v6.value;
  let verifyData = {
    email: email.value,
     mail: mail,
  };
  console.log(verifyData);
  fetch(forgotPwdUrl_verify, {
    method: "POST",
    body: JSON.stringify(verifyData),
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
          title: "驗證成功！",
          confirmButtonText: "下一步",
        }).then((result) => {
          if (result.isConfirmed) {
            // 開啟新密碼設定 Modal 並關閉 驗證 Modal
            newPasswordModal.style.display = "block";
            verifyModal.style.display = "none";
          }
        });
      }
      if (response.code == 400) {
        Swal.fire({
          icon: "error",
          title: "Oops! Something's wrong...",
          text: response.message,
        });
      }
    });
};

// ===========================================================
//                        輸入新密碼
// ===========================================================
let newPassword = document.getElementById("new-password");
let newConfirmPassword = document.getElementById("new-confirm-password");
let newPwd_submit = document.getElementById("newPwd-submit");

// password 判斷
const passwordReg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}$/;
newPassword.oninput = function () {
  setInterval(() => {
    let passwordInputError = document.getElementById("password-input-error");
    if (passwordReg.test(newPassword.value)) {
      passwordInputError.innerText = "";
    } else {
      passwordInputError.innerText = "密碼格式不符";
    }
  }, 2000);
};

// password 確認 判斷
newConfirmPassword.oninput = function () {
  let confirmPasswordInputError = document.getElementById(
    "confirm-password-input-error"
  );
  setInterval(() => {
    if (newConfirmPassword.value === newPassword.value) {
      confirmPasswordInputError.innerText = "";
    } else {
      confirmPasswordInputError.innerText = "密碼不符...";
    }
  }, 2000);
};
// 按按鈕傳送資料
newPwd_submit.onclick = function () {
  let newPwdData = {
	email: email.value,
    password: newPassword.value,
  };
  // console.log(newPwdData);
  fetch(newPwdUrl, {
    method: "POST",
    body: JSON.stringify(newPwdData),
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
          title: "密碼變更成功",
          text: "3秒後將跳轉至登入頁面",
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didClose: () => {
          location = response.data;
          },
        });
      }
      if (response.code == 400) {
        Swal.fire({
          icon: "error",
          title: "Oops! Oops! Something's wrong...",
          text: response.message,
        });
      }
    });
};
