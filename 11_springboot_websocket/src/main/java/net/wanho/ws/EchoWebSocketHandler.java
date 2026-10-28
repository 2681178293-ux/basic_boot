package net.wanho.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 原生 WebSocket 入门：你发什么，服务端回 echo: 原文。
 * 用来先搞懂：握手 → 收消息 → 发消息 → 断开。
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("[Echo] connected id=" + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // session = 刚才发消息的那个人；Echo 只要回给他，所以直接 session.sendMessage
        String payload = message.getPayload();
        session.sendMessage(new TextMessage("echo: " + payload));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("[Echo] closed id=" + session.getId() + " status=" + status);
    }
}
