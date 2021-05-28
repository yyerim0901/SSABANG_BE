package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.WordDto;
import com.ssafy.happyhouse.service.WordServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/word")
public class WordController {
	
	@Autowired
	private WordServiceImpl wService;
	
	public static final int COUNT_PER_PAGE = 15; //한 페이지당 보여주는 글 갯수
	
	@GetMapping(value="/detail/{wnum}")
	public ResponseEntity<WordDto> searchDetailWord(@PathVariable int wnum){
		WordDto word = wService.getDetailWord(wnum);
		if(word != null)
			return new ResponseEntity<WordDto>(word, HttpStatus.OK);
		return new ResponseEntity<WordDto>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value={"","/","/{word}","/{word}/{pagenum}"})
	public ResponseEntity<Map<String, Object>> searchAllWord(@PathVariable(required=false) String word, @PathVariable(required = false) Integer pagenum){
		if(word==null) word = "";
		int page=0;
		if(pagenum==null) page = 1;
		else page = pagenum;
		
		int totWordCnt = wService.getWordCnt(word); //전체 word개수
		int totpage = totWordCnt/COUNT_PER_PAGE; //만들어야하는 전체 페이지 수
		if(totWordCnt%COUNT_PER_PAGE>0) totpage++; //페이지들 채우고 꽉 차지 않는 한 페이지가 만들어질 때 페이지 추가
		
		int startpage = (((page-1)/5)*5)+1; //시작페이지는 ((입력받은 페이지)/5*5)+1
		int currpage = page; //현재 입력받은 페이지
		int endpage = startpage+4 > totpage? totpage:startpage+4; //시작페이지에 따른 끝 페이지
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", (currpage-1)*COUNT_PER_PAGE);
		map.put("word", word);
		map.put("countperpage", COUNT_PER_PAGE);
		List<WordDto> words = wService.getWordList(map);
		
		Map<String, Object> result = new HashMap<>();
		result.put("data", words);
		result.put("startpage", startpage);
		result.put("currpage", currpage);
		result.put("endpage", endpage);
		result.put("totpage", totpage);
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
