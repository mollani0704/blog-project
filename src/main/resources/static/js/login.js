'use strict'
 
 const loginBtn = document.querySelector('.login__btn')
 
 const username = document.querySelector("#username");
 const password = document.querySelector("#password");
 

 loginBtn.addEventListener('click', () => {
	
	let data = {
		username : username.value,
		password: password.value
	};
	
	$.ajax({
		type: "POST",
		url : "auth/api/user/login",
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	})
	.done(function(response) {
		alert("로그인이 되었습니다.")
		location.href = "/"
	})
	.fail(function(error) {
		console.log(error);
	})
	
});
