package com.sys.dto;

import java.sql.Timestamp;

public class SysLogDto {

	Integer id;
	Timestamp logDate;
	String remoteIp;
	int loginUserId;
	String url;
	
	public SysLogDto() {}
	
	public SysLogDto(String remoteIp, String url) {
		this.remoteIp = remoteIp;
		this.url = url;
	}
	public SysLogDto(int loginUserId, String remoteIp, String url) {
		this.loginUserId = loginUserId;
		this.remoteIp = remoteIp;
		this.url = url;
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
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "SysLogDto [id=" + id + ", logDate=" + logDate + ", remoteIp=" + remoteIp + ", loginUserId=" + loginUserId
				+ ", url=" + url + "]";
	}
}
