/**
 * ValidateCodeServlet.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.servlet;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.controller.BaseServlet;
import com.hua.validate.ValidateCode;

/**
 * ValidateCodeServlet
 * 描述: 
 * @author qye.zheng
 * 
 */
// 使用注解的方式 ( http://127.0.0.1:8080/servlet3/ValidateCodeServlet )
@WebServlet(name="ValidateCodeServlet", urlPatterns={"/ValidateCodeServlet"})
public final class ValidateCodeServlet extends BaseServlet
{
	 /* long */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * 转发到指定 Servlet 或 Jsp 页面 (传递 请求和响应 对象)
	 * 将本次请求传递给其他处理逻辑或资源，然后再进行响应
	 * 始终在本次请求中进行
	 * 
	 * 
	 * 重定向 (通知客户端，根据本次响应提供的地址再次发请求)
	 * 通知客户端再创建新的请求
	 * 本次请求结束之后，将发生新的请求
	 * 
	 */
	
	
	/**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException
	{
		log.info("doPost =====> ");
		final ValidateCode code = new ValidateCode();
		code.getValidateCode(response.getOutputStream());
		log.info("doPost =====> " + code.getRandomString());
		// get / post 处理逻辑
		// 请求对象 - 设置属性
		//request.setAttribute("user", user);
		
		// 转发到指定 Servlet 或 Jsp 页面 (传递 请求和响应 对象)
		// forwardUrl = "/front/el/ExpLang.jsp";
		// request.getRequestDispatcher(forwardUrl).forward(request, response);
		
		// 重定向 (通知客户端，根据本次响应提供的地址再次发请求)
		// redirectUrl = "";
		// response.sendRedirect(redirectUrl);
	}
	
	/**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException
	{
		log.info("doGet =====> ");
		// 调用 doPost 方法
		this.doPost(request, response);
	}
	
}
