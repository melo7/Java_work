package com.ssm.model;


public class Operator{
	int id;
	String operatorName;  //代理商名称
	String operatorPName;   //代理人姓名
	String createDate;      //创建时间
	String createBy;        //创建人
	String status;   //状态
	String adderss;     //地址
	String updateDate;     //更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorPName() {
		return operatorPName;
	}
	public void setOperatorPName(String operatorPName) {
		this.operatorPName = operatorPName;
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
	public String getStatus() {
		return status;
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
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
