package cn.wenhe9.chat_system.domain;

import lombok.Data;

/**
 * @author DuJinliang
 * 2022/6/6
 */
@Data
public class ResultMessage {
    private boolean isSystem;

    private String fromName;

    /**
     * 如果是系统消息是数组
     */
    private Object message;
}
