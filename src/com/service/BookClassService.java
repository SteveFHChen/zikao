package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.BookClassDao;
import com.dao.CancelClassDao;
import com.dao.HasSessionQueryClassStatusDao;
import com.dao.NoSessionQueryClassStatusDao;
import com.dao.QueryStudentClassDao;
import com.dao.QuerySystemParamsDao;
import com.dto.BookClassDto;
import com.dto.SelectedCourse;
import com.dto.StudentDto;
import com.dto.SystemParamDto;
import com.dto.SystemParamItemDto;

public class BookClassService {
	
	NoSessionQueryClassStatusDao noSessionQueryClassStatusDao = new NoSessionQueryClassStatusDao();
	HasSessionQueryClassStatusDao hasSessionQueryClassStatusDao = new HasSessionQueryClassStatusDao();
	QueryStudentClassDao queryStudentClassDao = new QueryStudentClassDao();
	CancelClassDao cancelClassDao = new CancelClassDao();
	BookClassDao bookClassDao = new BookClassDao();
	
	QuerySystemParamsDao querySystemParamsDao = new QuerySystemParamsDao();

	public List<SelectedCourse> noSessionQueryClassStatus(){
		Map dayRange = queryClassDayRange();
		return noSessionQueryClassStatusDao.dao(dayRange);
	}
	
	public List<SelectedCourse> hasSessionQueryClassStatus(StudentDto loginUser){
		Map dayRange = queryClassDayRange();
		loginUser.setStartDateStr((String) dayRange.get("StartDateStr"));
		loginUser.setEndDateStr((String) dayRange.get("EndDateStr"));
		return hasSessionQueryClassStatusDao.dao(loginUser);
	}
	
	public Map queryClassDayRange(){
		SystemParamDto params = new SystemParamDto();
		
		List<SystemParamItemDto> items = new ArrayList<SystemParamItemDto>();
		items.add(new SystemParamItemDto("StartDateStr", null, null));
		items.add(new SystemParamItemDto("EndDateStr", null, null));
		
		params.setSystemParams(items);
		List<SystemParamItemDto> result = querySystemParamsDao.dao(params);
		
		Map dayRange = new HashMap();
		for(SystemParamItemDto item : result) {
			dayRange.put(item.getParamCode(), item.getParamValue());
		}
		
		return dayRange;
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
