package cn.wenhe9.chat_system.utils;

import cn.wenhe9.chat_system.domain.ResultMessage;
import com.alibaba.fastjson.JSONObject;

/**
 * @author DuJinliang
 * 2022/6/6
 */
public class MessageUtils {
    public static String getMessage(boolean isSystemMessage, String fromName, Object message){
        ResultMessage result = new ResultMessage();
        result.setSystem(isSystemMessage);
        result.setMessage(message);
        if (fromName != null){
            result.setFromName(fromName);
        }
        return JSONObject.toJSONString(result);
    }
}
