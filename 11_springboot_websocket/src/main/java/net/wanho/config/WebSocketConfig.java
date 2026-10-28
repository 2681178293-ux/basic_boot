package net.wanho.config;

import net.wanho.ws.CsWebSocketHandler;
import net.wanho.ws.EchoWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 课堂演示放开跨域；生产改成具体前端域名
        registry.addHandler(new EchoWebSocketHandler(), "/ws/echo")
                .setAllowedOrigins("*");
        registry.addHandler(new CsWebSocketHandler(), "/ws/cs")
                .setAllowedOrigins("*");
    }
}
