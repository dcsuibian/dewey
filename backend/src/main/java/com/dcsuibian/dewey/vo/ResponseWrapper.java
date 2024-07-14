package com.dcsuibian.dewey.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {
    private T result;
    private String message;
    private int code;
    private long timestamp = System.currentTimeMillis();

    public ResponseWrapper(T result, String message, int code) {
        this.result = result;
        this.message = message;
        this.code = code;
    }

    public static <T> ResponseWrapper<T> success() {
        return new ResponseWrapper<>(null, "success", 200);
    }

    public static <T> ResponseWrapper<T> success(T result) {
        return new ResponseWrapper<>(result, "success", 200);
    }

    public static <T> ResponseWrapper<T> success(T result, int code) {
        return new ResponseWrapper<>(result, "success", code);
    }

    public static <T> ResponseWrapper<T> fail(String message, int code) {
        return new ResponseWrapper<>(null, message, code);
    }

}
