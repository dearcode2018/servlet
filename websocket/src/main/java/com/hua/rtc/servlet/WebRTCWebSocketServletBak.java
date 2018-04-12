/**
 * WebRTCWebSocketServlet.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.rtc.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * WebRTCWebSocketServlet
 * 描述: 
 * @author  qye.zheng
 */
//@ServerEndpoint("/akk/abc")
public class WebRTCWebSocketServletBak
{
	private static final Log log = LogFactory.getLog(WebRTCWebSocketServletBak.class);

    private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<WebRTCWebSocketServletBak> connections =
            new CopyOnWriteArraySet<>();

    private final String nickname;
    
    // javax.websocket.Session
    private Session session;

    public WebRTCWebSocketServletBak() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }


    @OnOpen
    public void start(final Session session) {
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        broadcast(message);
    }


    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s",
                nickname, "has disconnected.");
        broadcast(message);
    }


    @OnMessage
    public void incoming(final String message) {
        // Never trust the client
        String filteredMessage = "haha";
        
        broadcast(filteredMessage);
    }




    @OnError
    public void onError(final Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }


    private static void broadcast(final String msg) {
        for (WebRTCWebSocketServletBak client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }
}
