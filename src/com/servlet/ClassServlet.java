package com.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.BookClassDto;
import com.dto.HttpResponse;
import com.dto.SelectedCourse;
import com.dto.StudentDto;
import com.service.BookClassService;
import com.service.CourseService;
import com.util.Constant;
import com.util.Utils;

/**
 * Servlet implementation class ClassServlet
 */
@WebServlet("/service/class")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Received get request.");
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doGet(req, res);
		System.out.println("Received post request.");
		
		String name; 
		
		req.setCharacterEncoding("utf-8");
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			System.out.println(name + ": " + req.getParameter(name));
		}
		
		HttpResponse resp = new HttpResponse();
		StudentDto loginUser = (StudentDto) req.getSession().getAttribute("LOGINUSER");
		
//		String payload = Utils.getRequestPayload(req);
		BookClassDto dto = Utils.getPayloadAsJava(req, BookClassDto.class);
		
		BookClassService bookClassService = new BookClassService();
		List<SelectedCourse> scs = null;
		
		if("bookClass".equals(dto.getOper())) {
			if(null==loginUser) {
				resp.getHeader().setStatus(202);
				resp.getHeader().setMessage("您还没登入系统，请先登入再预约课时.");
			}else {
				dto.setUserId(loginUser.getStuId());
				scs = bookClassService.bookClass(dto);
				
				resp.getHeader().setStatus(200);
				resp.getHeader().setMessage("预约课时成功.");
				resp.setData(scs);
			}
		}else if("queryClassStatus".equals(dto.getOper())) {
			if(null==loginUser) {
				//No session situation
				scs = bookClassService.noSessionQueryClassStatus();
			}else {
				//Has session situation
				//Get student ID from session first
				scs = bookClassService.hasSessionQueryClassStatus(loginUser);
			}
			
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("查询课时成功.");
			resp.setData(scs);
		}else if("queryCourseList".equals(dto.getOper())){
			CourseService courseService = new CourseService();
			if(null==loginUser) {
				//No session situation
				StudentDto noSessionUser = new StudentDto();
				noSessionUser.setRoleCode(Constant.NO_SESSION_ROLE);
				resp.setData(courseService.queryCourseList(noSessionUser));
			}else {
				//Has session situation
				//Get student ID from session first
				resp.setData(courseService.queryCourseList(loginUser));
			}
			
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("查询课程列表成功.");
		}
		
		String sessionId = req.getSession().getId();
		System.out.println("sessionId:"+sessionId);
		System.out.println("Session LOGINUSER:"+req.getSession().getAttribute("LOGINUSER"));
		
		res.setCharacterEncoding("utf-8");
//		res.getWriter().println("{\"msg\":\"预约成功！\"}");
		res.getWriter().println(Utils.Java2Json(resp));
	}
	
	
	
}
