package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.SelectedCourse;
import com.dto.StudentDto;

public class QueryStudentClassDao extends AbstractQueryDao {

	public String sql = "SELECT book_id, b.course_code, b.class_time, c.course_name \r\n" + 
			"FROM zk_book_class b\r\n" + 
			"JOIN zk_student s ON b.stu_id=s.stu_id\r\n" + 
			"JOIN zk_course c ON b.course_code=c.course_code\r\n" + 
			"WHERE b.is_canceled='N' AND s.stu_id= ? ";
	
	public List<SelectedCourse> dao(StudentDto params){
		List<SelectedCourse> selectedCourses = queryList(sql, params, SelectedCourse.class);
		return selectedCourses;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "SELECT book_id, b.course_code, b.class_time, c.course_name \r\n" + 
				"FROM zk_book_class b\r\n" + 
				"JOIN zk_student s ON b.stu_id=s.stu_id\r\n" + 
				"JOIN zk_course c ON b.course_code=c.course_code\r\n" + 
				"WHERE b.is_canceled='N' AND s.stu_id= ? ";
		IDao dao = new QueryStudentClassDao();
		
		List<SelectedCourse> selectedCourses = dao.queryList(sql, new String("1"), SelectedCourse.class);
		
		System.out.println("Query DB and map to Java success.");
	}

	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		StudentDto p = (StudentDto) params;
		try {
			prepStat.setInt(1, Integer.valueOf(p.getStuId()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			
			result.add(sc);
		}
		
		return result;
	}

}
