package com.dcsuibian.dewey.po;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user_role")
public class UserRolePo {
    @EmbeddedId
    private UserRolePoId id;
}
