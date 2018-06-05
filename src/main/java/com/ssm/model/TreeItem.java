package com.ssm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeItem {
	private Long id;
	private String text;
	private String state;
	private boolean checked;
	private Map<String, Object> attributes;
	private List<TreeItem> children;
	
	public void setUrl(String url){
		if(attributes==null){
			attributes = new HashMap<String, Object>();
		}
		attributes.put("url", url);
	}
	
	public TreeItem() {
		state = "open";
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<TreeItem> getChildren() {
		return children;
	}
	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}
	
}
