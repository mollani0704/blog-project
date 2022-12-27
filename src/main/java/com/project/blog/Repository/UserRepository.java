package com.project.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	/*
	User findByUsernameAndPassword(String username, String password);
	
	*/
	//@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2")
	//User login(String username, String password);
	
	
}
