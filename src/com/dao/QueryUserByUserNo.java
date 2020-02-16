package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDto;

public class QueryUserByUserNo extends AbstractQueryDao {

	private String sql = "SELECT * FROM zk_student WHERE stu_no=?";
	
	public StudentDto dao(String userNo) {
		List<StudentDto> users = this.queryList(sql, userNo, StudentDto.class);
		return users.size()>=1 ? users.get(0) : null;
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		String userNo = (String) params;
		try {
			prepStat.setString(1, userNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepStat;
	}

	@Override
	public List<StudentDto> rowMapper(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		StudentDto user = null;
		List<StudentDto> result = new ArrayList<StudentDto>();
		
		while(rst.next()) {
			user = new StudentDto(); 
			user.setStuId(rst.getInt("stu_id"));
			user.setStuNo(rst.getString("stu_no")); 
			user.setName(rst.getString("stu_name"));
			user.setRoleCode(rst.getString("role_code"));
			user.setPwd(rst.getString("pwd"));
			user.setPhotoPath(rst.getString("photo_path"));
			
			result.add(user);
		}
		
		return result;
	}

}
