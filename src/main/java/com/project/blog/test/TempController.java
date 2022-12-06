package com.project.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
	
	@GetMapping("/temp/test")
	public String tempTest() {
		return "temp.html";
	}
	
}
