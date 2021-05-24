package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private SqlSession sqlSession;
	
	public List<HouseDealDto> selectAllHousedeal(){
		return sqlSession.getMapper(HouseMapper.class).selectAllHousedeal();
	}
	
	public List<HouseDealDto> selectGuHousedeal(String guname){
		return sqlSession.getMapper(HouseMapper.class).selectGuHousedeal(guname);
	}
}
