package com.edu.vo;

import com.edu.board.DTO;

public class SearchVO extends DTO {
	
	private String searchWord_a;
	private String searchWord_b;
	private String searchWord_c;
	private int pageNum;
	private String pageSize;
	private String searchDiv;
	
	public SearchVO() {}

	public SearchVO(String searchWord_a, String searchWord_b, String searchWord_c, int pageNum, String pageSize,
			String searchDiv) {
		super();
		this.searchWord_a = searchWord_a;
		this.searchWord_b = searchWord_b;
		this.searchWord_c = searchWord_c;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.searchDiv = searchDiv;
	}

	public String getSearchWord_a() {
		return searchWord_a;
	}

	public void setSearchWord_a(String searchWord_a) {
		this.searchWord_a = searchWord_a;
	}

	public String getSearchWord_b() {
		return searchWord_b;
	}

	public void setSearchWord_b(String searchWord_b) {
		this.searchWord_b = searchWord_b;
	}

	public String getSearchWord_c() {
		return searchWord_c;
	}

	public void setSearchWord_c(String searchWord_c) {
		this.searchWord_c = searchWord_c;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	@Override
	public String toString() {
		return "SearchVO [searchWord_a=" + searchWord_a + ", searchWord_b=" + searchWord_b + ", searchWord_c="
				+ searchWord_c + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", searchDiv=" + searchDiv + "]";
	}
	
}
