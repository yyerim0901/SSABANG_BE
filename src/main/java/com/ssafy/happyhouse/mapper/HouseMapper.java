package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDealDto;

@Mapper
public interface HouseMapper {
	
	public List<HouseDealDto> selectAllHousedeal();
	public List<HouseDealDto> selectGuHousedeal(String guname);
}
