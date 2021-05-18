package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	public MemberDto login(Map<String, String> map) throws SQLException;
	public void insert(MemberDto memberDto);
	public int modify(MemberDto memberDto);
	public int delete(@Param("userid") String userid);
	
}
