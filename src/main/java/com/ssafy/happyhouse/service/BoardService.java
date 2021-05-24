package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.CommentDto;

public interface BoardService {

	public Map<String, Object> makePage(int page);
	public List<BoardDto> list(Map<String, Object> map);
	public void write(BoardDto boardDto) throws SQLException;
	public BoardDto read(int bnum);
	public boolean update(BoardDto boardDto);
	public String check(int bnum);
	public int delete(int bnum);
	public List<CommentDto> getCmtList(int bnum);
	public boolean writeComment(CommentDto cmtDto);
	public boolean deleteComment(int cnum);
	public int getTotBoardList();
}
