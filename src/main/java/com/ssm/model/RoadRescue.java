package com.ssm.model;

import java.sql.Date;

import org.codehaus.jackson.map.ser.std.SerializableSerializer;

public class RoadRescue{
	int id;
	String callName;  //服务包名称
	String rescueTime;   //服务包id
	String rescueAdderss;      //包开始时间
	String rescueName;        //包结束时间
	String rescueResult;   //创建时间
	String remark;     //创建人
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCallName() {
		return callName;
	}
	public void setCallName(String callName) {
		this.callName = callName;
	}
	public String getRescueTime() {
		return rescueTime;
	}
	public void setRescueTime(String rescueTime) {
		this.rescueTime = rescueTime;
	}
	public String getRescueAdderss() {
		return rescueAdderss;
	}
	public void setRescueAdderss(String rescueAdderss) {
		this.rescueAdderss = rescueAdderss;
	}
	public String getRescueName() {
		return rescueName;
	}
	public void setRescueName(String rescueName) {
		this.rescueName = rescueName;
	}
	public String getRescueResult() {
		return rescueResult;
	}
	public void setRescueResult(String rescueResult) {
		this.rescueResult = rescueResult;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
