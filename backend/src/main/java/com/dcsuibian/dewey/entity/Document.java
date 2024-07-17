package com.dcsuibian.dewey.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Document {
    private Long id;
    private String title;
    private String description;
    private String tags;
    private Type type;
    private String cover;
    private String content;
    private User uploader;
    private Category category;
    private String accessLevel;
    private Instant createTime;
    private Instant updateTime;

    public enum Type {
        PDF("pdf"),
        WORD("word");
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
