package com.dto;

import java.util.Date;
import java.util.List;

public class BookClassDto {
	private Integer userId;//Student or Teacher ID
	private String pwd;
	private String oper;
	private String startTimeStr;
	private String endTimeStr;
	private Date startTime;
	private Date endTime;
	
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

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}
	
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<SelectedCourse> getSelectedCourses() {
		return selectedCourses;
	}

	public void setSelectedCourses(List<SelectedCourse> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}
	
}
