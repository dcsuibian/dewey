package com.dcsuibian.dewey.po;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "role_permission")
public class RolePermissionPo {
    @EmbeddedId
    private RolePermissionPoId id;
}
