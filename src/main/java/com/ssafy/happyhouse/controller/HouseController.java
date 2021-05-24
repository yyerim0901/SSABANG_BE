package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.service.HouseService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService hservice;
	
	// 모든 거래내역 조회
	@ApiOperation(value="모든 거래내역 조회 ")
	@GetMapping("/")
	public ResponseEntity<List<HouseDealDto>> selectAllHousedeal(){
		System.out.println("select all housedeal");
		List<HouseDealDto> housedeallist = hservice.selectAllHousedeal();
		System.out.println(housedeallist.size());
		if(housedeallist == null || housedeallist.size() == 0)
			return new ResponseEntity<List<HouseDealDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<HouseDealDto>>(housedeallist, HttpStatus.OK);
	}
	
	//검색 지역 거래내역 조회
	@ApiOperation(value="지역 이름으로 거래내역 조회 ")
	@GetMapping("/zone/{zone}")
	public ResponseEntity<List<HouseDealDto>> selectGuHousedeal(@PathVariable("zone") String zone){
		System.out.println("지역 이름으로 조회 : " +zone);
		List<HouseDealDto> housedeallist = hservice.selectGuHousedeal(zone);
		if(housedeallist == null || housedeallist.size()==0)
			return new ResponseEntity<List<HouseDealDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<HouseDealDto>>(housedeallist, HttpStatus.OK);
	}
}
