package com.huzheng.commoms.websockets;

import com.huzheng.entity.Customer;
import com.huzheng.entity.Login;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author 胡正
 * @Date 2020/3/27 12:22
 * @Description 聊天系统服务端，拦截器
 */
public class ServerHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        System.out.println("握手之前");

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {

                Object userInfo = session.getAttribute("userInfo");
                System.out.println("聊天username:"+userInfo);
                if (userInfo!=null) {

                    if (userInfo instanceof Login) {
                       Login login = (Login)userInfo;
                       attributes.put("WEBSOCKET_USERID",login.getUsername());
                    }else {
                        Customer customer =(Customer)userInfo;
                        attributes.put("WEBSOCKET_USERID",customer.getUsername());
                    }

                }

            }
        }

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("握手之后");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
