package com.hua.bean.sys;

import java.io.Serializable;
import java.util.Date;

import com.hua.entity.User;

/**
 * @description:
 * @date:
 * @author:钟伟雄
 */
public class LoginUser implements Serializable
{
	private static final long serialVersionUID = 644642498360427983L;
	private String id;
	private String code;
	private String name;
	private Date loginDt;
	private String sessionId;
	private String token;
	private String loginLogId;
	private String client;
	private int maxInactiveInterval;
	private User user;
	private String fsDomain;// 文件服务器域名

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getLoginDt()
	{
		return loginDt;
	}

	public void setLoginDt(Date loginDt)
	{
		this.loginDt = loginDt;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getLoginLogId()
	{
		return loginLogId;
	}

	public void setLoginLogId(String loginLogId)
	{
		this.loginLogId = loginLogId;
	}

	public String getClient()
	{
		return client;
	}

	public void setClient(String client)
	{
		this.client = client;
	}

	public int getMaxInactiveInterval()
	{
		return maxInactiveInterval;
	}

	public void setMaxInactiveInterval(int maxInactiveInterval)
	{
		this.maxInactiveInterval = maxInactiveInterval;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getFsDomain()
	{
		return fsDomain;
	}

	public void setFsDomain(String fsDomain)
	{
		this.fsDomain = fsDomain;
	}

	@Override
	public String toString()
	{
		return "LoginUser [id=" + id + ", code=" + code + ", name=" + name + ", loginDt=" + loginDt + ", sessionId=" + sessionId + ", token=" + token + ", loginLogId=" + loginLogId + ", client="
				+ client + ", maxInactiveInterval=" + maxInactiveInterval + ", user=" + user + ", fsDomain=" + fsDomain + "]";
	}
}
