package com.service;

import com.dao.LoginDao;
import com.dto.StudentDto;

public class LoginService {

	public LoginDao loginDao = new LoginDao();
	
	public StudentDto login(StudentDto params) {
		StudentDto loginUser = loginDao.dao(params);
		return loginUser;
	}
	
	
}
