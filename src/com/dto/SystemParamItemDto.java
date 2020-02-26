package com.dto;

public class SystemParamItemDto {

	private String paramCode;
	private String paramValue;
	private String paramDesc;
	
	public SystemParamItemDto() {}
	
	public SystemParamItemDto(String paramCode, String paramValue, String paramDesc) {
		this.paramCode = paramCode;
		this.paramValue = paramValue;
		this.paramDesc = paramDesc;
	}
	
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	@Override
	public String toString() {
		return "SystemParamItemDto [paramCode=" + paramCode + ", paramValue=" + paramValue + ", paramDesc=" + paramDesc
				+ "]";
	}
	
}
