package com.dcsuibian.dewey.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVo {
    private Type type;
    private String phoneNumber;
    private String verificationCode;

    public enum Type {
        PHONE_NUMBER_AND_VERIFICATION_CODE("phone_number_and_verification_code");
        private final String code;

        Type(String code) {
            this.code = code;
        }

        @JsonValue
        public String getCode() {
            return code;
        }

        @JsonCreator
        public static Type fromCode(String code) {
            if (null == code) {
                return null;
            }
            for (Type type : Type.values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No such type: " + code);
        }

    }
}
