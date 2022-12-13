package com.project.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//Delete문 만들기
	@DeleteMapping("/http/dummy/{id}")
	public String deleteUser(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);			
		} catch (Exception e) {
			return "해당 id의 user를 삭제할 수 없습니다";
		}
		
		return "해당 id의 user를 삭제 하였습니다.";
		
	}
	
	@Transactional
	@PutMapping("/http/dummy/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User requestUser) {
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 id의 user 정보를 업데이트 할 수 없습니다");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(user);
		
		return null;
	}
	
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
