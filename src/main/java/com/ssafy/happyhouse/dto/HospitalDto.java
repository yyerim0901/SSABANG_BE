package com.ssafy.happyhouse.dto;

public class HospitalDto {
	String hnum, htel, haddress, hname, hx, hy, hpart, hdesc;

	public HospitalDto(String hnum, String htel, String haddress, String hname, String hx, String hy, String hpart,
			String hdesc) {
		this.hnum = hnum;
		this.htel = htel;
		this.haddress = haddress;
		this.hname = hname;
		this.hx = hx;
		this.hy = hy;
		this.hpart = hpart;
		this.hdesc = hdesc;
	}

	public HospitalDto() {
	}

	public String getHnum() {
		return hnum;
	}

	public void setHnum(String hnum) {
		this.hnum = hnum;
	}

	public String getHtel() {
		return htel;
	}

	public void setHtel(String htel) {
		this.htel = htel;
	}

	public String getHaddress() {
		return haddress;
	}

	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public String getHx() {
		return hx;
	}

	public void setHx(String hx) {
		this.hx = hx;
	}

	public String getHy() {
		return hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public String getHpart() {
		return hpart;
	}

	public void setHpart(String hpart) {
		this.hpart = hpart;
	}

	public String getHdesc() {
		return hdesc;
	}

	public void setHdesc(String hdesc) {
		this.hdesc = hdesc;
	}
	
	
}
