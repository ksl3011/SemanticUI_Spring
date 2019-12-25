package com.edu.vo;

import com.edu.board.DTO;

public class FileVO extends DTO {
	
	private String path;
	private int postNum;
	
	public FileVO() {}

	public FileVO(String path, int postNum) {
		super();
		this.path = path;
		this.postNum = postNum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	@Override
	public String toString() {
		return "FileVO [path=" + path + ", postNum=" + postNum + "]";
	}
	
}
