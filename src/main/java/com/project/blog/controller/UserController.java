package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "joinForm.html";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "loginForm.html";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user_update";
	}
	
}
