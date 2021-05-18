package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.google.gson.Gson;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.FileInfoDto;
import com.ssafy.happyhouse.service.BoardService;


@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bservice;

	@Autowired
	ServletContext servletContext;

//	@GetMapping
//	public ResponseEntity<Map<String, Object>> list(@PathVariable int page) {
//		Map<String, Object> result = bservice.makePage(page);
//
//		if(result == null || result.get("boardlist") == null || ((List)result.get("boardList")).size()==0 || result.size()==0) {
//			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
//		}else 
//			return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
//	}
	
	@GetMapping
	public ResponseEntity<List<BoardDto>> list(){
		return new ResponseEntity<>(bservice.list(),HttpStatus.OK);
	}

	@GetMapping("{bnum}")
	public ResponseEntity<BoardDto> read(@PathVariable int bnum) {
		BoardDto dto = bservice.read(bnum);
		return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> write(@RequestBody BoardDto board, @PathVariable("upfile") MultipartFile[] files) throws IllegalStateException, IOException, SQLException {

		if(!files[0].isEmpty()) {
			//			String realPath = servletContext.getRealPath("/upload");
			String realPath = servletContext.getRealPath("/resources/img");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = realPath + File.separator + today;

			File folder = new File(saveFolder);
			if(!folder.exists())
				folder.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for(MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
				if(!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);

					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			board.setFileInfos(fileInfos);
		}

		bservice.write(board);
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}

	@PutMapping("{bnum}")
	public ResponseEntity<String> update(@RequestBody BoardDto boardDto) {
		boolean result = bservice.update(boardDto);
		if(!result)
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
		else 
			return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@DeleteMapping("{bnum}")
	public ResponseEntity<String> delete(@PathVariable int bnum) {
		int result = bservice.delete(bnum);
		if(result>0)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}


//	@GetMapping("/download")
//	public ResponseEntity<T> download(@PathVariable String sfolder, @PathVariable String ofile,
//			@PathVariable String sfile) {
//
//		Map<String, Object> fileInfo = new HashMap<String, Object>();
//		fileInfo.put("sfolder",sfolder);
//		fileInfo.put("ofile",ofile);
//		fileInfo.put("sfile",sfile);
//
//		//FileDownLoadView로 옮겨가서....해야되는데...
//
//	}

}
