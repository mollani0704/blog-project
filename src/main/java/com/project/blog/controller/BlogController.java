package com.project.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.blog.config.auth.PrincipalDetails;

@Controller
public class BlogController {
	
	@GetMapping({"", "/"})
	public String index() {
	
		return "index.html";
		
	}
	
	@GetMapping("/board/writeForm")
	public String board() {
		return "board.html";
	}
}
