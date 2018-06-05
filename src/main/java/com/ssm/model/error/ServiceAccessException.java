package com.ssm.model.error;

import org.apache.log4j.Logger;


/**
 * service异常类,暂时还是checked异常
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-22 下午1:40:59
 */
public class ServiceAccessException extends Exception implements IExceptionLogger, IExceptionLevel {

	private static final long serialVersionUID = 1L;
	/** 异常日志级别 ，默认为error级别 */
	private ExceptionLevel level = ExceptionLevel.ERROR;

	public ServiceAccessException() {
	}

	public ServiceAccessException(ExceptionLevel level) {
		this.level = level;
	}
	
	public ServiceAccessException(String message) {
		super(message);
	}

	public ServiceAccessException(Throwable cause) {
		super(cause);
	}
	
	public ServiceAccessException(ExceptionLevel level, String message) {
		super(message);
		this.level = level;
	}

	public ServiceAccessException(ExceptionLevel level, Throwable cause) {
		super(cause);
		this.level = level;
	}
	
	public ServiceAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceAccessException(ExceptionLevel level, String message, Throwable cause) {
		super(message, cause);
		this.level = level;
	}
	
	@Override
	public ExceptionLevel getLevel() {
		return level;
	}

	public void setLevel(ExceptionLevel level) {
		this.level = level;
	}

	@Override
	public void writeTo(Logger logger) {
		switch (level) {
		case WARN:
			logger.warn(getMessage(), getCause());
			break;
		case FATAL:
			logger.fatal(getMessage(), getCause());
			break;
		default:
			//默认为error级别
			logger.error(getMessage(), getCause());
		}
	}
	
}
