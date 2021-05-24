package com.ssafy.happyhouse.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private SqlSession sqlSession;
	
	public MemberDto read(String userid) {
		return sqlSession.getMapper(MemberMapper.class).read(userid);
	}
	
	public MemberDto login(Map<String, String> map) throws Exception{
		if(map.get("userid") == null || map.get("userpw")==null) return null;
		return sqlSession.getMapper(MemberMapper.class).login(map);
	}
	
	public boolean modify(MemberDto memberDto) {
		if(sqlSession.getMapper(MemberMapper.class).modify(memberDto)>0)
			return true;
		return false;
	}
	
	public int delete(String userid) {
		return sqlSession.getMapper(MemberMapper.class).delete(userid);
	}
	
	public void insert(MemberDto memberDto) {
		sqlSession.getMapper(MemberMapper.class).insert(memberDto);		
	}
	
}
