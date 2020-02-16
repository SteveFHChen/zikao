package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.SelectedCourse;
import com.dto.StudentDto;

public class HasSessionQueryClassStatusDao extends AbstractQueryDao {

	public String sql = "SELECT book_id, "+
			"   CASE WHEN 'teacher'=? or 'sysAdmin'=? THEN s.stu_id WHEN b.stu_id=? THEN ? ELSE NULL END stu_id, \r\n" +
			"   CASE WHEN 'teacher'=? or 'sysAdmin'=? THEN s.stu_name WHEN b.stu_id=? THEN ? ELSE NULL END stu_name, \r\n" +
			"	b.course_code, b.class_time, c.course_name \r\n" + 
			"FROM zk_book_class b\r\n" + 
			"LEFT JOIN zk_student s ON b.stu_id=s.stu_id\r\n" + 
			"JOIN zk_course c ON b.course_code=c.course_code\r\n" + 
			"WHERE b.is_canceled='N'";
	
	public List<SelectedCourse> dao(StudentDto loginUser){
		List<SelectedCourse> selectedCourses = queryList(this.sql, loginUser, SelectedCourse.class);
		return selectedCourses;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HasSessionQueryClassStatusDao dao = new HasSessionQueryClassStatusDao();
		
		StudentDto loginUser = new StudentDto();
		loginUser.setStuId(1);
		loginUser.setRoleCode("teacher");
		
		List<SelectedCourse> selectedCourses = dao.dao(loginUser);
		
		System.out.println("Query DB and map to Java success.");
	}

	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		StudentDto p = (StudentDto) params;
		
		try {
			prepStat.setString(1, p.getRoleCode());
			prepStat.setString(2, p.getRoleCode());
			prepStat.setInt(3, p.getStuId());
			prepStat.setInt(4, p.getStuId());
			prepStat.setString(5, p.getRoleCode());
			prepStat.setString(6, p.getRoleCode());
			prepStat.setInt(7, p.getStuId());
			prepStat.setString(8, p.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prepStat;
	}

	/*
	 * @Override 
	 * public <T> List<T> rowMapper(ResultSet rst) { // TODO
	 * Auto-generated method stub return null; }
	 */
	
	@Override
	public List<SelectedCourse> rowMapper(ResultSet rst) throws SQLException {
		SelectedCourse sc = null;
		List<SelectedCourse> result = new ArrayList<SelectedCourse>();
		
		while(rst.next()) {
			sc = new SelectedCourse();
			sc.setBookId(rst.getInt("book_id"));
			sc.setCourseCode(rst.getString("course_code"));
			sc.setClassId(rst.getString("class_time"));
			sc.setCourseName(rst.getString("course_name"));
			sc.setStudentId(rst.getInt("stu_id"));
			sc.setStuName(rst.getString("stu_name"));
			
			result.add(sc);
		}
		
		return result;
	}

}
