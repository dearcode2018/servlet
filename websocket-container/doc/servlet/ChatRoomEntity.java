/**
 * ChatRoomEntity.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.rtc.servlet;

import java.util.Date;

/**
 * ChatRoomEntity
 * 描述: 聊天房间 - 实体
 * @author  qye.zheng
 */
public final class ChatRoomEntity
{
	
	/* 房间id */
	private String id;
	
	/* 房间名称 */
	private String name;
	
	/* 房间密码 */
	private String password;
	
	/* 创建者 */
	private String creater;
	
	/* 创建日期时间 */
	private Date createDt;
	
	/* 状态 (ON | OFF) */
	private String status;
	
	/* 备注信息 */
	private String remark;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public ChatRoomEntity()
	{
	}

	/**
	 * @return the id
	 */
	public final String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public final String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the creater
	 */
	public final String getCreater()
	{
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public final void setCreater(String creater)
	{
		this.creater = creater;
	}

	/**
	 * @return the createDt
	 */
	public final Date getCreateDt()
	{
		return createDt;
	}

	/**
	 * @param createDt the createDt to set
	 */
	public final void setCreateDt(Date createDt)
	{
		this.createDt = createDt;
	}

	/**
	 * @return the status
	 */
	public final String getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @return the remark
	 */
	public final String getRemark()
	{
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public final void setRemark(String remark)
	{
		this.remark = remark;
	}

}
