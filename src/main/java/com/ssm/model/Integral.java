package com.ssm.model;

import java.sql.Date;

import org.codehaus.jackson.map.ser.std.SerializableSerializer;

public class Integral{
	int id;
	String integralName;  //积分名称
	String integralAmount;   //积分金额
	String startTime;      //包开始时间
	String endTime;        //包结束时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIntegralName() {
		return integralName;
	}
	public void setIntegralName(String integralName) {
		this.integralName = integralName;
	}
	public String getIntegralAmount() {
		return integralAmount;
	}
	public void setIntegralAmount(String integralAmount) {
		this.integralAmount = integralAmount;
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
	
}
