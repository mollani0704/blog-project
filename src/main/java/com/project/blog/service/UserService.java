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
	
	@Transactional
	public int update(User user, int id) {
		User rawUserData = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 id의 회원정보를 찾을 수 없습니다.");
		});
		
		rawUserData.setEmail(user.getEmail());
		rawUserData.setPassword(encoder.encode(user.getPassword()));
		
		return 1;
	}
	
	/*
	@Transactional
	public User login(User user) {
		
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
	}
	*/
	
}
