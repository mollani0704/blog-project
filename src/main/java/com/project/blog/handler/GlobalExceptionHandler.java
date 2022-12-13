package com.project.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public String HandlerArgumentException(Exception e) {
		
		return "<h1>" + e.getMessage() + "</h1>";
		
	}
	
}
