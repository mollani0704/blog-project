package com.project.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.Repository.UserRepository;
import com.project.blog.model.RoleType;
import com.project.blog.model.User;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

@RestController
public class DummyTestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/http/dummy/users")
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/http/dummy/user")
	public List<User> getPage(@PageableDefault(size = 1, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		Page<User> users = userRepository.findAll(pageable);
		
		List<User> data = users.getContent();
		
		return data;
	}
	
	@GetMapping("/http/dummy/{id}")
	public User getUser(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
					
				return new IllegalArgumentException("해당 id의 user 정보를 찾을 수 없습니다");
			}
		});
		
		return user;
	}
	
	@PostMapping("/http/dummy")
	public String insertUser(User user) {
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다";
		
	}
	
}