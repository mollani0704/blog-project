package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm.html";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm.html";
	}
	
}
