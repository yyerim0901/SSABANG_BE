package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, String>> allHouse() throws Exception {
		return sqlSession.getMapper(HouseMapper.class).allHouse();
	}

	@Override
	public List<Map<String, String>> guHouse(String guname) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).guHouse(guname);
	}

	@Override
	public List<Map<String, String>> dongHouse(String dongname) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).dongHouse(dongname);
	}

	@Override
	public List<Map<String, String>> aptnameHouse(String aptname) throws Exception {
		 return sqlSession.getMapper(HouseMapper.class).aptHouse(aptname);
	}

}
