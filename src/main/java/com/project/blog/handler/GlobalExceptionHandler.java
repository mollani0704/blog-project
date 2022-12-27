package com.project.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.dto.ResponseDto;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> HandlerArgumentException(Exception e) {
		
		return new ResponseDto<String>(HttpStatus.OK.value(), e.getMessage());
		
	}
	
}
