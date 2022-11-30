package com.project.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogTestController {

	@GetMapping("/blog/test")
	public String test() {
		
		return "<h1> hello world </h1>";
		
	}
		
}
