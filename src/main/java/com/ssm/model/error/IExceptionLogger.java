package com.ssm.model.error;

import org.apache.log4j.Logger;


/**
 * 异常日志记录器接口 异常日志记录器接口 
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-22 下午1:15:46
 */
public interface IExceptionLogger {
	
	/**
	 * 将异常信息写入到日志记录器中
	 * @param logger
	 * @throws
	 */
	void writeTo(Logger logger);
}
