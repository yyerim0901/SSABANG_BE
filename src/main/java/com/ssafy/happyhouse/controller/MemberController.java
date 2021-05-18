package com.ssafy.happyhouse.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.MemberService;

@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@GetMapping(value="/login")
	public String login() {
		return "/user/login_form";
	}
	
	@GetMapping(value="/join")
	public String join() {
		return "/user/join_form";
	}
	
	@PostMapping(value="/join")
	public ModelAndView join(MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		
		mService.insert(memberDto);
		mav.addObject("msg", "회원가입이 완료되었습니다.");
		mav.setViewName("index");
		
		return mav;
	}
	
	
	@PostMapping(value="/login")
	public ModelAndView login(@RequestParam Map<String, String> map, HttpSession session) {
		ModelAndView mav = new ModelAndView("index");
		try {
			MemberDto memberDto = mService.login(map);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);
				mav.addObject("msg", "로그인 성공");
			}else {
				mav.addObject("msg","아이디 또는 비밀번호 확인 후 로그인 해주세요");
			}
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("msg","로그인 중 문제가 발생하였습니다");
		}
		return mav;
	}

	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/index";
	}
	
	@GetMapping(value="/mypage")
	public String mypage() {
		return "/user/mypage";                                                                                   
	}
	
	
	@GetMapping(value="/modify")
	public String modify() {
		return "/user/modify_userinfo";
	}
	
	@PostMapping(value="/modify")
	public ModelAndView modify(MemberDto memberDto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if(mService.modify(memberDto)) {
			mav.addObject("msg", "회원 정보 수정이 완료되었습니다.");
			mav.setViewName("index");
		}else {
			mav.addObject("msg", "회원 정보를 수정하는 도중 오류가 났습니다.");
			mav.setViewName("index");
		}
		return mav;
	}
	
	
	@GetMapping("/delete")
	public ModelAndView delete(HttpSession session) {
		MemberDto userinfo = (MemberDto) session.getAttribute("userinfo");
		ModelAndView mav = new ModelAndView();
		
		String userid = userinfo.getUserid();
		
		mService.delete(userid);
		session.invalidate();
		mav.addObject("msg", "삭제를 완료하였습니다.");
		mav.setViewName("index");
		
		return mav;
	}
	
	
}
