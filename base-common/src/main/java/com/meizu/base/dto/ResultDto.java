package com.meizu.base.dto;

import java.io.Serializable;

/**
 * 返回结果-实体类
 * 
 * @author xiedongxiao
 *
 * @param <T>
 */

public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = -7934107979283229111L;
	
	private String code;
	private String msg;
	private T data;

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
