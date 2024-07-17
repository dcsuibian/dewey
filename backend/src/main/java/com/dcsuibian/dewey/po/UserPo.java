package com.dcsuibian.dewey.po;

import com.dcsuibian.dewey.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user")
public class UserPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String email;
    private String gender;

    public static UserPo convert(User user) {
        if (null == user) return null;
        UserPo po = new UserPo();
        po.setId(user.getId());
        po.setName(user.getName());
        po.setPassword(user.getPassword());
        po.setPhoneNumber(user.getPhoneNumber());
        po.setAvatar(user.getAvatar());
        po.setEmail(user.getEmail());
        po.setGender(null == user.getGender() ? null : user.getGender().getCode());
        return po;
    }

    public static User convert(UserPo po) {
        if (null == po) return null;
        User user = new User();
        user.setId(po.getId());
        user.setName(po.getName());
        user.setPassword(po.getPassword());
        user.setPhoneNumber(po.getPhoneNumber());
        user.setAvatar(po.getAvatar());
        user.setEmail(po.getEmail());
        user.setGender(User.Gender.fromCode(po.getGender()));
        return user;
    }
}
