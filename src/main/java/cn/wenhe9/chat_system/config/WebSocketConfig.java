package cn.wenhe9.chat_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

/**
 * @author DuJinliang
 * 2022/6/7
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注入ServerEndpointExporter bean对象，自动注册使用了@ServerEndpoint的bean
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
