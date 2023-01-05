'use strict'
 
const deleteBtn = document.querySelector('.delete__btn');


deleteBtn.addEventListener('click', () => {
	
	let id = document.querySelector('.writer__id').getAttribute("data-id");
	
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