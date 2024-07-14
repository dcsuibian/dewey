package com.dcsuibian.dewey.service.impl;

import com.dcsuibian.dewey.entity.User;
import com.dcsuibian.dewey.exception.BusinessException;
import com.dcsuibian.dewey.po.UserPo;
import com.dcsuibian.dewey.repository.UserPoRepository;
import com.dcsuibian.dewey.service.UserService;
import com.dcsuibian.dewey.vo.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserPoRepository poRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserPoRepository poRepository, PasswordEncoder passwordEncoder) {
        this.poRepository = poRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PageWrapper<User> getPublic(int pageNumber, int pageSize) {
        PageWrapper<User> page = get(pageNumber, pageSize);
        for (User user : page.getData()) {
            processForPublicAccess(user);
        }
        return page;
    }

    @Override
    public User loginByPhoneNumberAndPassword(String phoneNumber, String password) {
        Optional<UserPo> optional = poRepository.findByPhoneNumber(phoneNumber);
        if (optional.isEmpty()) {
            throw new BusinessException("用户名或密码错误");
        }
        UserPo po = optional.get();
        if (!passwordEncoder.matches(password, po.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        User user = UserPo.convert(po);
        processForPrivateAccess(user);
        return user;
    }

    private User getById(long id) {
        Optional<UserPo> optional = poRepository.findById(id);
        return optional.map(UserPo::convert).orElse(null);
    }

    public PageWrapper<User> get(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // PageRequest的页码从0开始
        return PageWrapper.of(poRepository.findAll(pageable), UserPo::convert);
    }

    private void processForPublicAccess(User user) {
        user.setPassword(null);
        user.setPhoneNumber(null);
        user.setEmail(null);
    }

    private void processForPrivateAccess(User user) {
        user.setPassword(null);
    }
}
