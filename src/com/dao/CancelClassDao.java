package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.BookClassDto;
import com.dto.SelectedCourse;

public class CancelClassDao extends AbstractUpdateDao {

	private String sql = "UPDATE zk_book_class SET is_canceled='Y', canceled_time=CURRENT_TIMESTAMP "
			+ "WHERE is_canceled='N' AND stu_id=? AND course_code=? AND class_time=?";
	
	public int[] dao(BookClassDto dto) {
		int[] n = updateBatch(sql, dto);
		return n;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<SelectedCourse> scs = new ArrayList<SelectedCourse>();
		
		SelectedCourse sc = new SelectedCourse();
		sc.setCourseCode("internetdb");
		sc.setClassId("2020020715301555");
		sc.setIsCancel("N");
		scs.add(sc);
		
		sc = new SelectedCourse();
		sc.setCourseCode("or");
		sc.setClassId("2020020716001625");
		sc.setIsCancel("Y");
		scs.add(sc);
		
		BookClassDto bc = new BookClassDto();
		bc.setUserId(1);
		bc.setSelectedCourses(scs);
		
		CancelClassDao dao = new CancelClassDao();
		int[] n = dao.dao(bc);
		
		System.out.println("Cancel class success.");
	}

	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		BookClassDto p = (BookClassDto) params;
		List<SelectedCourse> scs = p.getSelectedCourses();
		
		try {
			for(SelectedCourse sc:scs) {
				if("Y".equals(sc.getIsCancel())) {
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
