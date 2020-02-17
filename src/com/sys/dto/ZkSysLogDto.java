package com.sys.dto;

import java.sql.Timestamp;

public class ZkSysLogDto {

	Integer id;
	Timestamp logDate;
	Integer userId;
	String operCode;
	String detail;
	
	public ZkSysLogDto() {}
	
	public ZkSysLogDto(String operCode, String detail) {
		this.operCode = operCode;
		this.detail = detail;
	}
	
	public ZkSysLogDto(Integer userId, String operCode, String detail) {
		this.userId = userId;
		this.operCode = operCode;
		this.detail = detail;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getLogDate() {
		return logDate;
	}
	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ZkSysLogDto [id=" + id + ", logDate=" + logDate + ", userId=" + userId + ", operCode=" + operCode
				+ ", detail=" + detail + "]";
	}
}
