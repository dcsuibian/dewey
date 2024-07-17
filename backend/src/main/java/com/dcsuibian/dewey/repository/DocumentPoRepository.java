package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.DocumentPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentPoRepository extends JpaRepository<DocumentPo, Long> {
    Page<DocumentPo> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
