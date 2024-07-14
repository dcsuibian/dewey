package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPoRepository extends JpaRepository<UserPo, Long> {
}
