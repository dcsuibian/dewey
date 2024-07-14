package com.dcsuibian.dewey.service.impl;

import com.dcsuibian.dewey.entity.User;
import com.dcsuibian.dewey.exception.BusinessException;
import com.dcsuibian.dewey.po.UserPo;
import com.dcsuibian.dewey.repository.UserPoRepository;
import com.dcsuibian.dewey.service.UserService;
import com.dcsuibian.dewey.util.Util;
import com.dcsuibian.dewey.vo.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserPoRepository poRepository;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public UserServiceImpl(UserPoRepository poRepository, PasswordEncoder passwordEncoder, StringRedisTemplate redisTemplate) {
        this.poRepository = poRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
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

    @Override
    public User loginByPhoneNumberAndVerificationCode(String phoneNumber, String verificationCode) {
        String codeKey = "dewey:login:code:" + phoneNumber;
        String codeValue = redisTemplate.opsForValue().get(codeKey);
        if (null == codeValue) {
            throw new BusinessException("验证码已过期");
        }
        if (!codeValue.equals(verificationCode)) {
            throw new BusinessException("验证码错误");
        }
        redisTemplate.delete(codeKey);
        Optional<UserPo> optional = poRepository.findByPhoneNumber(phoneNumber);
        if (optional.isEmpty()) {
            log.error("手机号为{}的用户不存在，但Redis中却有其登录验证码", phoneNumber);
            throw new BusinessException("用户不存在");
        }
        UserPo po = optional.get();
        User user = UserPo.convert(po);
        processForPrivateAccess(user);
        return user;
    }

    @Override
    public void preLogin(String phoneNumber) {
        if (poRepository.findByPhoneNumber(phoneNumber).isEmpty()) {
            throw new BusinessException("用户不存在");
        }
        String countKey = "dewey:login:count:" + phoneNumber;
        Long attempts = redisTemplate.opsForValue().increment(countKey);
        if (null == attempts) {
            throw new BusinessException("Redis error");
        }
        if (1 == attempts) {
            redisTemplate.expire(countKey, 10, TimeUnit.MINUTES);
        }
        if (attempts > 10) {
            throw new BusinessException("尝试次数过多，请稍后再试");
        }
        String codeKey = "dewey:login:code:" + phoneNumber;
        String codeValue = redisTemplate.opsForValue().get(codeKey);
        if (null == codeValue) {
            codeValue = Util.generateRandomNumber(6);
            redisTemplate.opsForValue().set(codeKey, codeValue, 5, TimeUnit.MINUTES);
        }
        // TODO 发送验证码
        log.info("preLogin验证码：{}", codeValue);
    }

    @Override
    public void preRegister(String phoneNumber) {
        if (poRepository.existsByPhoneNumber(phoneNumber)) {
            throw new BusinessException("手机号已被注册");
        }
        String countKey = "dewey:register:count:" + phoneNumber;
        Long attempts = redisTemplate.opsForValue().increment(countKey);
        if (null == attempts) {
            throw new BusinessException("Redis error");
        }
        if (1 == attempts) {
            redisTemplate.expire(countKey, 10, TimeUnit.MINUTES);
        }
        if (attempts > 10) {
            throw new BusinessException("尝试次数过多，请稍后再试");
        }
        String codeKey = "dewey:register:code:" + phoneNumber;
        String codeValue = redisTemplate.opsForValue().get(codeKey);
        if (null == codeValue) {
            codeValue = Util.generateRandomNumber(6);
            redisTemplate.opsForValue().set(codeKey, codeValue, 5, TimeUnit.MINUTES);
        }
        // TODO 发送验证码
        log.info("preRegister验证码：{}", codeValue);
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
