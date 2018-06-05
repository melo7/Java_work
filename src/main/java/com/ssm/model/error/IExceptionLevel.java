package com.ssm.model.error;



/**
 * 异常级别接口
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-22 下午1:36:54
 */
public interface IExceptionLevel {
	
	/**
	 * 获得异常对象的日志级别
	 * @return
	 */
	ExceptionLevel getLevel();
}
