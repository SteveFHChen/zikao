package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dto.StudentDto;

public class ChangeSelfPwdDao extends AbstractUpdateDao {

	private String sql = "UPDATE zk_student SET pwd = ? WHERE stu_id = ?";
	
	public int dao(StudentDto params) {
		int n = this.update(sql, params);
		return n;
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		StudentDto dto = (StudentDto) params;
		try {
			prepStat.setString(1, dto.getNewPwd1());
			prepStat.setInt(2, dto.getStuId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepStat;
	}

}
