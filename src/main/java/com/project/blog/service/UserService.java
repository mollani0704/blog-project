package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.blog.Repository.UserRepository;
import com.project.blog.model.RoleType;
import com.project.blog.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional	
	public int save(User user) {
		
		String originPassword = user.getPassword();
		String encoderPassword = encoder.encode(originPassword);
		
		user.setRole(RoleType.USER);
		user.setPassword(encoderPassword);
		
		try {	
			
			userRepository.save(user);
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService 회원가입 : " + e.getMessage());
		}
		return -1;
	}
	
	/*
	@Transactional
	public User login(User user) {
		
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
	}
	*/
	
}
