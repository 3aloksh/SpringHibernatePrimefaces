package com.gadwala.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class AuthencticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String loginServletURI = req.getContextPath() + "/Login";
		String loginPageURI = req.getContextPath() + "/login.jsp";
		String URL = req.getRequestURL().toString();
		
		boolean inLoginServlet = loginServletURI.endsWith(req.getRequestURI());
		boolean inLoginPage = loginPageURI.endsWith(req.getRequestURI());
		boolean inLogoutPage = req.getRequestURI().endsWith("Logout");
		
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("user") != null;
		String uri = req.getRequestURI();
		
		if(loggedIn || inLogoutPage || inLoginServlet 
				|| inLoginPage || URL.contains("admin/login.jsp") || URL.contains("instructor/login.jsp") ||
				URL.contains("student/login.jsp") || uri.endsWith("index.html")|| URL.contains("manager/login.jsp")
				|| uri.endsWith("forgot-password.jsp")
				|| uri.contains("/images") || uri.contains("/js") || uri.contains("/css") || uri.contains("/fonts")) {
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(req.getContextPath() + "/index.html");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
