package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HospitalDto;
import com.ssafy.happyhouse.service.HospitalService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hospital")
public class HospitalController {
	
	@Autowired
	private HospitalService hservice;
	
	@GetMapping("/list")
	public ResponseEntity<List<HospitalDto>> selectAll(){
		List<HospitalDto> hList = hservice.selectAll();
		
		if(hList == null || hList.size() == 0)
			return new ResponseEntity<List<HospitalDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<HospitalDto>>(hList, HttpStatus.OK);
		
	}
	
}
