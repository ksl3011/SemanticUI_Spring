package com.edu.vo;

import com.edu.board.DTO;

public class FileVO extends DTO {
	
	private String regDt;
	private int postNum;
	private String oName;
	private String rName;
	
	public FileVO() {}

	public FileVO(String regDt, int postNum, String oName, String rName) {
		super();
		this.regDt = regDt;
		this.postNum = postNum;
		this.oName = oName;
		this.rName = rName;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	@Override
	public String toString() {
		return "FileVO [regDt=" + regDt + ", postNum=" + postNum + ", oName=" + oName + ", rName=" + rName + "]";
	}

}
