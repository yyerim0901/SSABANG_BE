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
import com.ssafy.happyhouse.service.WordService;

@RestController
@CrossOrigin("*")
@RequestMapping("/word")
public class WordController {
	
	@Autowired
	private WordService wService;
	
	public static final int COUNT_PER_PAGE = 15;
	
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
		
		int totWordCnt = wService.getWordCnt(word);
		int totpage = totWordCnt/COUNT_PER_PAGE;
		if(totWordCnt%COUNT_PER_PAGE>0) totpage++;
		
		int startpage = (((page-1)/10)*10)+1;
		int currpage = page;
		int endpage = startpage+9 > totpage? totpage:startpage+9;
		
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
