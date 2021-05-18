package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseMapper {

	public List<Map<String, String>> allHouse() throws SQLException;
	public List<Map<String, String>> guHouse(String guname) throws SQLException;
	public List<Map<String, String>> dongHouse(String dongname) throws SQLException;
	public List<Map<String, String>> aptHouse(String aptname) throws SQLException;
}
