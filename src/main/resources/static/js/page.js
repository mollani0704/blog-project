'use strict'

const navbar = document.querySelector('.navbar');
const a = document.querySelector('.logo>a');
const navItem = document.querySelectorAll('.nav__item');

document.addEventListener('scroll', () => {
	if(window.scrollY > navbar.getBoundingClientRect().height) {
		navbar.classList.add('navbar__white');
		a.style.color = "black";
		for(let i = 0; i < navItem.length; i++) {
			navItem[i].classList.add('nav__item--black')
		}
		
		
	} else {
		navbar.classList.remove('navbar__white');
		a.style.color = "white";
		for(let i = 0; i < navItem.length; i++) {
			navItem[i].classList.remove('nav__item--black')
		}
	}
})
