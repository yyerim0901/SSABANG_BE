# 0518_pairproject

## member
김예림, 박민상


## BackEnd
- [x] spring legacy project를 spring boot로 변경하기 
>- [x] BoardController 변경
>- [x] CommentController 변경
>- [x] HouseController 변경

## FrontEnd
- [x] Vue (vuex)로 게시판 구현
>- [x] create
>- [x] read
>- [x] update
>- [x] delete
- [ ] 게시판 댓글 구현


##### 메인페이지
![image](/uploads/305b1407c1b1162cc8d88d5c99189f67/image.png)
```java
<script>
// @ is an alias to /src

export default {
};
</script>

```

##### 게시판
![image](/uploads/dc7c53add1293b8c94abd3b7eadfbdb4/image.png)
```java
<script>
export default {
    name : 'BoardList',
    data() {
        return {
            bnum:'',
        }
    },
    created() {
        this.$store.dispatch("getBoard");
    },
    methods: {
        moveForm(){
            this.$router.push("/addForm");
        },
        detailview(bnum){
            console.log(bnum); 
            this.$store.dispatch("selectOne", bnum);
            this.$router.push("/detailBoard");
        }
    },
    computed:{
        boardlist(){
            return this.$store.state.boards;
        }
    }
}
</script>
```


##### 글작성페이지
![image](/uploads/1e5ba58f6e81444869341063ae0072bf/image.png)
```java
<script>
export default {
    name:'BoardForm',
    data() {
        return {
            board:{
                btitle:'',
                bwriter:'',
                bcontent:'',
            }
        }
    },
    methods: {
        checkHandler() {
            this.createHandler();
        },
        createHandler(){
            this.$router.push("/board");
        }
    },
}
</script>
```

##### 글 상세페이지
![image](/uploads/9fb62c3885b2c343464c672323c51ceb/image.png)
```java
<script>
export default {

    computed:{
        boardlistitem(){
            return this.$store.state.board;
        }
    },
    methods: {
        updateboard(){
            this.$store.dispatch("updateBoardForm", this.boardlistitem);
            this.$router.push("/updateboardform");
        },
        deleteboard(){
            this.$store.dispatch("deleteBoard", this.boardlistitem.bnum);
            this.$router.push("/board");
        },
        movelist(){
            this.$router.push("/board");
        },
    },    
}
</script>
```

##### 글 수정페이지
![image](/uploads/ad0448716e78daacfbf59dc1a2066510/image.png)
```java
<script>
export default {
    name:'UpdateBoardForm',
    data() {
        return {
            board:{
                bnum:'',
                btitle: '',
                bwriter: '',
                bcontent: '',
            }
        }
    },
    created() {
        this.board.bnum = this.boardlistitem.bnum;
        this.board.btitle = this.boardlistitem.btitle;
        this.board.bwriter = this.boardlistitem.bwriter;
        this.board.bcontent = this.boardlistitem.bcontent;
    },
    computed:{
        boardlistitem(){
            return this.$store.state.board;
        }
    },
    methods: {
        movedetailboard(){
            this.$store.dispatch("updateBoard", this.board);
            this.$router.push("/detailboard");
        },
    },
}
</script>
```

##### Store index.js 파일
```java
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    houses: [],
    boards: [],
    comments: [],
    board: {},
  },
  getters: {
    boardList(state) {
      return state.boards;
    },
  },
  mutations: {
    ADD_BOARD(state, boardItem) {
      state.boards.push(boardItem);
    },
    GET_BOARD(state, payload) {
      state.boards = payload;
    },
    SELECT_ONE(state, payload) {
      state.board = payload;
    },
  },
  actions: {
    addBoard({ commit }, boardItem) {
      http
        .post("/board", boardItem)
        .then(() => {
          commit("ADD_BOARD", boardItem);
        })
        .catch(() => {
          alert("등록 중 에러가 발생했습니다.");
        });
    },
    getBoard({ commit }) {
      http.get("/board").then((resp) => {
        commit("GET_BOARD", resp.data);
      });
    },
    selectOne({ commit }, bnum) {
      http.get(`/board/${bnum}`).then((resp) => {
        console.log("action bnum :" + bnum);
        commit("SELECT_ONE", resp.data);
      });
    },
    updateBoardForm({ commit }, boardItem) {
      commit("SELECT_ONE", boardItem);
    },
    updateBoard({ commit }, boardItem) {
      console.log(boardItem);
      http.put(`/board/${boardItem.bnum}`, boardItem).then(() => {
        commit("SELECT_ONE", boardItem);
      });
    },
    deleteBoard({ commit }, bnum) {
      http.delete(`/board/${bnum}`).then(() => {
        http.get("/board").then((resp) => {
          commit("GET_BOARD", resp.data);
        });
      });
    },
  },
});


```


# BoardController 변경

## LIST

##### 이전
```java
@GetMapping("/list")
public ModelAndView list(@RequestParam(defaultValue="1") int page) {
	ModelAndView mav = new ModelAndView("board/board_list");
	mav.addObject("pageInfo", bservice.makePage(page));
	return mav;
}
```
##### 이후
```java
@GetMapping
public ResponseEntity<List<BoardDto>> list(){
	return new ResponseEntity<>(bservice.list(),HttpStatus.OK);
}
```

