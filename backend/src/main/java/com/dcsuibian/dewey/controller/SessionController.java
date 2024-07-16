package com.dcsuibian.dewey.controller;

import com.dcsuibian.dewey.entity.User;
import com.dcsuibian.dewey.service.UserService;
import com.dcsuibian.dewey.vo.LoginVo;
import com.dcsuibian.dewey.vo.ResponseWrapper;
import com.dcsuibian.dewey.vo.SessionVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    private final UserService userService;

    @Autowired
    public SessionController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取当前会话
     */
    @GetMapping
    public ResponseWrapper<SessionVo> get(HttpSession httpSession) {
        SessionVo sessionVo = (SessionVo) httpSession.getAttribute("session");
        return ResponseWrapper.success(sessionVo);
    }

    @PostMapping
    public ResponseWrapper<SessionVo> login(HttpSession httpSession, @RequestBody LoginVo loginVo) {
        User user;
        if (LoginVo.Type.PHONE_NUMBER_AND_PASSWORD.equals(loginVo.getType())) {
            user = userService.loginByPhoneNumberAndPassword(loginVo.getPhoneNumber(), loginVo.getPassword());
        } else if (LoginVo.Type.PHONE_NUMBER_AND_VERIFICATION_CODE.equals(loginVo.getType())) {
            user = userService.loginByPhoneNumberAndVerificationCode(loginVo.getPhoneNumber(), loginVo.getVerificationCode());
        } else {
            return ResponseWrapper.fail("不支持的登录方式", 400);
        }
        SessionVo sessionVo = new SessionVo();
        sessionVo.setUser(user);
        httpSession.setAttribute("session", sessionVo); // 替换掉原来的SessionVo
        return ResponseWrapper.success(sessionVo, 201);
    }

    @DeleteMapping
    public ResponseWrapper<Void> logout(HttpSession httpSession) {
        httpSession.invalidate();
        return ResponseWrapper.success();
    }
}
