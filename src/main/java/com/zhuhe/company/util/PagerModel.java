package com.zhuhe.company.util;

public class PagerModel {
	private int pageIndex =1;
	private int pageLimit =10;
	private int rowStrat;
	private int rowCount;
	private boolean pageOn=false;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageLimit() {
		return pageLimit;
	}
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getRowStrat() {
		rowStrat = (pageIndex-1)*pageLimit;
		return rowStrat;
	}
	public void setRowStrat(int rowStrat) {
		this.rowStrat = rowStrat;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public boolean isPageOn() {
		return pageOn;
	}
	public void setPageOn(boolean pageOn) {
		this.pageOn = pageOn;
	}
	
	
	
}
