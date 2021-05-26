package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.WordDto;
import com.ssafy.happyhouse.mapper.WordMapper;

@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	SqlSession sqlSession;
	
	public int getWordCnt(String word) {
		return sqlSession.getMapper(WordMapper.class).getWordCnt(word);
	}
	
	public List<WordDto> getWordList(Map<String, Object> map){
		return sqlSession.getMapper(WordMapper.class).getWordList(map);
	}

	public WordDto getDetailWord(int wnum) {
		return sqlSession.getMapper(WordMapper.class).getDetailWord(wnum);
	}
}
