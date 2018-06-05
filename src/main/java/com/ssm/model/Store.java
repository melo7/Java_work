package com.ssm.model;


public class Store{
	int id;
	String storeName;  //商店名称
	String adderss;   //地址
	String createDate;      //创建时间
	String status;        //状态
	
	public int getId() {
		return id;
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
	
	public String getStatus() {
		return status;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	
}
