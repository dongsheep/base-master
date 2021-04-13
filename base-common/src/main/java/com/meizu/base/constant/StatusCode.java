package com.meizu.base.constant;

/**
 * 响应状态码
 * 
 * @author xiedongxiao
 *
 */

public enum StatusCode {

	SUCCESS("200", "操作成功"), 
	INTERNAL_ERROR("500", "服务器错误"),
	;

	private String code;
	private String msg;

	private StatusCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
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
