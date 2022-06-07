package cn.wenhe9.chat_system.domain;

import lombok.Data;

/**
 * @author DuJinliang
 * 2022/6/6
 */
@Data
public class Message {
    private String toName;

    private String message;
}
