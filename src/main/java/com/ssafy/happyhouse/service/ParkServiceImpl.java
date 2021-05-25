package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.ParkDto;
import com.ssafy.happyhouse.mapper.ParkMapper;

@Service
public class ParkServiceImpl implements ParkService {
	
	@Autowired
	private SqlSession pdao;
	
	public List<ParkDto> selectAll(){
		return pdao.getMapper(ParkMapper.class).selectAll();
	}
	
}
