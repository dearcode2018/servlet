/**
 * 描述: 
 * ServerTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.servlet.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.test.BaseTest;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * ServerTest
 */
public class ServerTest extends BaseTest {
	
	/**
	 * 描述: 
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	/**
	 * 描述: 
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}
}
