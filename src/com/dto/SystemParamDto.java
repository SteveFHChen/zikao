package com.dto;

import java.util.List;

public class SystemParamDto {
	private String oper;
	private StudentDto loginUser;
	private List<SystemParamItemDto> systemParams;

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public StudentDto getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(StudentDto loginUser) {
		this.loginUser = loginUser;
	}

	public List<SystemParamItemDto> getSystemParams() {
		return systemParams;
	}

	public void setSystemParams(List<SystemParamItemDto> systemParams) {
		this.systemParams = systemParams;
	}
	
}
