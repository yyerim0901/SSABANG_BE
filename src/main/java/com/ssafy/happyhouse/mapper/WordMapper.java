package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.WordDto;

@Mapper
public interface WordMapper {

	public int getWordCnt(String word);
	public List<WordDto> getWordList(Map<String, Object> map);
	public WordDto getDetailWord(int wnum);
}
