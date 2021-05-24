package com.ssafy.happyhouse.dto;

public class MemberDto {
	
	private String userid;
	private String username;
	private String userpw;
	private String useremail;
	private String useraddress;

	public MemberDto() {
	}

	public MemberDto(String userid, String userpw, String username, String useremail, String useraddress) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.useremail = useremail;
		this.useraddress = useraddress;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getuseremail() {
		return useremail;
	}

	public void setuseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getuseraddress() {
		return useraddress;
	}

	public void setuseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
}
