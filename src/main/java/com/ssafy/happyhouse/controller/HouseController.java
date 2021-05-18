package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.service.HouseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService hservice;


	@GetMapping
	public ResponseEntity <List<Map<String, String>>> allHouse() throws Exception {
		List<Map<String, String>> houselist = hservice.allHouse();

		if(houselist == null)
			return new ResponseEntity<List<Map<String,String>>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Map<String,String>>>(houselist, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Map<String, String>>> searchHouse(@PathVariable Map<String, String> map) throws Exception {
		String type = map.get("type");
		String keyword = map.get("keyword");

		List<Map<String, String>> result;
		
		if(type.equals("dong")) {
			result = hservice.dongHouse(keyword);
		}else {
			result = hservice.aptnameHouse(keyword);
		}
		if(result == null)
			return new ResponseEntity<List<Map<String,String>>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Map<String,String>>>(result, HttpStatus.OK);
	}
}
