package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.ParkDto;
import com.ssafy.happyhouse.service.ParkService;

@RestController
@CrossOrigin("*")
@RequestMapping("/park")
public class ParkController {
	
	@Autowired
	private ParkService pservice;
	
	@GetMapping("/list")
	public ResponseEntity<List<ParkDto>> selectAll(){
		List<ParkDto> parkList = pservice.selectAll();
		
		if(parkList == null || parkList.size() == 0)
			return new ResponseEntity<List<ParkDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<ParkDto>>(parkList, HttpStatus.OK);
	}
}
