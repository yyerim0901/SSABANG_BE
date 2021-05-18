package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

public interface HouseService {

	//모든 거래내역 반환
	public List<Map<String, String>> allHouse() throws Exception;
	
	//구 이름으로 검색
	public List<Map<String, String>> guHouse(String guname) throws Exception;
	
	//동 이름으로 검색
	public List<Map<String, String>> dongHouse(String dongname) throws Exception;
	
	//아파트 이름으로 검색
	public List<Map<String, String>> aptnameHouse(String aptname) throws Exception;
}
