package cn.wenhe9.chat_system.result;

import lombok.Getter;

import java.util.Objects;

/**
 * @author DuJinliang
 * 2022/6/6
 */
@Getter
public class ResultResponse<T> {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private ResultResponse() {
        this.code = ResultCodeEnum.OK.getCode();
        this.message = ResultCodeEnum.OK.getMessage();
    }

    private ResultResponse(ResultCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    private ResultResponse(T data) {
        this();
        this.data = data;
    }

    /**
     * 业务处理成功,无数据返回
     */
    public static ResultResponse<Void> ok() {
        return new ResultResponse<>();
    }

    /**
     * 业务处理成功，有数据返回
     */
    public static <T> ResultResponse<T> ok(T data) {
        return new ResultResponse<>(data);
    }

    /**
     * 业务处理失败
     */
    public static ResultResponse<Void> fail(ResultCodeEnum errorCode) {
        return new ResultResponse<>(errorCode);
    }


    /**
     * 系统错误
     */
    public static ResultResponse<Void> error() {
        return new ResultResponse<>(ResultCodeEnum.FAIL);
    }

    /**
     * 判断是否成功
     */
    public boolean isOk() {
        return Objects.equals(this.code, ResultCodeEnum.OK.getCode());
    }

}