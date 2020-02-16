package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDto;

public class LoginDao extends AbstractQueryDao {

	public String sql = "SELECT * FROM zk_student WHERE stu_no=? AND pwd=?;";
	
	public StudentDto dao(StudentDto params) {
		List<StudentDto> loginInfo = queryList(this.sql, params, StudentDto.class);
		return loginInfo.size() > 0 ? loginInfo.get(0) : null;
	}
	
	public static void main(String args[]) {
		LoginDao dao = new LoginDao();

		System.out.println("Test 1: normal");
		StudentDto params = new StudentDto();
		params.setStuNo("1001");
		params.setPwd("abc");
		params.setRoleCode("sysAdmin");
		
		StudentDto loginUser = dao.dao(params);
		System.out.println(loginUser);

		System.out.println("Test 2: not exists");
		params.setStuNo("1000"); 
		params.setPwd("xxx");
		params.setRoleCode("teacher");
		
		loginUser = dao.dao(params); 
		System.out.println(loginUser); 

		System.out.println("Test 3:Duplicate user");
		params.setStuNo("1003"); 
		params.setPwd("123");
		params.setRoleCode("student");
		  
		loginUser = dao.dao(params); 
		System.out.println(loginUser);
 
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		StudentDto p = (StudentDto) params;
		
		try {
			prepStat.setString(1, p.getStuNo());
			prepStat.setString(2, p.getPwd());
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
			user.setPhotoPath(rst.getString("photo_path"));
			
			result.add(user);
		}
		
		if(result.size()>1)
			throw new RuntimeException("FH:More than 1 same user []");
		
		return result;
	}

}
