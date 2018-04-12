/**
 * 描述: 
 * SessionServlet.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.session;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hua.controller.BaseServlet;
import com.hua.entity.User;
import com.hua.util.BeanUtil;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * SessionServlet
 */
//使用注解的方式 ( http://127.0.0.1:8080/servlet3/SessionServlet )
@WebServlet(name="SessionServlet", urlPatterns={"/SessionServlet"})
public final class SessionServlet extends BaseServlet {

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
		try
		{
			log.info("doPost =====> ");
			Map<String, String[]> map = request.getParameterMap();
			final User user = new User();
			BeanUtil.populate(user, map);
			log.info("doPost =====> " + user.getUsername());
			log.info("doPost =====> " + user.getPassword());
			request.getSession().setAttribute("user", user);
			// chrome : 008F9B423A0439A38C8977296D92ED19
			// ie : 308B0113CA6A0E8BEF567A1380BD51E9
			// firefox : 441EA23535F46892C6202C5D1E7BBAC8
			// 失效天数
			final int expireDay = 1;
			log.info("doPost =====> sessionId = " + request.getSession().getId());
			final HttpSession session = request.getSession(true);
			final String sessionId = session.getId();
			// 建立sessionId 与 user对象关联 (下次再登录的时候，经过拦截器，从中取出user对象，判断是否已经登录过)
			session.setAttribute(sessionId, user);
			Cookie tC = new Cookie("tId", sessionId);
			tC.setMaxAge(expireDay * 24 * 60 * 60);
			// 添加 cookie 到响应对象
			response.addCookie(tC);
			
			/**
			 * 同一个终端，使用不同的浏览器，其sessionId是不同的，但是多个浏览器之间切换，
			 * 
			 * sessionId 是根据 操作系统/浏览器/机器特性来生成的唯一标识符
			 * 
			 * sessionId 默认maxAge == -1 表示一旦关闭浏览器，此cookie就会消失，下次再请求则会产生新的sessionId
			 * 
			 * 删除cookie : 创建一个Cookie对象，设置好其name值，然后将其maxAge属性设置为0，表示将删除掉该cookie
			 * 
			 * 
			 maxAge 可以为正数，表示此cookie从创建到过期所能存在的时间，以秒为单位，此cookie会存储到客户端电脑，以cookie文件形式保存，不论关闭浏览器或关闭电脑，直到时间到才会过期。
			可以为负数，表示此cookie只是存储在浏览器内存里，只要关闭浏览器，此cookie就会消失。maxAge默认值为-1。
			还可以为0，表示从客户端电脑或浏览器内存中删除此cookie。）
			
			如果maxAge属性为正数，则表示该Cookie会在maxAge秒之后自动失效。浏览器会将maxAge为正数的Cookie持久化，即写到对应的Cookie文件中。无论客户关闭了浏览器还是电脑，只要还在maxAge秒之前，登录网站时该Cookie仍然有效。
			如果maxAge为负数，则表示该Cookie仅在本浏览器窗口以及本窗口打开的子窗口内有效，关闭窗口后该Cookie即失效。maxAge为负数的Cookie，为临时性Cookie，不会被持久化，不会被写到Cookie文件中。Cookie信息保存在浏览器内存中，因此关闭浏览器该Cookie就消失了。Cookie默认的maxAge值为-1。
			如果maxAge为0，则表示删除该Cookie。Cookie机制没有提供删除Cookie的方法，因此通过设置该Cookie即时失效实现删除Cookie的效果。失效的Cookie会被浏览器从Cookie文件或者内存中删除。
			response对象提供的Cookie操作方法只有一个添加操作add(Cookie cookie)。要想修改Cookie只能使用一个同名的Cookie来覆盖原来的Cookie，达到修改的目的。删除时只需要把maxAge修改为0即可。
			在所遇到的项目中，Action里创建了一个cookie，maxAge为-1，紧接着在另一个方法中要删除cookie，就可以通过创建一个同名同域的cookie，然后将maxAge设置为0，再通过response的addCookie方法对客户端的cookie文件或浏览器内存中的cookie进行删除。
			注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
			注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
			
			token机制 : 将用户信息和令牌特性/有效期等信息进行加密，用户第一次登录的时候服务器
			构造该令牌，放入session的属性之中，以后客户端每次请求都携带此信息放在请求头，
			服务器通过解析之后，提取用户信息，然后进行相关的操作.
			令牌失效 : 在令牌失效之前的一段时间内，服务器将对令牌失效前的最后一次请求，做换取新
			令牌工作，使用新的令牌，然后用户就可以继续操作了.
			也可以强制要求用户重新登录，来获取新的令牌.
			
			
			自动登录/记住密码多长时间(1天/一周/一月/半年/一年) : 客户端发请求，请求头携带sessionId，
			服务器根据sessionId从sesion对象中提取key为sessionId的值，如果存在，则用户已经登录，
			页面跳转到用户首页，否则，跳转到登录页.
			
			注销 : 根据sessionId从session中将key为sessionId值移除，用户若需操作自己的信息，需要
			重新登录，拦截没有登录的请求 可以通过拦截器来进行.
			
			*/
			Cookie[] cookies = request.getCookies();
			Cookie c =  cookies[0];
			log.info("doPost =====> " + c.getName() + "=" + c.getValue() + ", " + c.getMaxAge());
/*			for (Cookie c : cookies) {
				if (null != c) {
					log.info("doPost =====> " + c.getName() + ": " + c.getValue());
				}
			}*/
			
			
			// get / post 处理逻辑
			// 请求对象 - 设置属性
			//request.setAttribute("user", user);
			
			// 转发到指定 Servlet 或 Jsp 页面 (传递 请求和响应 对象)
			// forwardUrl = "/front/el/ExpLang.jsp";
			// request.getRequestDispatcher(forwardUrl).forward(request, response);
			
			// 重定向 (通知客户端，根据本次响应提供的地址再次发请求)
			// redirectUrl = "";
			// response.sendRedirect(redirectUrl);
		} catch (Exception e)
		{
			log.error("doPost =====> ", e);
		}
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
