package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.dto.StudentDto;
import com.sys.dto.SysLogDto;
import com.sys.service.SysLogService;
import com.util.Constant;

/**
 * Servlet Filter implementation class SystemMonitorFilter
 */
@WebFilter(urlPatterns= {"/class/hi.html", "/or2019b/*", "/internetdb2019b/*"})
public class SystemMonitorFilter implements Filter {

	private SysLogService sysLogService = new SysLogService();
    /**
     * Default constructor. 
     */
    public SystemMonitorFilter() {
    }

    /**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(this.getClass().getName()+" init() executing...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		System.out.println(this.getClass().getName()+" doFilter() executing...");
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		
		StudentDto loginUser = (StudentDto) httpReq.getSession().getAttribute(Constant.SESSION_KEY_LOGINUSER);
		if(loginUser!=null) {
			this.sysLogService.sysLogInput(new SysLogDto(loginUser.getStuId(), httpReq.getRemoteAddr(), httpReq.getRequestURL().toString()));
		}else {
			this.sysLogService.sysLogInput(new SysLogDto(httpReq.getRemoteAddr(), httpReq.getRequestURL().toString()));
		}
		
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println(this.getClass().getName()+" destroy() executing...");
	}

}
