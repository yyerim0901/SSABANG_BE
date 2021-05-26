package com.ssafy.happyhouse.controller;

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
		
		MemberDto member = mService.login(map);
		if(member == null) {
			return new ResponseEntity<String>("fail",HttpStatus.OK);
		}else {
			return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response){
		//이거 지우면 vue코드 대거 수정해야할거같아서 냅두기
		return new ResponseEntity<String>(HttpStatus.OK);
	}


	@PostMapping //회원가입
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		mService.insert(memberDto);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}


	@PutMapping //회원정보수정
	public ResponseEntity<String> modify(@RequestBody MemberDto memberDto) {
		if(mService.modify(memberDto))
			return new ResponseEntity<String>("success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam String userid, HttpServletResponse response) {
		mService.delete(userid);
		Cookie cookie = new Cookie("login_cookie", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return new ResponseEntity<String>(HttpStatus.OK);

	}


}
