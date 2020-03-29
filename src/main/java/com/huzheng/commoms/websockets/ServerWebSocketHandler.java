package com.huzheng.commoms.websockets;

import com.huzheng.entity.Customer;
import com.huzheng.entity.Login;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author 胡正
 * @Date 2020/3/27 10:23
 * @Description  聊天系统服务端，消息处理器
 */
public class ServerWebSocketHandler extends TextWebSocketHandler {

    /**
     * 存放客户的会话组,其中key为用户名
     */
    private Map<String, WebSocketSession> customerMap = new HashMap<>();
    /**
     * 存放管理员的会话组，其中key为用户名
     */
    private Map<String, WebSocketSession> adminMap = new HashMap<>();

    public ServerWebSocketHandler() {}

    /**
     * 连接成功时，触发的方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        System.out.println("用户信息："+attributes);
        Object userInfo = attributes.get("userInfo");
        if (userInfo instanceof Login) {
            adminMap.put((String) attributes.get("WEBSOCKET_USERID"), session);
        }else {
            customerMap.put((String) attributes.get("WEBSOCKET_USERID"), session);
        }
        System.out.println("成功建立websocket连接!");
    }

    /**
     * 关闭连接时，触发的方法
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        Object userInfo = attributes.get("userInfo");
        if (userInfo instanceof Login) {
            adminMap.remove(((Login) userInfo).getUsername());
        }else {
            Customer customer =  (Customer)userInfo;
            customerMap.remove(customer.getUsername());
        }
        System.out.println("关闭连接");
    }

    /**
     * 收到消息时，触发的方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("服务器收到消息："+message.toString());
        TextMessage text=new TextMessage("服务器转发："+message.getPayload());
        everyBodySend(text);
        super.handleTextMessage(session, message);

    }

    /**
     * 传输消息异常时，触发的方法
     */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("传输出现异常，关闭websocket连接... ");
    }

    /**
     * 是否处理分片消息
     */
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给所有人客户发消息
     */
    private void everyBodySend(TextMessage textMessage) throws IOException {
        Set<String> ckeys = customerMap.keySet();
        WebSocketSession webSocketSession=null;
        for (String sessionId:ckeys) {
            webSocketSession = customerMap.get(sessionId);
            webSocketSession.sendMessage(textMessage);
        }
    }


}
