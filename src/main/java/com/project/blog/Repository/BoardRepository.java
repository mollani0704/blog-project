package com.project.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
