# BlogProject
> JPA를 배운 내용들로 간단한 Blog를 만들어 보았습니다.
<br>

<img alt="blogProject" src="https://user-images.githubusercontent.com/72548305/236729216-85fec60f-123f-4aa9-9417-a779140719e9.JPG" width="550">

## 사용 기술 스택
<p>
 <img alt="html5" src="https://img.shields.io/badge/-html5-F44336?style=flat-square&logo=html5&logoColor=white" />
  <img alt="css" src="https://img.shields.io/badge/-css-03A9F4?style=flat-square&logo=css3&logoColor=white" />
  <img alt="javascript" src="https://img.shields.io/badge/-javascript-FFEB3B?style=flat-square&logo=javascript&logoColor=white" />
  <img alt="springboot" src="https://img.shields.io/badge/-springboot-13aa52?style=flat-square&logo=springboot&logoColor=white" />
  <img alt="thymeleaf" src="https://img.shields.io/badge/-thymeleaf-13aa52?style=flat-square&logo=thymeleaf&logoColor=white" />
  <img alt="Java" src="https://img.shields.io/badge/-java-007396?style=flat-square&logo=java&logoColor=white" />
  <img alt="Mysql" src="https://img.shields.io/badge/-mysql-007396?style=flat-square&logo=mysql&logoColor=white" />
  <img alt="Jpa" src="https://img.shields.io/badge/-jpa-827717?style=flat-square&logo=jpa&logoColor=white" />
</p>

## 구현기능
### Spring Security, Oauth를 이용해 로그인, 회원가입 구현.
<img alt="signup" src="https://user-images.githubusercontent.com/72548305/236732191-4648dfad-6096-4e48-854b-7a4187e378da.JPG" width="550">
<img alt="Login" src="https://user-images.githubusercontent.com/72548305/236732197-2667bd7e-7184-451c-9d31-83d8505e2efc.JPG" width="550">

```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder Encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().mvcMatchers("/static/**");

	};
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		  .antMatchers("/auth/**", "/", "/kakao/**")
		  .permitAll()
		  .anyRequest()
		  .authenticated()
		.and()
		  .formLogin()
		  .loginPage("/auth/loginForm")
		  .loginProcessingUrl("/auth/loginProc")
		  .defaultSuccessUrl("/");
		
		
		return http.build();
	};

}

```

### 글쓰기, 글수정
> 글쓰기 editor는 summernote를 이용했습니다.
<img alt="write" src="https://user-images.githubusercontent.com/72548305/236733523-e123c072-10f7-4557-9b14-f231db5843f5.JPG" width="550">
<img alt="writer" src="https://user-images.githubusercontent.com/72548305/236733858-2b99e3c0-b587-42af-a7ae-eda32ab08bb7.JPG" width="550">

### 댓글 기능
<img alt="reply" src="https://user-images.githubusercontent.com/72548305/236734407-63111d5e-57ce-4f79-acef-f09a63022a89.JPG" width="550">

## 배운점 & 아쉬운 점
### 배운점
- JPA에 대해서 공부하는 시간을 가질 수 있었다.
- Spring 버전이 업그레이드가 되어서 예전에 공부했던 버전과 Spring Security 구현코드가 좀 바뀌어서 새로운 구현코드 공부할 수 있었다.
<br>

### 아쉬운 점
- UI가 좀 많이 아쉬웠다. 최대한 꾸며본다고 했는데 쉽지않았고 CSS를 좀 더 공부할 필요성을 느꼈다.
- JPA에 대해 공부했지만 솔직히 제대로 이해했다곤 보기 어렵다. 기본기에 대한 이해를 좀 더 해야겠다고 다짐했습니다.
