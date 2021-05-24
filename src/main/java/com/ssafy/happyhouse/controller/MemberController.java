package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.MemberService;

@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService mService;

//	@GetMapping
//	public void getCookie(HttpServletRequest request) {
//		Cookie[] getCookie = request.getCookies();
//		if(getCookie != null) {
//			for(int i=0; i<getCookie.length; i++) {
//				Cookie c = getCookie[i];
//				String name = c.getName();//쿠키이름
//				String value = c.getValue();//쿠키값
//			}
//		}
//	}
//
//	@GetMapping
//	public void deleteAllCookie(HttpServletRequest request,HttpServletResponse response){
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null){
//			for(int i=0; i< cookies.length; i++){
//				cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
//				response.addCookie(cookies[i]); // 응답 헤더에 추가
//			}
//		}
//	}


	//	@GetMapping
	//	public void createCookie(HttpServletResponse response) {
	//		Cookie setCookie = new Cookie("login_cookie", "value");
	//		setCookie.setMaxAge(60*60*24);
	//		setCookie.setSecure(true);
	//		response.addCookie(setCookie);
	//	}
	
	@GetMapping
	public ResponseEntity<MemberDto> read(@RequestParam String userid){
		MemberDto member = mService.read(userid);
		
		if(member == null)
			return new ResponseEntity<MemberDto>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map, HttpServletResponse response) throws Exception{
		System.out.println("스프링부트안녕");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userid", userid);
//		map.put("userpw", userpw);
		
		MemberDto member = mService.login(map);
		if(member == null) {
			return new ResponseEntity<String>("fail",HttpStatus.OK);
		}else {
//			return new ResponseEntity<String>("success",HttpStatus.OK);
			//use cookie
			Cookie setCookie = new Cookie("login_cookie", member.getUserid());
			setCookie.setMaxAge(60*60*24);
			setCookie.setSecure(true);
			setCookie.setPath("/");
			response.addCookie(setCookie); //response에 쿠키를 붙여서 어떻게 전달하는건지 모르겠어서
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("login_cookie" ,"platform=mobile; Max-Age=604800; Path=/; Secure; HttpOnly");
			return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
//			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
			//여기서는 new하니까 오류가 나네.. 왜지..new 안해도 되는건가...
		}
	}
	
	//	@GetMapping ->로그아웃에 사용
	//	public void deleteCookie(HttpServletResponse response){
	//	   Cookie kc = new Cookie("choiceCookieName", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
	//	   kc.setMaxAge(0); // 유효시간을 0으로 설정
	//	   response.addCookie(kc); // 응답 헤더에 추가해서 없어지도록 함
	//	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response){
		Cookie cookie = new Cookie("login_cookie", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
		cookie.setMaxAge(0); // 유효시간을 0으로 설정
		response.addCookie(cookie); // 응답 헤더에 추가해서 없어지도록 함
		System.out.println("로그아웃 완료!");
		return new ResponseEntity<String>(HttpStatus.OK);
	}


	@PostMapping //회원가입
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		mService.insert(memberDto);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}


	//	//회원가입
	//	@PostMapping
	//	public ResponseEntity<TokenResponse> login(@RequestParam Map<String, String> map) {
	//		String token = mService.createToken(map);
	//        return ResponseEntity.ok().body(new TokenResponse(token, "bearer"));
	//	}

//	@GetMapping(value="/logout")
//	public String logout(HttpSession session) {
//		//쿠키삭제
//		session.invalidate();
//		return "/index";
//	}

	@PutMapping //회원정보수정
	public ResponseEntity<String> modify(@RequestBody MemberDto memberDto) {
		if(mService.modify(memberDto))
			return new ResponseEntity<String>("success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}

	//@CookieValue(value="login_cookie")
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam String userid, HttpServletResponse response) {
		//파라미터에서 사용된 cookievalue를 통해 사용자 아이디를 쿠키에서 불러오기
		mService.delete(userid);
		//삭제하고 쿠키 삭제
		Cookie cookie = new Cookie("login_cookie", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
		cookie.setMaxAge(0); // 유효시간을 0으로 설정
		response.addCookie(cookie); // 응답 헤더에 추가해서 없어지도록 함
		return new ResponseEntity<String>(HttpStatus.OK);

	}


}
