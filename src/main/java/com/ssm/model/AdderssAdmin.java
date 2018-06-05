package com.ssm.model;

import java.sql.Date;

import org.codehaus.jackson.map.ser.std.SerializableSerializer;

public class AdderssAdmin{
	int id;
	String expressName;  //
	String expressPhone;   //
	String adderss;      //
	String expressNature;        //
	String adderssDetail;   //创建时间
	String createTime;
	String createBy;     //创建人
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressPhone() {
		return expressPhone;
	}
	public void setExpressPhone(String expressPhone) {
		this.expressPhone = expressPhone;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public String getExpressNature() {
		return expressNature;
	}
	public void setExpressNature(String expressNature) {
		this.expressNature = expressNature;
	}
	public String getAdderssDetail() {
		return adderssDetail;
	}
	public void setAdderssDetail(String adderssDetail) {
		this.adderssDetail = adderssDetail;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}
