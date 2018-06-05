package com.ssm.model;

import java.sql.Date;

import org.codehaus.jackson.map.ser.std.SerializableSerializer;

public class RefuelRecharge{
	int id;
	String refuel_name;  //签到名称
	String refuel_phone;   //创建时间    
	String refuel_cardNbr;  //签到名称
	String singTime;   //创建时间    
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRefuel_name() {
		return refuel_name;
	}
	public void setRefuel_name(String refuel_name) {
		this.refuel_name = refuel_name;
	}
	public String getRefuel_phone() {
		return refuel_phone;
	}
	public void setRefuel_phone(String refuel_phone) {
		this.refuel_phone = refuel_phone;
	}
	public String getRefuel_cardNbr() {
		return refuel_cardNbr;
	}
	public void setRefuel_cardNbr(String refuel_cardNbr) {
		this.refuel_cardNbr = refuel_cardNbr;
	}
	public String getSingTime() {
		return singTime;
	}
	public void setSingTime(String singTime) {
		this.singTime = singTime;
	}
	
	
}
