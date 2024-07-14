package com.dcsuibian.dewey.po;

import com.dcsuibian.dewey.entity.Permission;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "permission")
public class PermissionPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public static PermissionPo convert(Permission permission) {
        if (null == permission) return null;
        PermissionPo po = new PermissionPo();
        po.setId(permission.getId());
        po.setName(permission.getName());
        po.setDescription(permission.getDescription());
        return po;
    }

    public static Permission convert(PermissionPo po) {
        if (null == po) return null;
        Permission permission = new Permission();
        permission.setId(po.getId());
        permission.setName(po.getName());
        permission.setDescription(po.getDescription());
        return permission;
    }
}
