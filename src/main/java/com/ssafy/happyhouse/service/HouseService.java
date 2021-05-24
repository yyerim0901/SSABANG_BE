package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;

public interface HouseService {
	
	public List<HouseDealDto> selectAllHousedeal();
	public List<HouseDealDto> selectGuHousedeal(String guname);
}
