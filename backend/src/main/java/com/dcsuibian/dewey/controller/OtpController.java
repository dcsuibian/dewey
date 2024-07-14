package com.dcsuibian.dewey.controller;

import com.dcsuibian.dewey.entity.Otp;
import com.dcsuibian.dewey.service.UserService;
import com.dcsuibian.dewey.vo.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final UserService userService;

    @Autowired
    public OtpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseWrapper<Void> send(@RequestBody Otp otp) {
        switch (otp.getPurpose()) {
            case REGISTER -> userService.preRegister(otp.getReceiver());
            case LOGIN -> userService.preLogin(otp.getReceiver());
        }
        return ResponseWrapper.success(null, 201);
    }
}
