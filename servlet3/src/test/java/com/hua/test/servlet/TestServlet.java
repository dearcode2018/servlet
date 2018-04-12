/**
* TestServlet.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;

/**
 * 描述: 
 * @author qye.zheng
 * TestServlet
 */
public final class TestServlet extends BaseTest {

	/**
	 * 描述: post方法测试
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		log.info("doPost =====> enter");
		// 其他测试
		String param = request.getParameter("param");
		log.info("doPost =====> param = " + param);
		
		response.getOutputStream().write("abc_hehe_post".getBytes(Constant.CHART_SET_UTF_8));
		
		// 仍然停留在当前页面
		//response.sendRedirect("test/testServlet.jsp");
	}
	
	/**
	 * 描述: get方法测试
	 * @author qye.zheng
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		log.info("doGet =====> enter");
		this.doPost(request, response);
	}
}
