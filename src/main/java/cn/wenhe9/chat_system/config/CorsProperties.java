package cn.wenhe9.chat_system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 跨域配置属性
 *
 * @author DuJinliang
 * 2022/5/18
 */
@ConfigurationProperties(prefix = "chat.cors")
@Data
public class CorsProperties {

    /**
     * 允许跨域的域名
     * */
    private List<String> allowOrigins;
}
