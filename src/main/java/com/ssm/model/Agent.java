package com.ssm.model;


public class Agent{
	int id;
	String agentGroupName;  //代理商名称
	String agentPName;   //代理人姓名
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
	public String getAgentGroupName() {
		return agentGroupName;
	}
	public void setAgentGroupName(String agentGroupName) {
		this.agentGroupName = agentGroupName;
	}
	public String getAgentPName() {
		return agentPName;
	}
	public void setAgentPName(String agentPName) {
		this.agentPName = agentPName;
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
