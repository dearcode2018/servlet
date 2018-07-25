/**
 * MessageWebSocket.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MessageWebSocket
 * 描述: 
 * @author  qye.zheng
 */
@ServerEndpoint("/ws/message")
public class MessageWebSocket
{
	
	
	/**
	 * 
	 * 推送给登录的用户，可以以用户令牌作为key，推送给指定的用户.
	 * 
	 * Map<String, Session>
	 * 
	 * 然后推送给相应的用户
	 * 
	 * 一个用户登录一次则创建一个会话
	 * 刷新浏览器则关闭上一个的WebSocket会话，开启一个新的会话
	 * 
	 * 
	 * 
	 */
	
	private static final Log log = LogFactory.getLog(MessageWebSocket.class);
    
    // javax.websocket.Session
    private Session session;
    
    /*  */
    public static final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<Session>();

    /**
     * 
     * @description 构造方法
     * @author qianye.zheng
     */
    public MessageWebSocket() {
    	System.out.println("MessageWebSocket.WebRTCWebSocketServlet()");
    }

    /**
     * 
     * @description 
     * @param session
     * @author qianye.zheng
     */
    @OnOpen
    public void start(final Session session) {
    	System.out.println("MessageWebSocket.start()");
        this.session = session;
        sessions.add(session);
    }

    /**
     * 
     * @description 
     * @author qianye.zheng
     */
    @OnClose
    public void end(final Session session) {
    	sessions.remove(session);
    	System.out.println("close socket");
    }

    /**
     * 
     * @description 
     * @param message
     * @author qianye.zheng
     */
    @OnMessage
    public void incoming(final Session session, final String message) {
    	System.out.println("MessageWebSocket.incoming(), message = " + message);

    }

    /**
     * 
     * @description 
     * @param t
     * @throws Throwable
     * @author qianye.zheng
     */
    @OnError
    public void onError(final Throwable t) throws Throwable {
        log.error("Error: " + t.toString(), t);
    }


}
