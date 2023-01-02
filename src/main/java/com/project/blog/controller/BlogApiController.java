package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.config.auth.PrincipalDetails;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.Board;
import com.project.blog.model.User;
import com.project.blog.service.BoardService;

@RestController
public class BlogApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		User user = principalDetails.getUser();
		
		int result = boardService.save(board, user);
		
		System.out.println("글쓰기가 완료 되었습니다.");
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
		
	}
	
}
