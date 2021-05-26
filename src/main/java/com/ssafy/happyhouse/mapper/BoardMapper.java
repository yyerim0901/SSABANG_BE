package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public void insert(BoardDto boardDto);
	public BoardDto selectOne(int bnum);
	public List<BoardDto> selectAll(Map<String, Object> map);
	public List<BoardDto> selectList(@Param("startRow")int startRow,@Param("count") int count);
	public int selectTotalCount();
	public int update(BoardDto boardDto);
	public String check(int bnum);
	public int delete(int bnum);
	public void addview(int bnum);
	
	public List<BoardDto> selectListByKey(@Param("searchCondition") String searchCondition, @Param("key") String key, @Param("startRow")int startRow,@Param("count") int count);
	public int selectTotalCountByKey (@Param("startRow")int startRow,@Param("count") int count);
	public int getTotBoardList();
}
