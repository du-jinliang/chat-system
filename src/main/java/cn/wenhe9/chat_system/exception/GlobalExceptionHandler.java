package cn.wenhe9.chat_system.exception;

import cn.wenhe9.chat_system.result.ResultCodeEnum;
import cn.wenhe9.chat_system.result.ResultResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author DuJinliang
 * 2022/6/7
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultResponse<Void> handleException(Exception e){
        e.printStackTrace();
        return ResultResponse.fail(ResultCodeEnum.FAIL);
    }
}
