package com.ssm.model.error;



/**
 * 通过html错误页面展示的异常信息
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-22 下午1:42:19
 */
public class HtmlErrorPageException extends BaseException implements IExceptionErrorPage{
	private static final long serialVersionUID = 1L;

	public HtmlErrorPageException() {
	}

	public HtmlErrorPageException(ExceptionLevel level, String message,
			Throwable cause) {
		super(level, message, cause);
	}

	public HtmlErrorPageException(ExceptionLevel level, String message) {
		super(level, message);
	}

	public HtmlErrorPageException(ExceptionLevel level, Throwable cause) {
		super(level, cause);
	}

	public HtmlErrorPageException(ExceptionLevel level) {
		super(level);
	}

	public HtmlErrorPageException(String message, Throwable cause) {
		super(message, cause);
	}

	public HtmlErrorPageException(String message) {
		super(message);
	}

	public HtmlErrorPageException(Throwable cause) {
		super(cause);
	}

}
