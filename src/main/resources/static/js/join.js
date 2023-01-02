'use strict'
 
 const joinBtn = document.querySelector('.join__btn');
 
 const username = document.querySelector("#username");
 const email = document.querySelector("#email");
 const password = document.querySelector("#password");
 
 // 자 문제점 발견... 물론 쉽게 js 파일 두개 만들어서 하면 된다 하지만 그건 비효율적
 // 어떻게 하면 js 파일 하나로 joinForm, loginForm에서 사용할 수 있는 지 다시 한 번 생각해보자.
 // 당연히 loginForm에는 joinBtn이 없으니까 에러가 발생하는 게 당연하다.
 // 제이쿼리를 사용해야하는데 그래도 오류가 안생겨나는가??? 한 번 실험해봐야겠다. 
 // 물어본 결과 안된다는 걸로 판명 됨.
 
 joinBtn.addEventListener('click', () => {
	let data = {
		username : username.value,
		email : email.value,
		password: password.value
	};
	
	$.ajax({
		type: "POST",
		url : "/auth/join",
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	})
	.done(function(response) {
		alert("회원가입이 완료 되었습니다.")
		location.href = "/"
	})
	.fail(function(error) {
		console.log(error);
	})
	
});
