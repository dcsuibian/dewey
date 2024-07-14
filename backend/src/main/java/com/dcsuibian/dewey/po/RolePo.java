package com.dcsuibian.dewey.po;

import com.dcsuibian.dewey.entity.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "role")
public class RolePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public static RolePo convert(Role role) {
        if (null == role) return null;
        RolePo po = new RolePo();
        po.setId(role.getId());
        po.setName(role.getName());
        po.setDescription(role.getDescription());
        return po;
    }

    public static Role convert(RolePo po) {
        if (null == po) return null;
        Role role = new Role();
        role.setId(po.getId());
        role.setName(po.getName());
        role.setDescription(po.getDescription());
        return role;
    }
}
