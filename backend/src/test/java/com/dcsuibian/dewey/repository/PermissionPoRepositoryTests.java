package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.PermissionPo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PermissionPoRepositoryTests {
    @Autowired
    private PermissionPoRepository poRepository;

    @Test
    void testFindAll() {
        List<PermissionPo> all = poRepository.findAll();
        System.out.println(all);
    }
}
