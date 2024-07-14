package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPoRepository extends JpaRepository<UserPo, Long> {
    Optional<UserPo> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByName(String name);
}
