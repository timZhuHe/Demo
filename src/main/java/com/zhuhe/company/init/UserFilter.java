package com.zhuhe.company.init;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(urlPatterns = {"/web/page/*", "/EmployeeServlet", "/DepartmentServlet"})
public class UserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String action = req.getParameter("action");
		if("reg".equals(action)||"login".equals(action)) {
			chain.doFilter(request, response);
			return;
		}
		Object user = req.getSession().getAttribute("user");
		if(user==null)
			req.getRequestDispatcher("/web/login.jsp").forward(request,response);
		else
			chain.doFilter(request, response);
		
	}

}
