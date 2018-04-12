/**
 * 描述: 
 * UserLogServlet.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.controller.BaseServlet;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * UserLogServlet
 */
public final class UserLogServlet extends BaseServlet {

	private static final String USER_LOG_SEARCH = "userLogSearch.action";
	
	
	 /* long */
	private static final long serialVersionUID = 1L;
	
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
		this.doPost(request, response);
	}
	
	/**
	 * 描述: post请求
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//final String method = request.getParameter();
	}
}
