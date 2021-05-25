package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.ParkDto;

@Mapper
public interface ParkMapper {
	
	public List<ParkDto> selectAll();

}
