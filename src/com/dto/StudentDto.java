package com.dto;

public class StudentDto {
	private Integer stuId;
	
//	@JsonProperty("userName")
	private String stuNo;
	private String name;
	private String pwd;
	private String roleCode;
	private String photoPath;
	
	//For change password
	private String newPwd1;
	private String newPwd2;
	private String oper;
	
	//For query class status
	private String startDateStr;
	private String endDateStr;
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	@Override
	public String toString() {
		return "StudentDto [stuId=" + stuId + ", stuNo=" + stuNo + ", name=" + name + ", roleCode=" + roleCode
				+ ", photoPath=" + photoPath + ", oper=" + oper + "]";
	}

	public String getNewPwd1() {
		return newPwd1;
	}
	public void setNewPwd1(String newPwd1) {
		this.newPwd1 = newPwd1;
	}
	public String getNewPwd2() {
		return newPwd2;
	}
	public void setNewPwd2(String newPwd2) {
		this.newPwd2 = newPwd2;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
}
