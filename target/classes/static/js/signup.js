// ===========================================================
//                      設定 url (後端api)
// ===========================================================
// 註冊 api
let signupUrl = "/api1/custo/signUp";

// 驗證 eamil 驗證碼 api
let verifyEmailUrl = "/api1/custo/check";
// ===========================================================

// ===========================================================
//                      前端輸入註冊資料判斷
// ===========================================================
// 獲取判斷相關元素

let email = document.getElementById("signup-email");
let password = document.getElementById("signup-password");
let confirmPassword = document.getElementById("signup-confirm-password");
let nickName = document.getElementById("username");
let phone = document.getElementById("user-phone");

// 傳送前判斷資料是否正確(初始為錯誤)
let signupDataIsCompleted = false;

// email 判斷
const emailReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
let emailInputError = document.getElementById("email-input-error");
email.oninput = function () {
  setInterval(() => {
    if (emailReg.test(email.value)) {
      emailInputError.innerText = "";
      signupDataIsCompleted = true;
    } else {
      emailInputError.innerText = "請輸入合法的 Email";
      signupDataIsCompleted = false;
    }
  }, 2000);
};

// password 判斷
const passwordReg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}$/;
password.oninput = function () {
  setInterval(() => {
    let passwordInputError = document.getElementById("password-input-error");
    if (passwordReg.test(password.value)) {
      passwordInputError.innerText = "";
      signupDataIsCompleted = true;
    } else {
      passwordInputError.innerText = "請輸入8~15位的數字與英文密碼";
      signupDataIsCompleted = false;
    }
  }, 2000);
};

// password 確認 判斷
confirmPassword.oninput = function () {
  let confirmPasswordInputError = document.getElementById(
    "confirm-password-input-error"
  );
  setInterval(() => {
    if (confirmPassword.value === password.value) {
      confirmPasswordInputError.innerText = "";
      signupDataIsCompleted = true;
    } else {
      confirmPasswordInputError.innerText = "密碼不符...";
      signupDataIsCompleted = false;
    }
  }, 2000);
};

// phone 判斷
const phoneReg = /^09[0-9]{8}$/;
let phoneInputError = document.getElementById("phone-input-error");
phone.oninput = function () {
  setInterval(() => {
    if (phoneReg.test(phone.value)) {
      phoneInputError.innerText = "";
      signupDataIsCompleted = true;
    } else {
      phoneInputError.innerText = "請輸入正確的手機號碼(台灣)";
      signupDataIsCompleted = false;
    }
  }, 2000);
};


// ===========================================================
//                      向後端傳送資料
// ===========================================================
// signup 資料傳送（按鈕：signup-submit）
let btnSignupSubmit = document.getElementById("signup-submit");

btnSignupSubmit.onclick = function () {
  // 按下傳送按鈕後先判斷是否有空值或格式不符

  // 判斷不能為空值
  if (
    email.value === "" ||
    email.value === null ||
    password.value === "" ||
    password.value === null ||
    confirmPassword.value === "" ||
    confirmPassword.value === null ||
    nickName.value === "" ||
    nickName.value === null ||
    phone.value === "" ||
    phone.value === null
  ) {
    signupDataIsCompleted = false;
  } else {
    usernameInputError = true;
  }

  // (關掉判斷可將 false 改 true)
  if (signupDataIsCompleted === false) {
    Swal.fire({
      icon: "error",
      title: "Opps！註冊資料有誤 ...",
      text: "請檢查是否漏填或格式不符",
    });
  } else {
    // 相關元素已於判斷時獲取（這邊將資料打包）
    const signupData = {
      email: email.value,
      password: password.value,
      nickName: nickName.value,
      phone: phone.value,
    };
    console.log(signupData);

    fetch(signupUrl, {
      method: "POST",
      body: JSON.stringify(signupData),
      headers: new Headers({
        "Content-Type": "application/json",
      }),
    })
      .then((res) => res.json())
      .catch((error) => console.error("Error:", error))
      .then(function (response) {
        console.log("Success:", response);
        // 註冊資料回傳判斷（成功）
        if (response.code == 200) {
          Swal.fire({
            icon: "success",
            title: "註冊資料填寫完成",
            text: "請至 Email 信箱收信",
            confirmButtonText: "下一步",
          }).then(function (result) {
            if (result.isConfirmed) {
              // 點擊輸入驗證碼開啟驗證碼Modal
              verifyModal.style.display = "block";

              // 點選驗證，傳送認證碼
            }
          });
        }
        // 註冊資料回傳判斷（失敗）
        if (response.code == 400) {
          Swal.fire({
            icon: "error",
            title: "Oops!",
            showConfirmButton: false,
            showCloseButton: true,
            footer: `可能的註冊失敗原因：${response.message}`,
          });
        }
        // return response;
      });
  }
};

// ===============================================================
// 驗證碼傳送

// 獲取驗證碼相關元素
let verifyModal = document.getElementById("verifyModal");
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
  fetch(verifyEmailUrl, {
    method: "POST",
    redirect: "manual",
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
          title: "驗證成功",
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
          title: "Oops! Something's wrong...",
          text: response.message,
        });
      }
    });
};
