package com.dcsuibian.dewey.controller;

import com.dcsuibian.dewey.entity.User;
import com.dcsuibian.dewey.exception.BusinessException;
import com.dcsuibian.dewey.service.UserService;
import com.dcsuibian.dewey.vo.PageWrapper;
import com.dcsuibian.dewey.vo.RegisterVo;
import com.dcsuibian.dewey.vo.ResponseWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseWrapper<PageWrapper<User>> get(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession httpSession) {
        PageWrapper<User> page = userService.getPublic(pageNumber, pageSize);
        return ResponseWrapper.success(page);
    }

    @PostMapping
    public ResponseWrapper<Void> register(@RequestBody RegisterVo registerVo) {
        if (RegisterVo.Type.PHONE_NUMBER_AND_VERIFICATION_CODE == registerVo.getType()) {
            userService.registerByPhoneNumberAndVerificationCode(registerVo.getPhoneNumber(), registerVo.getVerificationCode());
        } else {
            return ResponseWrapper.fail("不支持的注册方式", 400);
        }
        return ResponseWrapper.success(null, 201);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<User> getById(@PathVariable("id") long id) {
        throw new BusinessException("暂未实现", 501);
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper<Void> deleteById(@PathVariable("id") long id) {
        throw new BusinessException("暂未实现", 501);
    }

    @PutMapping("/{id}")
    public ResponseWrapper<User> editById(@PathVariable("id") long id, @RequestBody User user) {
        throw new BusinessException("暂未实现", 501);
    }
}
