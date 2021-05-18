package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CommentDto;

@Mapper
public interface CommentMapper {
	public int insert(CommentDto commentDto);
	public List<CommentDto> selectList(int bnum);
	public int selectCommentCount(int bnum);
	public int delete(int cnum);
}
