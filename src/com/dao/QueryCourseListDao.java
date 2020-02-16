package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.CourseDto;
import com.dto.StudentDto;

public class QueryCourseListDao extends AbstractQueryDao {

	private String sql = "SELECT c.* FROM zk_role_crs_map rcm\r\n" + 
			"JOIN zk_course c ON rcm.course_code=c.course_code\r\n" + 
			"WHERE rcm.role_code=?\r\n" + 
			"ORDER BY order_id";
	
	public List<CourseDto> dao(StudentDto loginUser){
		List<CourseDto> result = this.queryList(sql, loginUser, CourseDto.class);
		return result;
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		StudentDto user = (StudentDto) params;
		try {
			prepStat.setString(1, user.getRoleCode());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepStat;
	}

	@Override
	public List<CourseDto> rowMapper(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		List<CourseDto> result = new ArrayList<CourseDto>();
		
		CourseDto c = null;
		while(rst.next()) {
			c = new CourseDto();
			c.setCourseCode(rst.getString("course_code"));
			c.setCourseName(rst.getString("course_name"));
			
			result.add(c);
		}
		return result;
	}

}
