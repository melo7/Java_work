package com.ssm.model;


public class UserStatistics{
	int id;
	String singup;  
	String upNbr;   
	String createDate;
	public int getId() {
		return id;
	}
	public String getSingup() {
		return singup;
	}
	public void setSingup(String singup) {
		this.singup = singup;
	}
	public String getUpNbr() {
		return upNbr;
	}
	public void setUpNbr(String upNbr) {
		this.upNbr = upNbr;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
