package cn.wenhe9.chat_system.ws;

import cn.wenhe9.chat_system.domain.Message;
import cn.wenhe9.chat_system.utils.MessageUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DuJinliang
 * 2022/6/7
 */
@Slf4j
@Component
@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
public class ChatEndpoint {

    /**
     * 用来存储每一个客户端对象对应的ChatEndpoint对象
     */
    private static Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    /**
     * 声明session对象，通过该对象可以发送给指定的客户端
     */
    private Session session;

    /**
     * 声明一个HttpSession对象，之前在httpSession中存放了用户名
     */
    private HttpSession httpSession;

    /**
     * 连接建立时被调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        //将局部的session对象赋值给成员session
        this.session = session;
        //获取httpSession对象
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String username = (String) httpSession.getAttribute("user");
        log.info("用户{}上线了", username);
        //将当前对象存储到
        onlineUsers.put(username, this);

        //将当前在线的用户的用户名推送给所有的客户端
        //获取消息
        String message = MessageUtils.getMessage(true, null, getNames());
        //调用方法进行系统消息的推送
        broadcastAllUsers(message);
    }

    /**
     * 接收到客户端发送的数据被调用
     */
    @OnMessage
    public void onMessage(String message, Session session){
        try {
            //将message转换成Message
            Message mess = JSONObject.parseObject(message, Message.class);
            //要将数据发送给的用户
            String toName = mess.getToName();
            //获取消息数据
            String data = mess.getMessage();
            //获取当前登录的用户
            String fromName = (String) httpSession.getAttribute("user");
            //获取推送给指定用户的消息格式的数据
            String resultMessage = MessageUtils.getMessage(false, fromName, data);
            ChatEndpoint chatEndpoint = onlineUsers.get(toName);
            //发送数据
            chatEndpoint.session.getBasicRemote().sendText(resultMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭时被调用
     */
    @OnClose
    public void onClose(Session session){
        //从容器中删除指定的用户
        String username = (String) httpSession.getAttribute("user");
        log.info("用户{}下线了", username);
        onlineUsers.remove(username);
        //获取推送的消息
        String message = MessageUtils.getMessage(true, null, getNames());
        broadcastAllUsers(message);
    }

    /**
     * 获取所有用户名
     */
    private Set<String> getNames(){
        return onlineUsers.keySet();
    }

    /**
     * 将该消息推送给所有的客户端
     */
    private void broadcastAllUsers(String message){
        try {
            Set<String> names = onlineUsers.keySet();
            for (String name : names) {
                ChatEndpoint chatEndpoint = onlineUsers.get(name);
                chatEndpoint.session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
