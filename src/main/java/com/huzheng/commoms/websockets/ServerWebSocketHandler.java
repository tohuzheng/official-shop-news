package com.huzheng.commoms.websockets;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huzheng.commoms.websockets.dto.CustomerToAdmin;
import com.huzheng.commoms.websockets.dto.ReceiptAdminMsgDto;
import com.huzheng.commoms.websockets.dto.SystemMessageDto;
import com.huzheng.entity.Customer;
import com.huzheng.entity.Login;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 胡正
 * @Date 2020/3/27 10:23
 * @Description  聊天系统服务端，消息处理器
 */
public class ServerWebSocketHandler extends TextWebSocketHandler {

    /**
     * 存放客户的会话组,其中key为用户名
     */
    private static Map<String, WebSocketSession> customerMap = new ConcurrentHashMap<>();
    /**
     * 存放管理员的会话组，其中key为用户名
     */
    private static Map<String, WebSocketSession> adminMap = new ConcurrentHashMap<>();

    /**
     * 客服与顾客关联起来，key为客户名称，value为管理员名称
     */
    private static Map<String, String> relevance = new ConcurrentHashMap<>();

    /**
     * 上线客服集合
     */
    private static List<String> list = new LinkedList<>();

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
            list.add(((Login) userInfo).getUsername());
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
            list.remove(((Login) userInfo).getUsername());
            removeAllDiscord(((Login) userInfo).getUsername());
        }else {
            Customer customer =  (Customer)userInfo;
            customerMap.remove(customer.getUsername());
            relevance.remove(customer.getUsername());
        }
        System.out.println("关闭连接");
    }

    /**
     * 收到消息时，触发的方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        Object userInfo = attributes.get("userInfo");

        if (userInfo instanceof Login) {
            adminMessageHandler(message, ((Login) userInfo).getUsername());
        }else {
            customerMessageHandler(message, ((Customer)userInfo).getUsername());
        }

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
    private void everyBodyCustomerSend(TextMessage textMessage) throws IOException {
        Set<String> ckeys = customerMap.keySet();
        WebSocketSession webSocketSession=null;
        for (String sessionId:ckeys) {
            webSocketSession = customerMap.get(sessionId);
            webSocketSession.sendMessage(textMessage);
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/30 12:05
     * @description 给指定人发消息
     * @param name 接受消息的人
     * @param message 消息内容
     * @param toMsgUserType 接收消息人的类型，客服/顾客
     */
    private void sendOnePeople(String name, String message,String toMsgUserType){
        TextMessage textMessage = new TextMessage(message);
        WebSocketSession webSocketSession = null;
        if ("客服".equals(toMsgUserType)) {
            webSocketSession = adminMap.get(name);
        }
        if ("顾客".equals(toMsgUserType)){
            webSocketSession = customerMap.get(name);
        }

        if (webSocketSession != null) {
            try {
                webSocketSession.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/30 12:12
     * @description 处理客户向服务器发送的消息
     * @param textMessage 消息体
     * @param customerName 发送来消息的客户名称
     */
    private void customerMessageHandler(TextMessage textMessage,String customerName){
        if (!relevance.containsKey(customerName)) {
            // 没有客服为他服务，随机分配在线客服给他
            if (CollUtil.isEmpty(adminMap)){
                // 没有客服在线
                sendOnePeople(customerName, "您好！客服正在忙线中，您可以先尝试自助搜索喔！", "顾客");
            }else {
                // 有客服在线，分配客服
                String keFuName = getOneAdmin();
                if (keFuName == null) {
                    sendOnePeople(customerName, "您好！客服正在忙线中，您可以先尝试自助客服喔！", "顾客");
                }else {
                    // 建立服务关系
                    relevance.put(customerName, keFuName);
                    sendOnePeople(customerName, "您好！6379号客服为您服务，请问您有什么需要帮助的吗？", "顾客");
                }
            }
        }else {
            // 已有客服为他服务，把消息转发给它的客服
            CustomerToAdmin dto = new CustomerToAdmin();
            dto.setCustomerName(customerName);
            dto.setMsg(textMessage.getPayload());
            String msgDto = JSON.toJSONString(dto);
            sendOnePeople(relevance.get(customerName),msgDto, "客服");

        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/30 12:13
     * @description 处理管理员发来的消息
     * @param textMessage 消息体
     * @param adminName 管理员的名称
     */
    private void adminMessageHandler(TextMessage textMessage,String adminName){
        JSONObject jsonObject = JSON.parseObject(textMessage.getPayload());
        String customerName = (String)jsonObject.get("toCustomerName");
        String msg = (String)jsonObject.get("msg");

        if (!customerMap.containsKey(customerName)) {
            // 顾客已下线,回复管理员
            SystemMessageDto dto = new SystemMessageDto();
            dto.setMsg("客户已下线，不需要处理！");
            dto.setCustomerName(customerName);
            dto.setOffLine("离线");
            String msgDto = JSON.toJSONString(dto);
            sendOnePeople(adminName, msgDto, "客服");
        }else {
            // 顾客在线，转发消息给顾客
            sendOnePeople(customerName, msg, "顾客");
        }

    }

    /**
     * @author zheng.hu
     * @date 2020/3/30 12:48
     * @description 获取一个客服
     * @param
     */
    private String getOneAdmin () {

        int count = 0;
        if (list.size() == 0) {
            return null;
        }else {
            if (count>=list.size()-1){
                if (list.size() == 1){
                    return list.get(0);
                }
                int index = count%list.size();
                count++;
                return list.get(index);
            }else {
                count++;
               return list.get(count);
            }
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/30 18:15
     * @description 清理下线客服的关联关系
     * @param keFuName
     */
    private void removeAllDiscord(String keFuName){
        Set<String> keys = relevance.keySet();
        for (String k:keys){
            if (relevance.get(k).equals(keFuName)){
                relevance.remove(k);
            }
        }
    }

}
