package cn.wenhe9.chat_system.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DuJinliang
 * 2022/5/20
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * 正确执行后的返回
     * */
    OK("00000","一切 ok"),

    FAIL("A0001", "出错啦"),

    LOGIN_FAIL("A0200", "用户名或密码错误");

    /**
     * 错误码
     * */
    private String code;

    /**
     * 中文描述
     * */
    private String message;
}
