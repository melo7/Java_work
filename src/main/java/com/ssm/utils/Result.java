package com.ssm.utils;


public class Result {
	/**
	 * 表示返回成功或失败，true为成功
	 */
	private Boolean success = true;

	private String msg;

	private Object obj;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
