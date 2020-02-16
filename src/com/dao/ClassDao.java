package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.BookClassDto;
import com.dto.SelectedCourse;

public class ClassDao {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static <T,K> List<T> queryList(String sql, K params, Class<T> clazz) {
		String url = "jdbc:mysql://localhost:3306/myweb1?characterEncoding=utf8&serverTimezone=UTC";
		String userName = "root";
		String password = "root123";
		
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet rst = null;
		
		List<T> result = null;
				
		try {
			conn = DriverManager.getConnection(url, userName, password);
			prepStat = conn.prepareStatement(sql);
			
			//Type1: query
			/* prepStat = selectedCourseListSetParams(prepStat, params);
			
			rst = prepStat.executeQuery();
			
			//Rowmapping
			result = (List<T>) selectedCourseListMapper(rst);
			*/
			//Type2: update
			prepStat = bookClassParams(prepStat, params);
//			int n = prepStat.executeUpdate();
			int[] n = prepStat.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rst!=null) {
				try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(prepStat!=null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		testInsert();
//		testQuery();
	}
	
	public static void testQuery() {
		String sql = "SELECT book_id, b.course_code, b.class_time, c.course_name \r\n" + 
				"FROM zk_book_class b\r\n" + 
				"JOIN zk_student s ON b.stu_id=s.stu_id\r\n" + 
				"JOIN zk_course c ON b.course_code=c.course_code\r\n" + 
				"WHERE b.is_canceled='N' AND s.stu_id= ? ";
		List<SelectedCourse> selectedCourses = queryList(sql, new String("1"), SelectedCourse.class);
		
		System.out.println("Query DB and map to Java success.");
		
	}
	
	public static void testInsert() {
		List<SelectedCourse> scs = new ArrayList<SelectedCourse>();
		
		SelectedCourse sc = new SelectedCourse();
		sc.setCourseCode("internetdb");
		sc.setClassId("2020020410001025");
		scs.add(sc);
		
		sc = new SelectedCourse();
		sc.setCourseCode("or");
		sc.setClassId("2020020510001025");
		scs.add(sc);
		
		BookClassDto bc = new BookClassDto();
		bc.setUserId(1);
		bc.setSelectedCourses(scs);
		
		String sql2 = "INSERT INTO zk_book_class(stu_id, course_code, class_time) "
				+ "VALUES(?, ?, ?)";
		queryList(sql2, bc, SelectedCourse.class);
		
		System.out.println("Insert DB success.");
	}
	
	public static PreparedStatement selectedCourseListSetParams(PreparedStatement prepStat, Object params) {
		String p = (String) params;
		try {
			prepStat.setInt(1, Integer.valueOf(p));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepStat;
	}
	
	public static List<SelectedCourse> selectedCourseListMapper(ResultSet rst) throws SQLException {
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
	
	public static PreparedStatement bookClassParams(PreparedStatement prepStat, Object params) {
		BookClassDto p = (BookClassDto) params;
		List<SelectedCourse> scs = p.getSelectedCourses();
		
		try {
			for(SelectedCourse sc:scs) {
				prepStat.setInt(1, p.getUserId());
				
				prepStat.setString(2, sc.getCourseCode());
				prepStat.setString(3, sc.getClassId());
				prepStat.addBatch();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepStat;
	}
}
