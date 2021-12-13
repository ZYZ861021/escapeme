// 設定 url
let url = "api1/custo/login";

// ===========================================================

// login 傳送資料
let btnLoginSubmit = document.getElementById("login-submit");

btnLoginSubmit.onclick = function() {
	let email = document.getElementById("login-email").value;
	let password = document.getElementById("login-password").value;

	const loginData = {
		email: email,
		password: password,
	};

	fetch(url, {
		method: "POST",
		body: JSON.stringify(loginData),
		headers: new Headers({
			"Content-Type": "application/json",
		}),
	})
		.then((res) => res.json())
		.catch((error) => console.error("Error:", error))
		.then(function(response) {
			console.log("Success:", response);
			if (response.code == 200) {
				console.log("登入成功");
				location = response.data;
			}
			if (response.code == 150) {
				Swal.fire({
					icon: "error",
					title: "Oops! Failed to login...",
					text: response.message,
				});

			}
			if (response.code == 400) {
				Swal.fire({
					icon: "error",
					title: "Oops! Failed to login...",
					text: response.message,
				});
			}
		});
};
