package com.ssm.controller;

public class CommonErrorCode {
	
	public enum LuoErrorCode {

	    NULL_OBJ("LUO001","sql error"),
	    ERROR_ADD_USER("LUO002","error from request url"),
	    UNKNOWN_ERROR("LUO999","system timeout,try again later....");

	    private String value;
	    private String desc;

	    private LuoErrorCode(String value, String desc) {
	        this.setValue(value);
	        this.setDesc(desc);
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.value + "]" + this.desc;
	    }
	}
}
