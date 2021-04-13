package com.meizu.base.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 界面与服务端传输的dto对象
 * 
 * @author xiedongxiao
 *
 */

public class UserDto implements Serializable {

	private static final long serialVersionUID = 4246025776638486338L;

	private Integer id;
	
	private String name;
	
	private Integer sex;
	
	private String sexText;
	
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexText() {
		return sexText;
	}

	public void setSexText(String sexText) {
		this.sexText = sexText;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", sex=" + sex + ", sexText=" + sexText + ", updateTime=" + updateTime + "]";
	}
	
}
