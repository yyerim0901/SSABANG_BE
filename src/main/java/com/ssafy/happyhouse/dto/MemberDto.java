package com.ssafy.happyhouse.dto;

public class MemberDto {
	
	private String userid;
	private String username;
	private String userpw;
	private String useremail;
	private String useraddress;
	
	

	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String userid, String username, String userpw, String useremail, String useraddress) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpw = userpw;
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

	public String getUserpwd() {
		return userpw;
	}

	public void setUserpwd(String userpwd) {
		this.userpw = userpwd;
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
