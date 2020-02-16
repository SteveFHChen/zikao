package com.service;

import java.util.List;

import com.dao.QueryCourseListDao;
import com.dto.CourseDto;
import com.dto.StudentDto;

public class CourseService {
	
	private QueryCourseListDao queryCourseListDao = new QueryCourseListDao();
	
	public List<CourseDto> queryCourseList(StudentDto loginUser){
		return queryCourseListDao.dao(loginUser);
	}
}
