# 0518_pairproject

## Frontend -> https://github.com/yyerim0901/SSABANG_SSAFY_FinalProject_FrontEnd.git

## member
김예림, 박민상

# BoardController

## LIST

```java
@GetMapping
public ResponseEntity<List<BoardDto>> list(){
	return new ResponseEntity<>(bservice.list(),HttpStatus.OK);
}
```

***

## READ DETAIL

```java
@GetMapping("{bnum}")
public ResponseEntity<BoardDto> read(@PathVariable int bnum) {
	BoardDto dto = bservice.read(bnum);
	return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
}
```

***

## WRITE

```java
@PostMapping
public ResponseEntity<String> write(@RequestBody BoardDto board, @PathVariable("upfile") MultipartFile[] files) throws IllegalStateException, IOException, SQLException {
		if(files!= null && !files[0].isEmpty()) {
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
```

***

## UPDATE

```java
@PutMapping("{bnum}")
public ResponseEntity<String> update(@RequestBody BoardDto boardDto) {
	boolean result = bservice.update(boardDto);
	if(!result)
		return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	else 
		return new ResponseEntity<String>("success", HttpStatus.OK);
}
```

***

## DELETE

```java
@DeleteMapping("{bnum}")
public ResponseEntity<String> delete(@PathVariable int bnum) {
	int result = bservice.delete(bnum);
	if(result>0)
		return new ResponseEntity<String>("success",HttpStatus.OK);
	else
		return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
}
```

# CommentController

## LIST

```java
@GetMapping("{bnum}")
public ResponseEntity<List<CommentDto>> listCmd(@PathVariable int bnum){
	List<CommentDto> clist = cservice.getCmtList(bnum);
	
	if(clist == null)
		return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);
	else
		return new ResponseEntity<List<CommentDto>>(clist, HttpStatus.OK);
}
```

***

## WRITE

```java
@PostMapping("{bnum}")
public ResponseEntity<String> registCmd(@PathVariable int bnum, @RequestBody CommentDto dto){
	boolean result = cservice.writeComment(dto);
	if(!result)
		return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	else
		return new ResponseEntity<String>("success", HttpStatus.OK);
}
```

***

## DELETE

##### 추가된 기능
```java
@DeleteMapping("{cnum}")
public ResponseEntity<String> deleteCmd(@PathVariable int cnum){
	boolean result = cservice.deleteComment(cnum);
	if(!result)
		return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	else
		return new ResponseEntity<String>("success", HttpStatus.OK);
}
```

***

# HouseController

## LIST

```java
@GetMapping
public ResponseEntity <List<Map<String, String>>> allHouse() throws Exception {
	List<Map<String, String>> houselist = hservice.allHouse();

	if(houselist == null)
		return new ResponseEntity<List<Map<String,String>>>(HttpStatus.NO_CONTENT);
	else
		return new ResponseEntity<List<Map<String,String>>>(houselist, HttpStatus.OK);
}
```

***

## SEARCH

```java
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
```

