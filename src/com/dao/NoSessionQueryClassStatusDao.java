package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dto.SelectedCourse;

public class NoSessionQueryClassStatusDao extends AbstractQueryDao {

	public String sql = "SELECT book_id, NULL stu_id, b.course_code, b.class_time, c.course_name \r\n" + 
			"FROM zk_book_class b\r\n" + 
			"JOIN zk_course c ON b.course_code=c.course_code\r\n" + 
			"WHERE b.is_canceled='N' AND class_time >= ?";
	
	public List<SelectedCourse> dao(Map classDayRange){
		List<SelectedCourse> selectedCourses = queryList(this.sql, classDayRange, SelectedCourse.class);
		return selectedCourses;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NoSessionQueryClassStatusDao dao = new NoSessionQueryClassStatusDao();
		
		Map dayRange = new HashMap();
		dayRange.put("StartDateStr", "2020-02-14");
		List<SelectedCourse> selectedCourses = dao.dao(dayRange);
		
		System.out.println("Query DB and map to Java success.");
	}

	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		Map p = (Map) params;
		
		String sd = (String) p.get("StartDateStr");
		try {
			prepStat.setString(1, sd.replaceAll("-", "")+"00000000");
			//Test data: '2020021400000000'
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
