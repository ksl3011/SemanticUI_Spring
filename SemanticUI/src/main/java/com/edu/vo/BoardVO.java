package com.edu.vo;

import com.edu.board.DTO;

public class BoardVO extends DTO {

	private int postNum;
	private int total;
	private String userId;
	private String title;
	private String pw;
	private String RegDt;
	private String FileCode;
	private String contents;

	public BoardVO() {}

	public BoardVO(int postNum, int total, String userId, String title, String pw, String regDt, String fileCode,
			String contents) {
		super();
		this.postNum = postNum;
		this.total = total;
		this.userId = userId;
		this.title = title;
		this.pw = pw;
		RegDt = regDt;
		FileCode = fileCode;
		this.contents = contents;
	}


	public int getPostNum() {
		return postNum;
	}


	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getRegDt() {
		return RegDt;
	}


	public void setRegDt(String regDt) {
		RegDt = regDt;
	}


	public String getFileCode() {
		return FileCode;
	}


	public void setFileCode(String fileCode) {
		FileCode = fileCode;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	@Override
	public String toString() {
		return "BoardVO [postNum=" + postNum + ", total=" + total + ", userId=" + userId + ", title=" + title + ", pw="
				+ pw + ", RegDt=" + RegDt + ", FileCode=" + FileCode + ", contents=" + contents + "]";
	}

}
