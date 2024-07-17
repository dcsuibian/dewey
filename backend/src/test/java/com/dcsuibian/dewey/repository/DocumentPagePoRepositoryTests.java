package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.DocumentPagePo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DocumentPagePoRepositoryTests {
    @Autowired
    private DocumentPagePoRepository poRepository;

    @Test
    void testFindAll() {
        List<DocumentPagePo> all = poRepository.findAll();
    }
}
