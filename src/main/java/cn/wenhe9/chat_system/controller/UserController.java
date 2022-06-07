package cn.wenhe9.chat_system.controller;

import cn.wenhe9.chat_system.domain.User;
import cn.wenhe9.chat_system.result.ResultCodeEnum;
import cn.wenhe9.chat_system.result.ResultResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author DuJinliang
 * 2022/6/6
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public ResultResponse login(@RequestBody User user, HttpSession session){
        if (user != null && "123".equals(user.getPassword())){
            session.setAttribute("user", user.getUsername());
            return ResultResponse.ok(user.getUsername());
        }else {
            return ResultResponse.fail(ResultCodeEnum.LOGIN_FAIL);
        }
    }

    @GetMapping("/username")
    public ResultResponse getUsername(HttpSession session){
        return ResultResponse.ok(session.getAttribute("user"));
    }
}
