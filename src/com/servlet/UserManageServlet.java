package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.HttpResponse;
import com.dto.StudentDto;
import com.service.UserManageService;
import com.util.Utils;

/**
 * Servlet implementation class UserManageServlet
 */
@WebServlet("/service/manageuser")
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpResponse resp = new HttpResponse();
		StudentDto loginUser = (StudentDto) req.getSession().getAttribute("LOGINUSER");
		
		StudentDto dto = Utils.getPayloadAsJava(req, StudentDto.class);
		
		UserManageService userManageService = new UserManageService();
		StudentDto result = null;
		
		if("changePwd".equals(dto.getOper())) {
			if(null==loginUser) {
				resp.getHeader().setStatus(202);
				resp.getHeader().setMessage("您还没登入系统，请先登入!.");
			}else {
				dto.setStuId(loginUser.getStuId());
				dto.setStuNo(loginUser.getStuNo());
				Integer n = userManageService.updateSelfPwd(dto);
				
				resp.getHeader().setStatus(200);
				resp.getHeader().setMessage("修改密码成功.");
				resp.setData(n);
			}
		}else if("queryUserInfo".equals(dto.getOper())) {
			if(null==loginUser) {
				//No session situation
				resp.getHeader().setStatus(202);
				resp.getHeader().setMessage("您还没登入系统，请先登入!.");
			}else {
				//Has session situation
				//Get student ID from session first
				resp.getHeader().setStatus(200);
				resp.getHeader().setMessage("查询成功.");
				result = userManageService.queryUserByUserId(loginUser.getStuId());
			}
			
			resp.setData(result);
		}else if("queryCurrentUserInfo".equals(dto.getOper())) {
			if(null==loginUser) {
				//No session situation
				resp.getHeader().setStatus(202);
				resp.getHeader().setMessage("您还没登入系统，请先登入!.");
			}else {
				//Has session situation
				//Get student ID from session first
				resp.getHeader().setStatus(200);
				resp.getHeader().setMessage("查询成功.");
				result = loginUser;
			}
			
			resp.setData(result);
		}
		
		res.setCharacterEncoding("utf-8");
		res.getWriter().println(Utils.Java2Json(resp));
	}

}
