package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.RolePo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RolePoRepositoryTests {
    @Autowired
    private RolePoRepository poRepository;

    @Test
    void testFindAll() {
        List<RolePo> all = poRepository.findAll();
        System.out.println(all);
    }
}
