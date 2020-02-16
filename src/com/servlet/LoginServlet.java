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
import com.util.Constant;
import com.util.Utils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginService();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpResponse resp = new HttpResponse();
		String name; 
		
		req.setCharacterEncoding("utf-8");
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			System.out.println(name + ": " + req.getParameter(name));
		}
		
		String p1 = req.getParameter("p1");
		if("logout".equals(p1)) {
			req.getSession().removeAttribute(Constant.SESSION_KEY_LOGINUSER);
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("注销成功.");
		}else {//login
			StudentDto dto = Utils.getPayloadAsJava(req, StudentDto.class);
			
			System.out.println(dto);
			
			StudentDto loginUser = loginService.login(dto);
			
			String msg = null;
			if(loginUser!=null && loginUser.getStuId()!=0) {
				HttpSession session = req.getSession();
				session.setAttribute(Constant.SESSION_KEY_LOGINUSER, loginUser);
				session.setMaxInactiveInterval(60*30);
				
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
				
			}else {
				resp.getHeader().setStatus(201);
				resp.getHeader().setMessage("登入失败！");
			}
		}
		
		res.setCharacterEncoding("utf-8");
		res.getWriter().println(Utils.Java2Json(resp));
	}

}
