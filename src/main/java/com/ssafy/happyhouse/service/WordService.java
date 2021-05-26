package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.WordDto;

public interface WordService {
	
	public int getWordCnt(String word);
	
	public List<WordDto> getWordList(Map<String, Object> map);

	public WordDto getDetailWord(int wnum);
}
