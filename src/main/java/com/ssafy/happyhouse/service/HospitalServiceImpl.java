package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HospitalDto;
import com.ssafy.happyhouse.mapper.HospitalMapper;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private SqlSession hdao;
	
	public List<HospitalDto> selectAll(){
		return hdao.getMapper(HospitalMapper.class).selectAll();
	}
	
}
