'use strict'
 
const updateBtn = document.querySelector('.update__btn');

const title = document.querySelector('#title')
const content = document.querySelector('.summernote')

let id = document.querySelector(".user__id").value;

updateBtn.addEventListener('click', () => {
	
	let data = {
		title : title.value,
		content : content.value
	};
	
	console.log(id)
	console.log(typeof(data.title));
	
	$.ajax({
		type: "PUT",
		url: "/api/board/"+id,
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done((response) => {
		alert("글 수정이 완료 되었습니다.")
		location.href = "/"
	})
	.fail((error) => {
		console.log(error);
	})
});