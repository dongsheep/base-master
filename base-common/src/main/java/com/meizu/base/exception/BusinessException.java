package com.meizu.base.exception;

import com.meizu.base.constant.StatusCode;

/**
 * 自定义异常类
 * 
 * @author xiedongxiao
 *
 */

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -9068786667519810234L;

	private String code;

	private String msg;

	public BusinessException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public BusinessException(StatusCode obj) {
		this.code = obj.getCode();
		this.msg = obj.getMsg();
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

}
