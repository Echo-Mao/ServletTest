package com.smg.pojo;

import java.util.Date;

public class Search {
	
	private String sTitle;
	private Date sStartDate;
	private Date sEndDate;
	public Search() {
		super();
	}
	public Search(String sTitle, Date sStartDate, Date sEndDate) {
		super();
		this.sTitle = sTitle;
		this.sStartDate = sStartDate;
		this.sEndDate = sEndDate;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public Date getsStartDate() {
		return sStartDate;
	}
	public void setsStartDate(Date sStartDate) {
		this.sStartDate = sStartDate;
	}
	public Date getsEndDate() {
		return sEndDate;
	}
	public void setsEndDate(Date sEndDate) {
		this.sEndDate = sEndDate;
	}
}
