package com.ssafy.happyhouse.dto;

import java.util.Date;
import java.util.List;


public class BoardDto {
	private int bnum;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bregdate;
	private int viewnum;
	private List<FileInfoDto> fileInfos;
	////////////////////////////////////////////////////////////////////////////

	public BoardDto() {
	}
	public BoardDto(int bnum, String btitle, String bcontent, String bwriter, Date bregdate, int viewnum,
			List<FileInfoDto> fileInfos) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bregdate = bregdate;
		this.viewnum = viewnum;
		this.fileInfos = fileInfos;
	}
	/////////////////////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "BoardDto [bnum=" + bnum + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter
				+ ", bregdate=" + bregdate + ", viewnum=" + viewnum + ", fileInfos=" + fileInfos + "]";
	}

	/////////////////////////////////////////////////////////////////////////////
	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public Date getBregdate() {
		return bregdate;
	}

	public void setBregdate(Date bregdate) {
		this.bregdate = bregdate;
	}

	public int getViewnum() {
		return viewnum;
	}

	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}

}
