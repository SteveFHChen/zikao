package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDto;

public class QueryUserByUserId extends AbstractQueryDao {

	private String sql = "SELECT * FROM zk_student WHERE stu_id=?";
	
	public StudentDto dao(Integer userId) {
		List<StudentDto> users = this.queryList(sql, userId, StudentDto.class);
		return users.size()>=1 ? users.get(0) : null;
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		Integer userId = (Integer) params;
		try {
			prepStat.setInt(1, userId);
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
