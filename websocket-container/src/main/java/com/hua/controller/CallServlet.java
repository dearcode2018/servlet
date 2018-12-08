/**
  * @filename CallServlet.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.websocket.MessageWebSocket;

/**
 * @type CallServlet
 * @description 
 * @author qianye.zheng
 */
public class CallServlet extends BaseServlet
{
	
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
		System.out.println("CallServlet.doGet()");
		this.doPost(request, response);
		MessageWebSocket.sessions.get(0).getBasicRemote().sendText("CallServlet.doGet() 嗨");
		
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
		System.out.println("CallServlet.doPost()");
		//final String method = request.getParameter();
	}
	
}
