package com.ssm.model.error;



/**
 * 应用访问异常（ajax请求异常之后，系统自动封装json返回）
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-22 下午1:40:59
 */
public class ApplicationAccessException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ApplicationAccessException() {
	}

	public ApplicationAccessException(ExceptionLevel level, String message,
			Throwable cause) {
		super(level, message, cause);
	}

	public ApplicationAccessException(ExceptionLevel level, String message) {
		super(level, message);
	}

	public ApplicationAccessException(ExceptionLevel level, Throwable cause) {
		super(level, cause);
	}

	public ApplicationAccessException(ExceptionLevel level) {
		super(level);
	}

	public ApplicationAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationAccessException(String message) {
		super(message);
	}

	public ApplicationAccessException(Throwable cause) {
		super(cause);
	}
	
}
