package com.service;

import com.dao.ChangeSelfPwdDao;
import com.dao.QueryUserByUserId;
import com.dao.QueryUserByUserNo;
import com.dto.StudentDto;
import com.util.Constant;

public class UserManageService {
	
	private QueryUserByUserId queryUserByUserId = new QueryUserByUserId();
	private QueryUserByUserNo queryUserByUserNo = new QueryUserByUserNo();
	public ChangeSelfPwdDao changeSelfPwdDao = new ChangeSelfPwdDao();
	
	public StudentDto queryUserByUserId(Integer userId) {
		StudentDto user = queryUserByUserId.dao(userId);
		return user;
	}
	
	public StudentDto queryUserByUserNo(String userNo) {
		/**
		 * Only for sysAdmin
		 */
		StudentDto user = queryUserByUserNo.dao(userNo);
		return user;
	}
	
	
	
	public Integer updateSelfPwd(StudentDto params) {
		Integer updateRecordCount = null;
		/**
		 * Check old pwd (pwd field) is same as DB
		 * Check newPwd1 = newPwd2, and != old pwd
		 * If all are yes, then update pwd.
		 */
		
		if(params.getPwd()!=null 
				&& !"".equals(params.getPwd())
				
				&& params.getNewPwd1()!=null
				&& !"".equals(params.getNewPwd1())
				&& !Constant.DEFAULT_PWD.equals(params.getNewPwd1())
				
				&& params.getNewPwd1().equals(params.getNewPwd2())
				) {
			StudentDto oldUser = queryUserByUserId(params.getStuId());
			if(params.getPwd().equals(oldUser.getPwd())) {
				updateRecordCount = changeSelfPwdDao.dao(params);
			}
		}
		
		return updateRecordCount==null ? 0 : updateRecordCount;
	}
	
	public StudentDto resetPwd(String stu_no) {
		return null;
	}
}
