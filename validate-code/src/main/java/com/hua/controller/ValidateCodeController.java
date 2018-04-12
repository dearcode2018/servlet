/**
  * @filename ValidateCodeController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hua.validate.ValidateCode;

 /**
 * @type ValidateCodeController
 * @description 
 * @author qianye.zheng
 */
@Controller
@RequestMapping("/validate")
public class ValidateCodeController extends BaseController
{
	
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @author qianye.zheng
	 */
	@RequestMapping("/code/v1")
	public void validateCodeV1(final HttpServletRequest request, final HttpServletResponse response)
	{
		final ValidateCode code = new ValidateCode();
		try
		{
			code.getValidateCode(response.getOutputStream());
		} catch (IOException e)
		{
			log.error("异常: ", e);
		}
		log.info("validateCodeV1 =====> " + code.getRandomString());
	}
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @author qianye.zheng
	 */
	@RequestMapping("/code/v2")
	public void validateCodeV2(final HttpServletRequest request, final HttpServletResponse response)
	{
		final ValidateCode code = new ValidateCode();
		try
		{
			String rCode = "1 + 3 = ";
			 rCode = "afasdfasdfasdfasdfasdfasdf23e423423&&^#@@#$$^&&";
			code.getValidateCode(response.getOutputStream(), rCode);
		} catch (IOException e)
		{
			log.error("异常: ", e);
		}
		log.info("validateCodeV2 =====> " + code.getRandomString());
	}
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @author qianye.zheng
	 */
	@RequestMapping("/code/v3")
	public void validateCodeV3(final HttpServletRequest request, final HttpServletResponse response)
	{
		final ValidateCode code = new ValidateCode();
		try
		{
			String rCode = "1 + 3 = ";
			code.getValidateCode(response.getOutputStream(), rCode);
		} catch (IOException e)
		{
			log.error("异常: ", e);
		}
		log.info("validateCodeV2 =====> " + code.getRandomString());
	}
	
}
