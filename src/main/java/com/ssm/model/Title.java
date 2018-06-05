package com.ssm.model;


public class Title{
	int id;
	String evaluate_title;  //文章标题
	String evaluate_head;   //文章二级
	String evaluate_selectoption;      //类型选项
	String editor;        //文章内容
	String viewNbr;     //浏览量
	String createDate;     //创建时间
	String upNbr;      //点赞量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEvaluate_title() {
		return evaluate_title;
	}
	public void setEvaluate_title(String evaluate_title) {
		this.evaluate_title = evaluate_title;
	}
	public String getEvaluate_head() {
		return evaluate_head;
	}
	public void setEvaluate_head(String evaluate_head) {
		this.evaluate_head = evaluate_head;
	}
	public String getEvaluate_selectoption() {
		return evaluate_selectoption;
	}
	public void setEvaluate_selectoption(String evaluate_selectoption) {
		this.evaluate_selectoption = evaluate_selectoption;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getViewNbr() {
		return viewNbr;
	}
	public void setViewNbr(String viewNbr) {
		this.viewNbr = viewNbr;
	}
	public String getUpNbr() {
		return upNbr;
	}
	public void setUpNbr(String upNbr) {
		this.upNbr = upNbr;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
