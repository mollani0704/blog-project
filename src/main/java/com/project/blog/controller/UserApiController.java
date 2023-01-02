package com.project.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.dto.ResponseDto;
import com.project.blog.model.RoleType;
import com.project.blog.model.User;
import com.project.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/join")
	public ResponseDto<Integer> save(@RequestBody User user) {

		int result = userService.save(user);
		
		System.out.println("회원가입이 완료 되었습니다.");
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	};
	
	/*
	@PostMapping("auth/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		
		//principal -> 접근주체라는 용어
		User principal = userService.login(user);
		
		session.setAttribute("principal", principal);
		
		System.out.println("로그인이 되었습니다.");
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		
	}
	*/
	
}
