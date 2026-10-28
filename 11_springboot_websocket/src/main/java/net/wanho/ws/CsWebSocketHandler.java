package net.wanho.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 客服一对一（课堂精简版）。
 * <p>
 * 连接：/ws/cs?role=customer&name=张三  或  /ws/cs?role=agent&name=客服小王
 * <p>
 * 和 Echo 的区别（一定要看懂）：
 * Echo 是“发给刚才说话的那个人” → 用方法参数 session 就够了。
 * 客服是“顾客说的话要转给客服” → 必须自己记住两个人的连接，所以多了两个字段。
 */
public class CsWebSocketHandler extends TextWebSocketHandler {

    /*
     * 方法参数里的 session = 这一次事件对应的那个人（刚连上 / 刚说话 / 刚断开）。
     * 框架每次回调都会传进来，回调结束就“用完了”，下次不一定还是同一个人。
     *
     * 字段里的 customerSession / agentSession = 我们自己贴的标签：
     * “谁是顾客、谁是客服”，方便以后把消息转给对方。
     * 赋值那一刻：customerSession = session，两者指向同一个连接对象。
     */
    /** 贴了“顾客”标签的那条连接（课堂只留 1 个） */
    private WebSocketSession customerSession;
    private String customerName;

    /** 贴了“客服”标签的那条连接（课堂只留 1 个） */
    private WebSocketSession agentSession;
    private String agentName;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // session：此刻刚连上来的这个人
        String role = getParam(session, "role");
        String name = getParam(session, "name");

        if ("customer".equals(role)) {
            // 记住：这个人是顾客（后面客服说话时，要往这里发）
            customerSession = session;
            customerName = name == null || name.isBlank() ? "顾客" : name;

            // 回给“刚连上的这个人” → 用参数 session 即可（和 Echo 一样）
            session.sendMessage(new TextMessage("系统：你是顾客【" + customerName + "】"));

            if (agentSession != null && agentSession.isOpen()) {
                // 通知对方（客服）→ 必须用字段 agentSession，参数 session 是顾客自己
                agentSession.sendMessage(new TextMessage("系统：顾客【" + customerName + "】已上线"));
                session.sendMessage(new TextMessage("系统：已接通客服【" + agentName + "】"));
            } else {
                session.sendMessage(new TextMessage("系统：等待客服上线…"));
            }
            return;
        }

        if ("agent".equals(role)) {
            // 记住：这个人是客服（后面顾客说话时，要往这里发）
            agentSession = session;
            agentName = name == null || name.isBlank() ? "客服" : name;

            session.sendMessage(new TextMessage("系统：你是客服【" + agentName + "】"));

            if (customerSession != null && customerSession.isOpen()) {
                // 通知对方（顾客）→ 用字段 customerSession
                customerSession.sendMessage(new TextMessage("系统：客服【" + agentName + "】已上线"));
                session.sendMessage(new TextMessage("系统：已接入顾客【" + customerName + "】"));
            } else {
                session.sendMessage(new TextMessage("系统：等待顾客上线…"));
            }
            return;
        }

        session.sendMessage(new TextMessage("系统：role 只能是 customer 或 agent"));
        session.close(CloseStatus.BAD_DATA);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // session：此刻正在说话的人；Echo 里直接 session.sendMessage 回给他就行
        // 这里要转给“另一个人”，所以看 session 是顾客还是客服，再往对方字段上发
        String text = message.getPayload();

        if (session == customerSession) {
            // 说话的是顾客 → 消息发给客服（对方），不是发回 session 自己
            if (agentSession == null || !agentSession.isOpen()) {
                session.sendMessage(new TextMessage("系统：客服不在线"));
                return;
            }
            agentSession.sendMessage(new TextMessage(customerName + "：" + text));
            return;
        }

        if (session == agentSession) {
            // 说话的是客服 → 消息发给顾客（对方）
            if (customerSession == null || !customerSession.isOpen()) {
                session.sendMessage(new TextMessage("系统：顾客不在线"));
                return;
            }
            customerSession.sendMessage(new TextMessage(agentName + "：" + text));
            return;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // session：此刻断开的人；要通知的是还在线的另一个人 → 用字段
        if (session == customerSession) {
            customerSession = null;
            customerName = null;
            if (agentSession != null && agentSession.isOpen()) {
                agentSession.sendMessage(new TextMessage("系统：顾客已离开"));
            }
            return;
        }
        if (session == agentSession) {
            agentSession = null;
            agentName = null;
            if (customerSession != null && customerSession.isOpen()) {
                customerSession.sendMessage(new TextMessage("系统：客服已离开"));
            }
        }
    }

    /** 从 ?role=xx&name=yy 里取参数 */
    private String getParam(WebSocketSession session, String key) {
        if (session.getUri() == null || session.getUri().getQuery() == null) {
            return null;
        }
        for (String pair : session.getUri().getQuery().split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2 && key.equals(kv[0])) {
                return java.net.URLDecoder.decode(kv[1], java.nio.charset.StandardCharsets.UTF_8);
            }
        }
        return null;
    }
}
