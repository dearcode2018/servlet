/**
 * 描述: 
 * UserService.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.service;

import com.hua.bean.ResultBean;
import com.hua.entity.User;
import com.hua.entity.UserLog;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * UserService
 */
public interface UserService extends CoreService {
	
	/**
	 * 
	 * 描述: 用户注册
	 * 
	 * @author qye.zheng
	 * @param login
	 * @return
	 */
	public ResultBean register(final User user);
	
	/**
	 * 
	 * 描述: 用户登录 
	 * 
	 * @author qye.zheng
	 * @param login
	 * @return
	 */
	public ResultBean login(final UserLog login);
	
	/**
	 * 
	 * 描述: 用户注销
	 * 
	 * @author qye.zheng
	 * @param login
	 * @return
	 */
	public ResultBean logout(final UserLog login);
}
