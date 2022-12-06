package com.project.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		
		Member member = Member.builder().id(12).name("Moon").password(12341234).email("mollani@hanmail.com").build();
		System.out.println(member.getId());
		System.out.println(member.getName());
		System.out.println(member.getPassword());
		System.out.println(member.getEmail());
		
		
		return "lombok 테스트 중";
	}
	
	// 인터넷 브라우저는 무조건 get방식만 가능하다.
	
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id, @RequestParam String name, @RequestParam int password, @RequestParam String email) {
		return id + " , " + name + " , " + password + " , " + email; 
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member member) {
		return "post 방식" + member.getId() + "," + member.getName() + "," + member.getPassword() + "," + member.getEmail();	
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member member) {
		return "put 방식" + member.getId() + "," + member.getName() + "," + member.getPassword() + "," + member.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 방식";
	}
}
