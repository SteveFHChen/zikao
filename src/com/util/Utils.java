package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dto.SystemParamDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        
        try(BufferedReader reader = req.getReader();) {
                 char[]buff = new char[1024];
                 int len;
                 while((len = reader.read(buff)) != -1) {
                          sb.append(buff,0, len);
                 }
        }catch (IOException e) {
                 e.printStackTrace();
        }
        return sb.toString();
	}
	
	public static <T> T Json2Java(String jsonStr, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		
		T result = null;
		try {
			result = (T) mapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String Java2Json(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static <T> T getPayloadAsJava(HttpServletRequest req, Class<T> clazz) {
		return Json2Java(getRequestPayload(req), clazz);
	}
	
	public static void main(String[] args) {
		//Test Java map convert to JSON
		Map m1 = new HashMap();
		m1.put("StartDateStr", "2020-02-03");
		m1.put("EndDateStr", "2020-02-23");
		
		SystemParamDto dto = new SystemParamDto();
		dto.setOper("Query");
		m1.put("Obj1", dto);
		System.out.println(Utils.Java2Json(m1));
	}
}
