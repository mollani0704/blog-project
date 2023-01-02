'use strict'

let index = {
	init : function() {
		$(".join__btn").on("click", () => {
			console.log('test')
		})
		
		$(".login__btn").on("click", () => {
			console.log('test2')
		})
	}
}

index.init();