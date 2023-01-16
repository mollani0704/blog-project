'use strict'
 
const deleteBtn = document.querySelector('.delete__btn');
const replySaveBtn = document.querySelector('.reply__save--button')
const replyContent = document.querySelector('.reply__content')
let id = document.querySelector('.writer__id').getAttribute("data-id");


replySaveBtn.addEventListener('click', () => {
	let data = {
		content : replyContent.value
	};
	
	$.ajax({
		type: "POST",
		url: `/api/board/${id}/reply`,
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(response) {
		alert("댓글 작성이 되었습니다.")
		location.href = `/board/${id}`
	})
	.fail(function(error) {
		console.log(error);
	})
})

deleteBtn.addEventListener('click', () => {
	
	console.log(typeof(id));
	
	$.ajax({
		type: "DELETE",
		url: "/api/board/"+id,
		dataType: "json"
	})
	.done((response) => {
		alert("글 삭제가 완료 되었습니다.")
		location.href = "/"
	})
	.fail((error) => {
		console.log(error);
	})
});