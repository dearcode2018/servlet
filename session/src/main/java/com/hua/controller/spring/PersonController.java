/**
 * 描述: 
 * PersonController.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.controller.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hua.bean.ResultBean;
import com.hua.bean.spring.PersonSearchBean;
import com.hua.controller.BaseController;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * PersonController
 */
// 控制器
@Controller
@RequestMapping(value={"/PersonController"}, method = RequestMethod.GET)
//@RequestMapping(value={"/"})
public final class PersonController extends BaseController
{
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value={"/search"})
	@ResponseBody
	public ResultBean search(final HttpServletRequest request, 
			final HttpServletResponse response, final PersonSearchBean searchBean) {
		
		return null;
	}
	
}
