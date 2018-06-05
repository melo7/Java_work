package com.ssm.model;

public class Personal {
	int id;
	String userName;  //用户名
	String idNumber;    //性别
	String phoneNumber;		//手机
	String adderss;	//邮箱
	String realName;
	String createDate;	//地址
	String clientLevel;	//加入时间
	String emall;  //邮箱
	String status;
	public int getId() {
		return id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public String getEmall() {
		return emall;
	}
	public void setEmall(String emall) {
		this.emall = emall;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getClientLevel() {
		return clientLevel;
	}
	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}
	
}
