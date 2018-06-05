package com.ssm.common;


/**
 * 封装响应结果模型
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-21 下午2:17:43
 */
public class ResponseResult<T> extends BaseModel {
	private static final long serialVersionUID = -2830751366652316323L;
	
	public static final String SUCCESS = "0";
	public static final String TIMEOUT = "1";
	public static final String EXPIRED = "2";
	public static final String FAILED  = "9";

	/** 请求是否成功，成功为true，否则为true */
	private String code = SUCCESS;
	/** 响应消息，请求失败必须设置响应消息 */
	private String msg = "请求成功。";
	/** 响应数据 */
	private T data;

	public ResponseResult() {
	}

	/**
	 * @param data  响应数据      
	 */
	public ResponseResult(T data) {
		this.data = data;
	}

	/**
	 * @param success  请求是否成功
	 * @param msg      响应消息
	 */
	public ResponseResult(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * @param success  请求是否成功
	 * @param msg      响应消息
	 * @param data     响应数据
	 */
	public ResponseResult(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
