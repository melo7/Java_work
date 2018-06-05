package com.ssm.model;

import java.sql.Date;

import org.codehaus.jackson.map.ser.std.SerializableSerializer;

public class ServicePackage{
	int id;
	String packageName;  //服务包名称
	String packageNbr;   //服务包id
	String startTime;      //包开始时间
	String endTime;        //包结束时间
	String createDate;   //创建时间
	String createBy;     //创建人
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageNbr() {
		return packageNbr;
	}
	public void setPackageNbr(String packageNbr) {
		this.packageNbr = packageNbr;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
