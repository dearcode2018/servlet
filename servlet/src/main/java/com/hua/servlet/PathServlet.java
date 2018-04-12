/**
 * PathServlet.java
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
import com.hua.util.WebPathUtil;

/**
 * PathServlet
 * 描述: 
 * @author qye.zheng
 * 
 */
// 使用注解的方式 ( http://127.0.0.1:8080/servlet3/PathServlet )
@WebServlet(name="PathServlet", urlPatterns={"/PathServlet"})
public final class PathServlet extends BaseServlet
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
	 * 
	 * 实例值 :  			127.0.0.1												8080									/servlet3
	 * 	获取方法 : request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
	 * 
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
		/**
		 * ServletRequest
		 * 
		 * ServletContext
		 * 
		 * Class getResousce(String) --> URL
		 * 
		 * 
		 * URL
		 * 
		 * ServletContext - getRealPath(String) - 真实物理路径
		 * ServletContext - getResource(String) --> URL
		 * URL - getPath()
		 * 
		 * 
		// request.getRealPath("/front"); 方式已经过时，不再使用此方式
		
		final ServletContext context = request.getServletContext();
		
		// web 根路径 (没有 路径分隔符)  - 建议返回采用此种方式
		log.info("doPost =====> " + context.getRealPath(""));
		
		// web 根路径 (有 路径分隔符)
		log.info("doPost =====> " + context.getRealPath("/"));
		
		// web 根路径下的子路径，子路径可以不存在  (有 路径分隔符) - 建议传参采用这种方式
		log.info("doPost =====> " + context.getRealPath("/front/test"));
		
		// web 根路径下的子路径，子路径可以不存在  (有 路径分隔符)
		log.info("doPost =====> " + context.getRealPath("front/test2"));
		
		
		// 获取资源的 url 对象 （以根路径为参） - 获取的是相对路径 (/localhost/appName/)
		// 一般不使用 URL 对象来获取 web 的路径信息
		URL url = null;
		url = context.getResource("/");
		log.info("doPost =====> url.getPath1() = " + url.getPath());
		
		// 子路径为参 (获取失败 - 原因未详)
		//url = context.getResource("/front/test");
		//log.info("doPost =====> url.getPath2() = " + url.getPath());
		
		
		// Class (获取指定类的所在路径)
		Class<?> clazz = getClass();
		// 参数 : 空字符串 - 获取指定类的所在路径 ( 传指定的 Class<?> )
		url = clazz.getResource("");
		log.info("doPost =====> url.getPath3() = " + url.getPath());
		
		// 获取类的根路径 (.../WEB-INF/classes/) ( 不用传参 )
		url = clazz.getResource("/");
		log.info("doPost =====> url.getPath4() = " + url.getPath());
		
		// 获取类的根路径下的子路径 (.../WEB-INF/classes/com/hua/) - 子路径不存在会抛异常 ( 传子路径 )
		// 必须以 / 开头，子路径必须存在（不存在则抛异常）- 以路径分隔符结尾
		url = clazz.getResource("/com/hua");
		log.info("doPost =====> url.getPath5() = " + url.getPath());
		
		//  ( 传子路径 )
		// 必须以 / 开头，子路径必须存在 （不存在则抛异常）- 以路径分隔符结尾
		url = clazz.getResource("/com/hua/");
		log.info("doPost =====> url.getPath6() = " + url.getPath());
		 * 
		 * 
		 * 
		 * 
		 */
		
		/**
		 * web 根路径
		 * 
		 * web根路径下的子路径 (不存在 - 不抛异常)
		 * 
		 * 类根路径
		 * 
		 * 类根路径下的子路径 (不存在 - 抛异常)
		 * 
		 * 当前类所在的路径
		 * 
		 * 
		 */
		
		// request.getRealPath("/front"); 方式已经过时，不再使用此方式
		
		final ServletContext context = request.getServletContext();
		
		// web 根路径 (末尾没有 路径分隔符)
		log.info("doPost =====> " + context.getRealPath(""));
		
		// web 根路径 (有 路径分隔符)
		log.info("doPost =====> " + context.getRealPath("/"));
		
		// web 根路径下的子路径，子路径可以不存在  (有 路径分隔符) - 建议传参采用这种方式
		log.info("doPost =====> " + context.getRealPath("/front/test/"));
		
		// web 根路径下的子路径，子路径可以不存在  (有 路径分隔符)
		log.info("doPost =====> " + context.getRealPath("front/test2/"));
		
		// 获取资源的 url 对象 （以根路径为参） - 获取的是相对路径 (/localhost/appName/)
		// 一般不使用 URL 对象来获取 web 的路径信息
		URL url = null;
		url = context.getResource("/");
		log.info("doPost =====> url.getPath1() = " + url.getPath());
		
		// 子路径为参 (获取失败 - 原因未详)
		//url = context.getResource("/front/test");
		//log.info("doPost =====> url.getPath2() = " + url.getPath());
		
		// Class (获取指定类的所在路径)
		Class<?> clazz = getClass();
		// 参数 : 空字符串 - 获取指定类的所在路径
		url = clazz.getResource("");
		log.info("doPost =====> url.getPath3() = " + url.getPath());
		
		// 获取类的根路径 (.../WEB-INF/classes/)
		url = clazz.getResource("/");
		log.info("doPost =====> url.getPath4() = " + url.getPath());
		
		// 获取类的根路径下的子路径 (.../WEB-INF/classes/com/hua) - 子路径不存在会抛异常
		// 必须以 / 开头，子路径必须存在（不存在则抛异常）- 以路径分隔符结尾
		url = clazz.getResource("/com/hua");
		log.info("doPost =====> url.getPath5() = " + url.getPath());
		
		// 必须以 / 开头，子路径必须存在 （不存在则抛异常）- 以路径分隔符结尾
		url = clazz.getResource("/com/hua/");
		log.info("doPost =====> url.getPath6() = " + url.getPath());
		
		log.info("doPost =====>11111 " + WebPathUtil.getWebSubpath(context, "/front/test/"));
		log.info("doPost =====>11111 " + WebPathUtil.getWebSubpath(context, "/front/test"));
/*		String path = WebPathUtil.getWebPath(getClass());
		log.info("doPost =====> path1 = " + path);
		
		path = WebPathUtil.getWebPath(request);
		log.info("doPost =====> path2 = " + path);
		
		path = WebPathUtil.getWebPath(request, "/front/test");
		log.info("doPost =====> path3 = " + path);
		
		path = WebPathUtil.getWebPath(request.getServletContext());
		log.info("doPost =====> path4 = " + path);*/
		
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
