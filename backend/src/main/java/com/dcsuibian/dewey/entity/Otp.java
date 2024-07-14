package com.dcsuibian.dewey.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Otp {
    private Type type;
    private String receiver;
    private String content;
    private Purpose purpose;

    public enum Type {
        SMS("sms");
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

    public enum Purpose {
        REGISTER("register"), LOGIN("login");
        private final String code;

        Purpose(String code) {
            this.code = code;
        }

        @JsonValue
        public String getCode() {
            return code;
        }

        @JsonCreator
        public static Purpose fromCode(String code) {
            if (null == code) {
                return null;
            }
            for (Purpose purpose : Purpose.values()) {
                if (purpose.getCode().equals(code)) {
                    return purpose;
                }
            }
            throw new IllegalArgumentException("No such purpose: " + code);
        }
    }
}
