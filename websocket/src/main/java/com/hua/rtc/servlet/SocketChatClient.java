/**
 * SocketChatClient.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.rtc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hua.util.DateTimeUtil;
import com.sun.swing.internal.plaf.basic.resources.basic;

/**
 * SocketChatClient 
 * 描述: 聊天客户端
 * 
 * @author qye.zheng
 */
@ServerEndpoint("/ws/chat/socket")
public final class SocketChatClient
{

	/**
	
	 C/S协调要点: 
	 1) 通过 WebSocket 来构建客户端和服务端的通讯，传输文本和基本信令.
	 2) 客户端音视频: 服务端下达信令，客户端直接建立对等连接，数据不经过过服务端.
	 
	 
	 聊天客户端
	 1) 每打开一个聊天客户端，则创建一个该对象，并触发相应的方法
	 2) 所有聊天客户端都在此管理
	 3) 不同房间的客户端根据唯一的房间编号来存放客户端对象
	 Map<String, Set<SocketChatClient>> key-房间编号，value 是在该房间的所有客户端
	 4) 消息: 公开消息-所有客户端可接收，房间内部消息-房间内客户端可接收，私聊消息-仅对方可接收
	 5) 
	 
	 聊天服务端
	 1) 维护所有客户端和房间的信息，并进行合适范围的广播 
	 2) 维护客户端和房间的关系(可以是 一对一 或者 一对多)
	 3) 发送调度指令
	 4) 进行会话安全和权限检查
	 5) 
	 
	 */

	private static final Log log = LogFactory.getLog(SocketChatClient.class);

	private static final String GUEST_PREFIX = "Guest";

	// 房间编号(唯一)
	private static final AtomicInteger connectionIds = new AtomicInteger(0);

	// 每个聊天客户端 (静态公共的)
	private static final Set<SocketChatClient> connections = new CopyOnWriteArraySet<>();

	// 客户端昵称
	private String nickname;

	// javax.websocket.Session
	private Session session;

	/**
	 * 
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public SocketChatClient()
	{
		System.out.println("SocketChatClient.SocketChatClient()");
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();

	}

	/**
	 * 
	 * 描述:  客户端进入房间
	 * @author  qye.zheng
	 * @param session
	 */
	@OnOpen
	public void start(final Session session)
	{
		System.out.println("SocketChatClient.start()");
		this.session = session;
		
		// 给客户端传递参数
		final RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
	
		try
		{
			log.info("start =====> 向客户端发送数据");
			// 设置
			basicRemote.sendText("success");
			basicRemote.flushBatch();
			//basciRemote.sendText(GUEST_PREFIX, false);
			//basciRemote.sendText(GUEST_PREFIX, true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		/**
		 	进入房间之前应该进行验证，验证通过再给房间广播消息
		 */
		final Map<String, List<String>> paramMap = session.getRequestParameterMap();
		
		final String roomId = paramMap.get("roomId").get(0);
		final String password = paramMap.get("password").get(0);
		System.out.println("roomId = " + roomId + ", password = " + password);
		// 房间验证
		if (authCheck(roomId, password))
		{
			// 验证通过
			log.info("start =====> 验证通过");
		} else
		{
			// 验证没有通过
			log.info("start =====> 验证没有通过");
			
			// 直接返回
			return;
		}
		
		/**
		 将当前对象放入客户端集合中
		 */
		connections.add(this);
		// 进入消息
		String message = String.format("* %s %s", nickname, "has joined.");
		// 广播
		broadcast(message);
	}

	/**
	 * 
	 * 描述: 客户端发送文本消息
	 * @author  qye.zheng
	 * @param message 客户端发送的文本消息
	 */
	@OnMessage
	public void incoming(final String message)
	{
		System.out.println("SocketChatClient.incoming()");
		log.info("incoming =====> message = " + message);
		// 过滤消息，可以过滤敏感字符
		// Never trust the client
		String filteredMessage = nickname + " ( "  + DateTimeUtil.format(DateTimeUtil.getDateTime()) + ")<br />" + message;
		// 广播
		broadcast(filteredMessage);
	}
	
	/**
	 * 
	 * 描述: 客户端退出房间
	 * @author  qye.zheng
	 */
	@OnClose
	public void end()
	{
		System.out.println("SocketChatClient.end()");
		/*
		 将当前对象从客户端集合中移除
		 */
		connections.remove(this);
		// 退出消息
		String message = String
				.format("* %s %s", nickname, "has disconnected.");
		// 广播
		broadcast(message);
	}

	/**
	 * 
	 * 描述:  发生严重错误/异常
	 * @author  qye.zheng
	 * @param t
	 * @throws Throwable
	 */
	@OnError
	public void onError(final Throwable t) throws Throwable
	{
		log.error("Chat Error: " + t.toString(), t);
	}

	/**
	 * 
	 * 描述: 消息广播
	 * @author  qye.zheng
	 * @param msg
	 */
	private static void broadcast(final String msg)
	{
		for (SocketChatClient client : connections)
		{
			try
			{
				synchronized (client)
				{
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e)
			{
				log.debug("Chat Error: Failed to send message to client", e);
				// 该客户端发生IO连接异常，将从客户端列表中移除
				connections.remove(client);
				try
				{
					// 关闭当前客户端的会话
					client.session.close();
				} catch (IOException e1)
				{
					// Ignore
				}
				// 广播的时候，提示某个客户端已经断开连接
				String message = String.format("* %s %s", client.nickname,
						"has been disconnected.");
				// 广播
				broadcast(message);
			}
		}
	}

	/**
	 * 
	 * 描述: 房间验证
	 * @author  qye.zheng
	 * @param roomId
	 * @param password
	 * @return
	 */
	private boolean authCheck(final String roomId, final String password)
	{
		boolean flag = false;
		// 密码暂时为  123456，roomId 可以是 1- 10
		int id = Integer.parseInt(roomId);
		if (id >= 0 && id <= 10 && "123456".equals(password))
		{
			flag = true;
		}
		
		return flag;
	}

}
