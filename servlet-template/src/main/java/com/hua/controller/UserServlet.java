/**
 * 描述: 
 * UserServlet.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hua.entity.UserLog;
import com.hua.util.DateTimeUtil;
import com.hua.util.WebUtil;

/**
 * 描述: 用户接口
 * 包含 : 用户注册、登录、注销
 * 
 * @author qye.zheng
 * UserServlet
 */
public final class UserServlet extends BaseServlet {
	
	private static final String USER_REGISTER = "userRegister.action";
	
	private static final String USER_LOGIN = "userLogin.action";
	
	private static final String USER_LOGOUT = "userLogout.action";
	
	/**
	 * 获取请求后缀，进行转发
	 * 描述: post请求
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String suffixUri = WebUtil.getRequestUriSuffix(request);
		log.info("doPost =====> uriSuffix = " + suffixUri);
		if (USER_REGISTER.equals(suffixUri)) {
			// 用户注册
			userRegister(request, response);
		} else if (USER_LOGIN.equals(suffixUri)) {
			// 用户登录
			userLogin(request, response);
		} else if (USER_LOGOUT.equals(suffixUri)) {
			// 用户注销
			userLogout(request, response);
		}
	}
	
	/**
	 * 描述: get请求
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 转为 post 处理
		this.doPost(request, response);
	}
	
	/**
	 * 
	 * 描述: 用户注册
	 * 
	 * @author qye.zheng
	 * @param request
	 * @param response
	 */
	private void userRegister(final HttpServletRequest request, final HttpServletResponse response) {
		log.info("userLogin =====> enter");
		//log.info("userLogin =====> clientIp = " + request.getRemoteHost());
		final HttpSession session = request.getSession(true);
		final UserLog login = getLogin(request);
		if (null == session.getAttribute(login.getUsername())) {
			log.info("userLogin =====> session中没有登录信息，将登录对象放入session");
			session.setAttribute(login.getUsername(), login);
		} else {
			log.info("userLogin =====> session中有登录信息");
		}
		
	}

	/**
	 * 
	 * 描述: 用户登录 
	 * 
	 * @author qye.zheng
	 * @param request
	 * @param response
	 */
	private void userLogin(final HttpServletRequest request, final HttpServletResponse response) {
		log.info("userLogin =====> enter");
		//log.info("userLogin =====> clientIp = " + request.getRemoteHost());
		final HttpSession session = request.getSession(true);
		final UserLog login = getLogin(request);
		if (null == session.getAttribute(login.getUsername())) {
			log.info("userLogin =====> session中没有登录信息，将登录对象放入session");
			session.setAttribute(login.getUsername(), login);
		} else {
			log.info("userLogin =====> session中有登录信息");
		}
		
	}
	
	/**
	 * 
	 * 描述: 用户注销
	 * 
	 * @author qye.zheng
	 * @param request
	 * @param response
	 */
	private void userLogout(final HttpServletRequest request, final HttpServletResponse response) {
		
	}
	
	/**
	 * 
	 * 描述:  封装用户日志
	 * 
	 * @author qye.zheng
	 * @param request
	 * @return
	 */
	private UserLog getLogin(final HttpServletRequest request) {
		final UserLog login = new UserLog();
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		login.setUsername(username);
		login.setLoginTime(DateTimeUtil.getTimestamp());
		login.setLoginIp(request.getRemoteHost());

		
		return login;
	}
	
	
}
