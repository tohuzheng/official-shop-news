package com.huzheng.config;

import com.huzheng.commoms.websockets.ServerHandshakeInterceptor;
import com.huzheng.commoms.websockets.ServerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

/**
 * @Author 胡正
 * @Date 2020/3/27 10:12
 * @Description spring的WebSocket配置
 */
@Configuration
@EnableWebSocket
public class SpringWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        // 添加拦截器以及相应的websocket消息处理器
        WebSocketHandlerRegistration registration = registry.addHandler(serverWebSocketHandler(),
                "/websocket").setAllowedOrigins("*");

        // 添加拦截器
        registration.addInterceptors(serverHandshakeInterceptor());
    }

    /**
     * 把websocket处理器加载到bean容器中
     */
    @Bean
    public ServerWebSocketHandler serverWebSocketHandler(){
        return new ServerWebSocketHandler();
    }

    /**
     * 把websocket拦截器加载到bean容器中
     */
    @Bean
    public ServerHandshakeInterceptor serverHandshakeInterceptor(){
        return new ServerHandshakeInterceptor();
    }
}
