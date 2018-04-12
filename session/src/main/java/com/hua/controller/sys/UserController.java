/**
  * @filename UserController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller.sys;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hua.bean.ResultBean;
import com.hua.bean.sys.LoginUser;
import com.hua.constant.UriConstant;
import com.hua.constant.WebConstant;
import com.hua.controller.BaseController;
import com.hua.entity.User;
import com.hua.service.UserService;
import com.hua.util.WebUtil;

 /**
 * @type UserController
 * @description  
 * @author qye.zheng
 */
@Controller
@RequestMapping(UriConstant.API + "sys")
public final class UserController extends BaseController
{
	
	public static final Map<String, LoginUser> loginUserMap = 
			new HashMap<String, LoginUser>();
	
	@Resource
	private UserService userService;
	
	private HttpSession session1;
	
	private HttpSession session2;
	
	@RequestMapping("login")
	//@ResponseBody
	public final String login(final HttpServletRequest request, 
			final HttpServletResponse response, final User user)
	{
		String resultUrl = "/views/login.html";
		Cookie cookie = WebUtil.findCookie(request, 
				WebConstant.DEFAULT_SESSION_ID_NAME);
		String sessionId = null;
		if (null != cookie)
		{
			System.out.println("获取到指定的cookie");
			sessionId = cookie.getValue();
			System.out.println("sessinoId = " + sessionId);
		}
		
		if (null != loginUserMap.get("LoginUser-" + sessionId))
		{
			resultUrl = "/views/login-success.html";
			
			return resultUrl;
		}
		
		if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword()))
		{
			log.info("login =====> 用户名和密码验证成功!");
			final HttpSession session = request.getSession();
			// 生成自定义的sessionId
			LoginUser loginUser = new LoginUser();
			loginUser.setSessionId(UUID.randomUUID() + session.getId());
			loginUser.setCode(user.getUsername());
			loginUser.setUser(user);
			
			// 将Cookie存储到客户端
			Cookie sessionIdCookie = new Cookie(WebConstant.DEFAULT_SESSION_ID_NAME,
					loginUser.getSessionId());
			
			int seconds = 1 * 24 * 60 * 60 ;
			sessionIdCookie.setDomain("127.0.0.1");
			sessionIdCookie.setPath("/");
			sessionIdCookie.setMaxAge(seconds);
			response.addCookie(sessionIdCookie);
			
			loginUserMap.put("LoginUser-" + loginUser.getSessionId(), loginUser);
			
			resultUrl = "/views/login-success.html";
		}
		
		// 返回视图的相对路径
		return resultUrl;
	}

	@RequestMapping("logout")
	public final String logout(final HttpServletRequest request, 
			final HttpServletResponse response, final User user)
	{
		String resultUrl = "/views/login.html";
		Cookie cookie = WebUtil.findCookie(request, 
				WebConstant.DEFAULT_SESSION_ID_NAME);
		String sessionId = null;
		if (null != cookie)
		{
			sessionId = cookie.getValue();
			System.out.println("sessinoId = " + sessionId);
			loginUserMap.remove("LoginUser-" + sessionId);
			//Cookie rCookie  = new Cookie(WebConstant.DEFAULT_SESSION_ID_NAME, null);
			cookie.setValue(null);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		}
		request.getSession().invalidate();
		
		return resultUrl;
	}
	
	@RequestMapping("listUser")
	public final ResultBean listUser(final HttpServletRequest request, 
			final HttpServletResponse response, final User user)
	{
		System.out.println("UserController.listUser()");
		return null;
	}

	@RequestMapping("testSession1")
	public final ResultBean testSession1(final HttpServletRequest request, 
			final HttpServletResponse response, final User user)
	{
		// javax.servlet.http.HttpSession
		session1 = request.getSession();
		// D668902EDC04428968C3985B0E8901EF
		log.info("testSession1 =====> sessionId = " + session1.getId());
		
		
		
		return null;
	}
	
	@RequestMapping("testSession2")
	public final ResultBean testSession2(final HttpServletRequest request, 
			final HttpServletResponse response, final User user)
	{
		// javax.servlet.http.HttpSession
		session2 = request.getSession();
		// D668902EDC04428968C3985B0E8901EF
		log.info("testSession2 =====> sessionId = " + session2.getId());
		
		log.info("testSession2 =====> equals = " + session1.equals(session2));
		
		
		return null;
	}
	
	@RequestMapping("")
	public final ResultBean temp(final HttpServletRequest request, 
			final HttpServletResponse response, final User user)
	{
		return null;
	}
	
}
