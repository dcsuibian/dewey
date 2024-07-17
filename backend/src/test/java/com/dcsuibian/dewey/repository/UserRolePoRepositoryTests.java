package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.UserRolePo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRolePoRepositoryTests {
    @Autowired
    private UserRolePoRepository poRepository;

    @Test
    void testFindAll() {
        List<UserRolePo> all = poRepository.findAll();
    }
}
