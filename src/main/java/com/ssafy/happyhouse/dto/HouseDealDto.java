package com.ssafy.happyhouse.dto;

public class HouseDealDto {
	private String apt_CD, apt_NM, apt_load_NM, gu_NM, dong_NM, call_number, build_company;
	private int area_under60, area_under85, area_under135, parking_size;
	private double lat, lng;
	public String getApt_CD() {
		return apt_CD;
	}
	public void setApt_CD(String apt_CD) {
		this.apt_CD = apt_CD;
	}
	public String getApt_NM() {
		return apt_NM;
	}
	public void setApt_NM(String apt_NM) {
		this.apt_NM = apt_NM;
	}
	public String getApt_load_NM() {
		return apt_load_NM;
	}
	public void setApt_load_NM(String apt_load_NM) {
		this.apt_load_NM = apt_load_NM;
	}
	public String getGu_NM() {
		return gu_NM;
	}
	public void setGu_NM(String gu_NM) {
		this.gu_NM = gu_NM;
	}
	public String getDong_NM() {
		return dong_NM;
	}
	public void setDong_NM(String dong_NM) {
		this.dong_NM = dong_NM;
	}
	public String getCall_number() {
		return call_number;
	}
	public void setCall_number(String call_number) {
		this.call_number = call_number;
	}
	public String getBuild_company() {
		return build_company;
	}
	public void setBuild_company(String build_company) {
		this.build_company = build_company;
	}
	public int getArea_under60() {
		return area_under60;
	}
	public void setArea_under60(int area_under60) {
		this.area_under60 = area_under60;
	}
	public int getArea_under85() {
		return area_under85;
	}
	public void setArea_under85(int area_under85) {
		this.area_under85 = area_under85;
	}
	public int getArea_under135() {
		return area_under135;
	}
	public void setArea_under135(int area_under135) {
		this.area_under135 = area_under135;
	}
	public int getParking_size() {
		return parking_size;
	}
	public void setParking_size(int parking_size) {
		this.parking_size = parking_size;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
}
