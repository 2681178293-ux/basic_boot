package net.wanho.stomp;

import lombok.Data;

@Data
public class ChatMessage {
    /** 昵称 */
    private String from;
    /** 聊天内容 */
    private String content;
}
