package com.ssafy.happyhouse.dto;

public class ParkDto {
	private String parkname, guname, address, lat, lng;

	public ParkDto(String parkname, String guname, String address, String lat, String lng) {
		this.parkname = parkname;
		this.guname = guname;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}

	public ParkDto() {
	}

	public String getParkname() {
		return parkname;
	}

	public void setParkname(String parkname) {
		this.parkname = parkname;
	}

	public String getGuname() {
		return guname;
	}

	public void setGuname(String guname) {
		this.guname = guname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
	
	
	
}
