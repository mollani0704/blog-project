package com.project.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.model.KakaoProfile;
import com.project.blog.model.OauthToken;
import com.project.blog.model.User;
import com.project.blog.service.UserService;

@Controller
public class UserController {

	@Value("${oauth.key}")
	private String oauthKey;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "joinForm.html";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "loginForm.html";
	}

	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user_update";
	}

	@GetMapping("/kakao/auth/callback")
	public String kakaoLogin(String code) {

		RestTemplate rt = new RestTemplate();

		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "0d152628d273dc617740a77b9c9dd5af");
		params.add("redirect_uri", "http://localhost:8000/kakao/auth/callback");
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);

		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		OauthToken oauthToken = null;

		try {
			oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders header2 = new HttpHeaders();
		header2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		header2.add("Authorization", "Bearer " + oauthToken.getAccess_token());

		HttpEntity<MultiValueMap<String, String>> kakaoAccessRequest2 = new HttpEntity<>(header2);

		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoAccessRequest2, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		System.out.println("카카오 아이디(번호)" + kakaoProfile.getId());
		System.out.println("카카오 이메일" + kakaoProfile.getKakao_account().getEmail());

		// UUID garbagePassword = UUID.randomUUID();

		User kakaoUser = new User().builder()
				.username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId()).password(oauthKey)
				.email(kakaoProfile.getKakao_account().getEmail()).oauth("kakao").build();

		User originUser = userService.findUser(kakaoUser.getUsername());

		if (originUser.getUsername() == null) {
			userService.save(kakaoUser);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), oauthKey));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// 회원찾기 메소드를 userService에서 만들어야하며
		// 이후에 만약 같은 것이 있다면 그냥 로그인 하게 하고
		// 없으며 회원가입 하게끔 로직을 짜야함ㄴ

		return "redirect:/";
	}

}
