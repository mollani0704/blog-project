package com.project.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.blog.Repository.BoardRepository;
import com.project.blog.model.Board;
import com.project.blog.model.User;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public int save(Board board, User user) {
		
		board.setCount(0);
		board.setUser(user);
		
		boardRepository.save(board);
		
		return 1;
	};
	 
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
}
