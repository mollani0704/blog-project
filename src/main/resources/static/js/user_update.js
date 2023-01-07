'use strict'
 
 const updateBtn = document.querySelector('.user__update--btn');
 
 const email = document.querySelector("#email");
 const password = document.querySelector("#password");
 
 let id = document.querySelector(".user__id").value
 
 
 updateBtn.addEventListener('click', () => {
	let data = {
		email : email.value,
		password: password.value
	};
	
	$.ajax({
		type: "PUT",
		url : "/user/"+id,
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	})
	.done(function(response) {
		alert("회원수정이 완료 되었습니다.")
		location.href = "/"
	})
	.fail(function(error) {
		console.log(error);
	})
	
});