***

## READ DETAIL

##### 이전
```java
@GetMapping("/read/{bnum}")
public ModelAndView read(@PathVariable("bnum") int bnum) {
	ModelAndView mav = new ModelAndView("board/board_read");
	mav.addObject("boardDto", bservice.read(bnum));
	return mav;
}
```
##### 이후
```java
@GetMapping("{bnum}")
public ResponseEntity<BoardDto> read(@PathVariable int bnum) {
	BoardDto dto = bservice.read(bnum);
	return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
}
```

***

## WRITE

##### 이전 (jsp page이동 및 처리)
```java
@GetMapping("/write")
public ModelAndView write(HttpSession session) {
	MemberDto loginInfo = (MemberDto) session.getAttribute("userinfo");
	ModelAndView mav = new ModelAndView();
	if(loginInfo != null) {
		mav.setViewName("board/board_write");
		return mav;
	}else {
		mav.setViewName("index");
		mav.addObject("msg", "로그인이 필요합니다.");
		return mav;
	}
}
	
@PostMapping("/write")
public ModelAndView write(BoardDto board, @RequestParam("upfile") MultipartFile[] files, HttpSession session) throws IllegalStateException, IOException, SQLException {
	
	ModelAndView mav = new ModelAndView();
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
	mav.setViewName("board/board_list");
		
	return mav;
}
```
##### 이후
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

##### 이전(jsp page 이동 및 처리)
```java
@GetMapping("/update/{bnum}")
public ModelAndView update(HttpSession session,@PathVariable("bnum")int bnum) {
	MemberDto loginInfo = (MemberDto) session.getAttribute("userinfo");
	ModelAndView mav = new ModelAndView();
	String id = bservice.check(bnum);
	
	if(loginInfo != null && loginInfo.getUserid().equals(id)) {
		mav.addObject("boardDto", bservice.read(bnum));
		mav.setViewName("board/board_update");
	}else if(loginInfo != null) {
		mav.setViewName("board/board_read");
		mav.addObject("msg", "수정 권한이 없습니다.");
	}else {
		mav.setViewName("board/board_read");
		mav.addObject("msg", "로그인이 필요합니다.");
	}
	return mav;
}

@PostMapping("/update/{bnum}")
public ModelAndView updateValue(BoardDto boardDto) {
	ModelAndView mav = new ModelAndView();
	
	if(bservice.update(boardDto)) {
		mav.addObject("msg", "수정이 완료되었습니다.");
		mav.setViewName("board/board_read");
	}else {
		mav.addObject("msg", "수정하는 도중 오류가 났습니다.");
		mav.setViewName("board/board_read");
	}
	return mav;
}
```
##### 이후
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

##### 이전
```java
@PostMapping("/delete/{bnum}")
public ModelAndView delete(HttpSession session,@PathVariable("bnum")int bnum) {
	MemberDto loginInfo = (MemberDto) session.getAttribute("userinfo");
	ModelAndView mav = new ModelAndView();
	String id = bservice.check(bnum);
	
	if(loginInfo != null && loginInfo.getUserid().equals(id)) {
		if(bservice.delete(bnum)>0)
			mav.addObject("msg", "삭제가 완료되었습니다.");
		else
			mav.addObject("msg", "삭제하는 도중 오류가 났습니다.");
		mav.setViewName("board/result");
	}else if(loginInfo != null) {
		mav.setViewName("board/board_read");
		mav.addObject("msg", "삭제 권한이 없습니다.");
	}else {
		mav.setViewName("board/board_read");
		mav.addObject("msg", "로그인이 필요합니다.");
	}
	return mav;
}
```
##### 이후
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

# CommentController 변경

## LIST

##### 이전
```java
@GetMapping(value="/ajax", produces = "application/json;charset=utf8")
@ResponseBody
public String ajax(int bnum){
	Gson gs = new Gson();
	return gs.toJson((bservice.getCmtList(bnum)));
}
```
##### 이후
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

##### 이전
```java
@PostMapping("/ajax")
@ResponseBody
public String ajax(@RequestBody CommentDto cmtDto) {
	if(bservice.writeComment(cmtDto))
		return "success";
	else 
		return "fail";
}
```
##### 이후
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

# HouseController 변경

## LIST

##### 이전
```java
@GetMapping("/list")
public ModelAndView allHouse() {
	ModelAndView mav = new ModelAndView("house/list");
	try {
		mav.addObject("houseList", hService.allHouse());
	}catch(Exception e) {
		e.printStackTrace();
		mav.setViewName("index");
	}
	return mav;
}
```
##### 이후
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

##### 이전
```java
@PostMapping("/search")
public ModelAndView searchHouse(@RequestParam Map<String, String> map) {
	String type = map.get("type");
	String keyword = map.get("keyword");
	ModelAndView mav = new ModelAndView("house/list");
	try {
		if(type.equals("dong")) {
			mav.addObject("houseList", hService.dongHouse(keyword));
		}else {
			mav.addObject("houseList", hService.aptnameHouse(keyword));
		}
	}catch(Exception e) {
		e.printStackTrace();
		mav.setViewName("index");
	}
	return mav;
}
```
##### 이후
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

