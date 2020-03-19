package com.cg.fms.bean;

public class LocalDateTime {
	
	private String date;
	private String hour;
	public LocalDateTime( String date,String hour) {
		super();
		this.date = date;
		this.hour = hour;
	}
	public LocalDateTime() {
		super();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	

}
