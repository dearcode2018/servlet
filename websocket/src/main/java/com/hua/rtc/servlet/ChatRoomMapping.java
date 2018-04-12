/**
 * ChatRoomMapping.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.rtc.servlet;

import java.util.Map;
import java.util.Set;

/**
 * ChatRoomMapping
 * 描述: 聊天房间 - 映射
 * 房间和客户端的映射关系
 * @author  qye.zheng
 */
public final class ChatRoomMapping
{

	/* 房间id */
	private String id;
	
	/* 一个房间对应多个客户端 */
	private Set<ChatClient> chatClients;
	
	/* 房间编号和客户端的一对多映射 */
	private Map<String, Set<ChatClient>> roomMap;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public ChatRoomMapping()
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
	 * @return the chatClients
	 */
	public final Set<ChatClient> getChatClients()
	{
		return chatClients;
	}

	/**
	 * @param chatClients the chatClients to set
	 */
	public final void setChatClients(Set<ChatClient> chatClients)
	{
		this.chatClients = chatClients;
	}

	/**
	 * @return the roomMap
	 */
	public final Map<String, Set<ChatClient>> getRoomMap()
	{
		return roomMap;
	}

	/**
	 * @param roomMap the roomMap to set
	 */
	public final void setRoomMap(Map<String, Set<ChatClient>> roomMap)
	{
		this.roomMap = roomMap;
	}

}
