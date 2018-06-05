package com.ssm.model;

import java.sql.Date;

import org.codehaus.jackson.map.ser.std.SerializableSerializer;

public class Cooperator{
	int id;
	String cooCompanyName;  //
	String cooName;   //
	String cooPNumber;      //
	String city;        //
	String createDate;   //创建时间
	String createBy;     //创建人
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCooCompanyName() {
		return cooCompanyName;
	}
	public void setCooCompanyName(String cooCompanyName) {
		this.cooCompanyName = cooCompanyName;
	}
	public String getCooName() {
		return cooName;
	}
	public void setCooName(String cooName) {
		this.cooName = cooName;
	}
	public String getCooPNumber() {
		return cooPNumber;
	}
	public void setCooPNumber(String cooPNumber) {
		this.cooPNumber = cooPNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}
