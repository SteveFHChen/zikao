package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.HttpResponse;
import com.dto.StudentDto;
import com.dto.SystemParamDto;
import com.dto.SystemParamItemDto;
import com.listener.SystemListener;
import com.mysql.cj.Constants;
import com.service.SystemManageService;
import com.util.Constant;
import com.util.Utils;

/**
 * Servlet implementation class SystemManageServlet
 */
@WebServlet("/service/managesystem")
public class SystemManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SystemManageService systemManageService = new SystemManageService();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
		
		HttpResponse resp = new HttpResponse();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		System.out.println(req.getParameter("p1") + " = " + SystemListener.get(req.getParameter("p1")));
		
		//Reload application.properties without application restart.
		String oper=req.getParameter("oper");
		
		if("reloadConfig".equals(oper)) {
			SystemListener.loadProperties();
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("Reload config success.");
		}
		
		res.getWriter().println(Utils.Java2Json(resp));
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpResponse resp = new HttpResponse();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		StudentDto loginUser = (StudentDto) req.getSession().getAttribute("LOGINUSER");
		if(null==loginUser) {
			resp.getHeader().setStatus(202);
			resp.getHeader().setMessage("您还没登入系统，请先登入!.");
			res.getWriter().println(Utils.Java2Json(resp));
			
			return;
		}else if(!Constant.ROLE_SYSADMIN.equals(loginUser.getRoleCode())) {
			resp.getHeader().setStatus(203);
			resp.getHeader().setMessage("权限不足，无法进行些操作!.");
			res.getWriter().println(Utils.Java2Json(resp));
			
			return;
		}
		
		SystemParamDto dto = Utils.getPayloadAsJava(req, SystemParamDto.class);
		
		if("querySystemParam".equals(dto.getOper())) {
			dto.setLoginUser(loginUser);
			List<SystemParamItemDto> systemParam = systemManageService.querySystemParmas(dto);
			
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("查询成功.");
			resp.setData(systemParam);
		}else if("updateSystemParam".equals(dto.getOper())) {
			List<SystemParamItemDto> result = systemManageService.updateSystemParams(dto);
			resp.setData(result);
			
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("修改成功.");
		}else if("reloadConfig".equals(dto.getOper())) {
			SystemListener.loadProperties();
			resp.getHeader().setStatus(200);
			resp.getHeader().setMessage("Reload config success.");
		}
		
		res.getWriter().println(Utils.Java2Json(resp));
	}

}
