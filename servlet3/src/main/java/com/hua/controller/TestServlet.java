/**
 * 描述: 
 * TestServlet.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.util.StringUtil;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * TestServlet
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
@SuppressWarnings({"serial"})
public final class TestServlet extends BaseServlet {

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
		System.out.println(StringUtil.streamToString(request.getInputStream()));
		System.out.println("TestServlet.doPost()");
	}
}
