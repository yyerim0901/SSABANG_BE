package com.ssafy.happyhouse.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.service.BoardService;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private BoardService cservice;
	
	@GetMapping("{bnum}")
	public ResponseEntity<List<CommentDto>> listCmd(@PathVariable int bnum){
		List<CommentDto> clist = cservice.getCmtList(bnum);
		
		if(clist == null)
			return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<CommentDto>>(clist, HttpStatus.OK);
	}
	
	@PostMapping("{bnum}")
	public ResponseEntity<String> registCmd(@PathVariable int bnum, @RequestBody CommentDto dto){
		boolean result = cservice.writeComment(dto);
		if(!result)
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	@DeleteMapping("{cnum}")
	public ResponseEntity<String> deleteCmd(@PathVariable int cnum){
		boolean result = cservice.deleteComment(cnum);
		if(!result)
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
