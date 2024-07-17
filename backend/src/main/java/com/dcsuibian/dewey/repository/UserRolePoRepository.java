package com.dcsuibian.dewey.repository;

import com.dcsuibian.dewey.po.UserRolePo;
import com.dcsuibian.dewey.po.UserRolePoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolePoRepository extends JpaRepository<UserRolePo, UserRolePoId> {
}
