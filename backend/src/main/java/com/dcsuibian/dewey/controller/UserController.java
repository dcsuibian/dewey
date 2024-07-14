package com.dcsuibian.dewey.controller;

import com.dcsuibian.dewey.entity.User;
import com.dcsuibian.dewey.service.UserService;
import com.dcsuibian.dewey.vo.PageWrapper;
import com.dcsuibian.dewey.vo.ResponseWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
