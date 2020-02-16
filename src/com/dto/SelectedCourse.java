package com.dto;

public class SelectedCourse {

	private Integer bookId;
	private String classId;
	private String courseCode;
	private String courseName;//reduandent
	private String isCancel;//
	private Integer studentId;//For checking class status
	private String stuName;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	@Override
	public String toString() {
		return "SelectedCourse [bookId=" + bookId + ", classId=" + classId + ", courseCode=" + courseCode
				+ ", courseName=" + courseName + ", isCancel=" + isCancel + ", studentId=" + studentId + ", stuName="
				+ stuName + "]";
	}
}
