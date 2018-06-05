package com.ssm.model;


public class ConsumptionStatistics{
	int id;
	String integral;  
	String service;   
	String platform;
	String line;  
	String serviceq;   
	String djq;   
	String createDate;
	public int getId() {
		return id;
	}
	
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getServiceq() {
		return serviceq;
	}

	public void setServiceq(String serviceq) {
		this.serviceq = serviceq;
	}

	public String getDjq() {
		return djq;
	}

	public void setDjq(String djq) {
		this.djq = djq;
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
