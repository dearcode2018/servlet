/**
 * FileDownloadServlet.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.test.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.constant.Constant;
import com.hua.controller.BaseServlet;
import com.hua.util.ClassPathUtil;

/**
 * FileDownloadServlet
 * 描述: 
 * @author  qye.zheng
 */
@WebServlet(name="FileDownloadServlet", urlPatterns={"/FileDownloadServlet"})
public final class FileDownloadServlet extends BaseServlet
{

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
	 * @author  qye.zheng
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
		
		// 设置响应属性
		/*
		 常见的内容类型
		 text/css;charset=UTF-8
		 text/plain;charset=UTF-8
		 text/html;charset=UTF-8           
		 image/png;charset=UTF-8
  		image/gif;charset=UTF-8
   		application/jpeg;charset=UTF-8
   		// doc
    	application/msword;charset=UTF-8
    	// docx
     	application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8
     	application/vnd.ms-excel;charset=UTF-8
     	
		 */
		response.setContentType("text/plain;charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=\"" + "wenjianminghaha\"");
		
		String filePath = ClassPathUtil.getClassSubpath("/file/txt/info_01.txt", true);
		// 文件输入流
		InputStream input = new FileInputStream(filePath);
		//
		
		// 输出流
		OutputStream output = response.getOutputStream();
		// 1024 byte = 1kb
		byte[] data = new byte[1024];
		// 每次读取的字节长度，到文件末尾的时候，为了防止写入字节数变多
		int length = -1;
		log.info("doPost =====> " + input.available());
		response.setContentLength(input.available());
		
		while (Constant.NEGATIVE_ONE != (length = input.read(data)))
		{
			output.write(data, 0, length);
		}
		output.flush();
		output.close();
		input.close();
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
	 * @author  qye.zheng
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
