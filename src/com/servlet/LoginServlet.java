package com.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.HttpResponse;
import com.dto.StudentDto;
import com.service.LoginService;
import com.sys.dto.ZkSysLogDto;
import com.sys.service.ZkSysLogService;
import com.util.Constant;
import com.util.Utils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/service/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginService();
	private ZkSysLogService zkSysLogService = new ZkSysLogService();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpResponse resp = new HttpResponse();
		String name; 
		ZkSysLogDto zkSysLogDto = new ZkSysLogDto();
		StringBuffer logDetail = new StringBuffer();
		
		req.setCharacterEncoding("utf-8");
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			System.out.println(name + ": " + req.getParameter(name));
		}
		
		String p1 = req.getParameter("p1");
		if("logout".equals(p1)) {
			StudentDto loginUser = (StudentDto) req.getSession().getAttribute("LOGINUSER");
			
			req.getSession().removeAttribute(Constant.SESSION_KEY_LOGINUSER);
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("注销成功.");
			
			zkSysLogDto.setOperCode("User Logout");
			zkSysLogDto.setUserId(loginUser.getStuId());
			if(loginUser!=null)
				logDetail.append("User "+loginUser.getStuNo()+" "+loginUser.getName()+" logout.");
		}else {//login
			StudentDto dto = Utils.getPayloadAsJava(req, StudentDto.class);
			
			System.out.println(dto);
			zkSysLogDto.setOperCode("User Login");
			logDetail.append("Params: "+dto.getStuNo()+" "+dto.getPwd());
			
			StudentDto loginUser = loginService.login(dto);
			
			String msg = null;
			if(loginUser!=null && loginUser.getStuId()!=0) {
				HttpSession session = req.getSession();
				session.setAttribute(Constant.SESSION_KEY_LOGINUSER, loginUser);
				session.setMaxInactiveInterval(60*60*24*5);
				
				String sessionId = req.getSession().getId();
				System.out.println("sessionId:"+sessionId);
				
				System.out.println("Session LOGINUSER:"+req.getSession().getAttribute("LOGINUSER"));
				
				if(Constant.DEFAULT_PWD.equals(dto.getPwd())) {
					resp.getHeader().setStatus(202);
					resp.getHeader().setMessage("登入成功，请修改默认密码.");
				}else {
					resp.getHeader().setStatus(200);
					resp.getHeader().setMessage("登入成功.");
				}
				
				logDetail.append(" success.");
				zkSysLogDto.setUserId(loginUser.getStuId());
			}else {
				zkSysLogDto.setUserId(-1);
				resp.getHeader().setStatus(201);
				resp.getHeader().setMessage("登入失败！");
				
				logDetail.append(" failed.");
			}
		}
		
		zkSysLogDto.setDetail(logDetail.toString());
		this.zkSysLogService.sysLogInput(zkSysLogDto);
		
		res.setCharacterEncoding("utf-8");
		res.getWriter().println(Utils.Java2Json(resp));
	}

}
