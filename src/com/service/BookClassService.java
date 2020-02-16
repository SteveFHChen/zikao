package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.BookClassDao;
import com.dao.CancelClassDao;
import com.dao.HasSessionQueryClassStatusDao;
import com.dao.NoSessionQueryClassStatusDao;
import com.dao.QueryStudentClassDao;
import com.dto.BookClassDto;
import com.dto.SelectedCourse;
import com.dto.StudentDto;

public class BookClassService {
	
	NoSessionQueryClassStatusDao noSessionQueryClassStatusDao = new NoSessionQueryClassStatusDao();
	HasSessionQueryClassStatusDao hasSessionQueryClassStatusDao = new HasSessionQueryClassStatusDao();
	QueryStudentClassDao queryStudentClassDao = new QueryStudentClassDao();
	CancelClassDao cancelClassDao = new CancelClassDao();
	BookClassDao bookClassDao = new BookClassDao();

	public List<SelectedCourse> noSessionQueryClassStatus(){
		return noSessionQueryClassStatusDao.dao();
	}
	
	public List<SelectedCourse> hasSessionQueryClassStatus(StudentDto loginUser){
		return hasSessionQueryClassStatusDao.dao(loginUser);
	}
	
	public List<SelectedCourse> queryClass(BookClassDto dto){
		StudentDto stu = new StudentDto();
		stu.setStuId(dto.getUserId());
		List<SelectedCourse> result = queryStudentClassDao.dao(stu);
		return result;
	}
	
	public List<SelectedCourse> bookClass(BookClassDto dto) {
		int[] n1 = cancelClassDao.dao(dto);
		
		int[] n2 = bookClassDao.dao(dto);
		
		return queryClass(dto);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookClassService service = new BookClassService();
		
		//Test queryClass()
		BookClassDto dto = new BookClassDto();
		dto.setUserId(1);
		
		List<SelectedCourse> scs = service.queryClass(dto);
		System.out.println(scs);
		
		//Test bookClass()
		List<SelectedCourse> newScs = new ArrayList<SelectedCourse>();
		
		SelectedCourse sc = new SelectedCourse();
		sc.setCourseCode("internetdb");
		sc.setClassId("2020020410001025");
		newScs.add(sc);
		
		sc = new SelectedCourse();
		sc.setCourseCode("or");
		sc.setClassId("2020020510001025");
		newScs.add(sc);
		
		dto.setSelectedCourses(newScs);
		scs = service.bookClass(dto);
		System.out.println(scs);
	}

}
