package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.config.auth.PrincipalDetails;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.Board;
import com.project.blog.model.Reply;
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
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable String id) {
		
		int result = boardService.delete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		
		int result = boardService.updateBoard(id, board);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		int result = boardService.writeReply(boardId, reply, principalDetails.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
}
