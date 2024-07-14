package com.dcsuibian.dewey.service;

import com.dcsuibian.dewey.entity.User;
import com.dcsuibian.dewey.vo.PageWrapper;

public interface UserService {
    PageWrapper<User> getPublic(int pageNumber, int pageSize);

    User loginByPhoneNumberAndPassword(String phoneNumber, String password);
}
