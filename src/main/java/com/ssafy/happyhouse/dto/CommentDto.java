package com.ssafy.happyhouse.dto;

import java.util.Date;

public class CommentDto {
	private int cnum;
	private int bnum;
	private String cwriter;
	private String ccontent;
	private Date cregdate;
//////////////////////////////////////////////////////////////////////////////	
	public CommentDto(int cnum, int bnum, String cwriter, String ccontent, Date cregdate) {
		this.cnum = cnum;
		this.bnum = bnum;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
		this.cregdate = cregdate;
	}
	public CommentDto() {
	}
//////////////////////////////////////////////////////////////////////////////
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public Date getCregdate() {
		return cregdate;
	}
	public void setCregdate(Date cregdate) {
		this.cregdate = cregdate;
	}
	@Override
	public String toString() {
		return "CommentDto [cnum=" + cnum + ", bnum=" + bnum + ", cwriter=" + cwriter + ", ccontent=" + ccontent
				+ ", cregdate=" + cregdate + "]";
	}
	
}
