package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private SqlSession bdao;

	@Autowired

	private static final int COUNT_PER_PAGE=10;
	
	public Map<String, Object> makePage(int page){
		// 총 게시글 갯수 디비에서 조회함.
		int totalCount = bdao.getMapper(BoardMapper.class).selectTotalCount();

		// 총 페이지수 계산
		int totalPage = totalCount/COUNT_PER_PAGE;
		if(totalCount%COUNT_PER_PAGE>0)
			totalPage++;

		// 화면 하단의 시작 페이지
		int startPage = (page-1)/10*10+1;

		int endPage = startPage+9;
		if(endPage>totalPage)
			endPage = totalPage;

		// 현재 페이지 게시글 조회
		int startRow = (page-1)*COUNT_PER_PAGE; // limit 의 시작행 번호 계산.
		List<BoardDto> bList = bdao.getMapper(BoardMapper.class).selectList(startRow, COUNT_PER_PAGE);

		Map<String, Object> pageInfo = new HashMap<>();

		pageInfo.put("startPage", startPage);
		pageInfo.put("endPage", endPage);
		pageInfo.put("totalPage", totalPage);
		pageInfo.put("curPage", page);
		pageInfo.put("bList", bList);

		return pageInfo;
	}
	
	public List<BoardDto> list(Map<String, Object> map){
		return bdao.getMapper(BoardMapper.class).selectAll(map);
	}
	
	@Transactional
	public void write(BoardDto boardDto) throws SQLException {
		BoardMapper boardMapper = bdao.getMapper(BoardMapper.class);
		boardMapper.insert(boardDto);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public BoardDto read(int bnum) {
		bdao.getMapper(BoardMapper.class).addview(bnum);
		return bdao.getMapper(BoardMapper.class).selectOne(bnum);
	}

	public boolean update(BoardDto boardDto) {
		if(bdao.getMapper(BoardMapper.class).update(boardDto)>0)
			return true;
		return false;
	}

	public String check(int bnum) {
		return bdao.getMapper(BoardMapper.class).check(bnum);
	}
	
	public int delete(int bnum) {
		return bdao.getMapper(BoardMapper.class).delete(bnum);
	}

	public int getTotBoardList() {
		return bdao.getMapper(BoardMapper.class).getTotBoardList();
	}
}
