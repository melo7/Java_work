package com.ssm.model;


public class Log{
	int id;
	String sort;  //商店名称
	String title;   //地址
	String createDate;      //创建时间
	String status;        //状态
	String introdu;
	String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIntrodu() {
		return introdu;
	}
	public void setIntrodu(String introdu) {
		this.introdu = introdu;
	}
	
	
}
