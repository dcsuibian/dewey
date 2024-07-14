package com.dcsuibian.dewey.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String email;
    private Gender gender;

    public enum Gender {
        MALE("男"),
        FEMALE("女"),
        SECRET("保密");
        private final String code;

        Gender(String code) {
            this.code = code;
        }

        @JsonValue
        public String getCode() {
            return code;
        }

        @JsonCreator
        public static Gender fromCode(String code) {
            if (null == code) {
                return null;
            }
            for (Gender gender : Gender.values()) {
                if (gender.getCode().equals(code)) {
                    return gender;
                }
            }
            throw new IllegalArgumentException("No such gender: " + code);
        }
    }
}
