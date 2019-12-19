package com.edu.vo;

public class BoardVO {
	
	private int no;
	private String title;
	private String contents;
	private String regDt;
	private String id;
	private float star;
	
	public BoardVO() {}

	public BoardVO(int no, String title, String contents, String regDt, String id, float star) {
		super();
		this.no = no;
		this.title = title;
		this.contents = contents;
		this.regDt = regDt;
		this.id = id;
		this.star = star;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getStar() {
		return star;
	}

	public void setStar(float star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt + ", id=" + id
				+ ", star=" + star + "]";
	}

}
