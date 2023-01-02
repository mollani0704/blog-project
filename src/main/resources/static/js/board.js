'use strict'
 
const writeBtn = document.querySelector('.write__btn');
const title = document.querySelector('#title')
const content = document.querySelector('.summernote')

writeBtn.addEventListener('click', () => {
	let data = {
		title : title.value,
		content : content.value
	};
	
	$.ajax({
		type: "POST",
		url: "/api/board",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done((response) => {
		alert("회원가입이 완료 되었습니다.")
		location.href = "/"
	})
	.fail((error) => {
		console.log(error);
	})
	
	console.log(data);
});