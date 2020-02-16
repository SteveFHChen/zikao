package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.BookClassDto;
import com.dto.SelectedCourse;

public class BookClassDao extends AbstractUpdateDao {

	private String sql = "INSERT INTO zk_book_class(stu_id, course_code, class_time) "
			+ "VALUES(?, ?, ?)";
	
	public int[] dao(BookClassDto dto) {
		int[] n = updateBatch(sql, dto);
		return n;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		IDao dao = new BookClassDao();
		int[] n = dao.updateBatch(sql2, bc);
		
		System.out.println("Insert DB success.");
	}

	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		BookClassDto p = (BookClassDto) params;
		List<SelectedCourse> scs = p.getSelectedCourses();
		
		try {
			for(SelectedCourse sc:scs) {
				if("N".equals(sc.getIsCancel())) {
					prepStat.setInt(1, p.getUserId());
					
					prepStat.setString(2, sc.getCourseCode());
					prepStat.setString(3, sc.getClassId());
					prepStat.addBatch();
				}
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
