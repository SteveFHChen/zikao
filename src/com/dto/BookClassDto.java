package com.dto;

import java.util.List;

public class BookClassDto {
	private Integer userId;//Student or Teacher ID
	private String pwd;
	private String oper;
	
	private List<SelectedCourse> selectedCourses;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<SelectedCourse> getSelectedCourses() {
		return selectedCourses;
	}

	public void setSelectedCourses(List<SelectedCourse> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}
	
}
