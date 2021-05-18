package com.ssafy.happyhouse.service;

import java.util.Map;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService {
	
	public MemberDto login(Map<String, String> map) throws Exception;
	public boolean modify(MemberDto memberDto);
	public int delete(String userid);
	public void insert(MemberDto memberDto);
}
