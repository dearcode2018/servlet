/**
 * 描述: 
 * UserServiceImpl.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.service.impl;

import com.hua.bean.ResultBean;
import com.hua.entity.User;
import com.hua.entity.UserLog;
import com.hua.service.UserService;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * UserServiceImpl
 */
public class UserServiceImpl extends CoreServiceImpl implements UserService {

	/**
	 * 描述: 用户注册
	 * @author qye.zheng
	 * @param user
	 * @return
	 */
	@Override
	public ResultBean register(final User user) {
		return null;
	}

	/**
	 * 描述: 用户登录
	 * @author qye.zheng
	 * @param login
	 * @return
	 */
	@Override
	public ResultBean login(final UserLog login) {
		return null;
	}

	/**
	 * 描述: 用户注销
	 * @author qye.zheng
	 * @param login
	 * @return
	 */
	@Override
	public ResultBean logout(final UserLog login) {
		return null;
	}
	
}
