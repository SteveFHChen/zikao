package com.test;

import com.dto.SelectedCourse;
import com.util.Utils;

public class UtilsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String jsonStr = "{\"classId\":\"1001\",\"courseCode\":\"OR\"}";
		
		SelectedCourse sc = Utils.Json2Java(jsonStr, SelectedCourse.class);
		
		System.out.println(sc);
	}

}
