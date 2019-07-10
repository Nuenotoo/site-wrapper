package com.dean.boot.response;

/**
 * @Author: luoxy
 * @Date: 2019/5/27
 * @Description:
 */
public enum ApiResponseCode {
    SUCCESS(0, "成功"),
    UNKNOWN(-1, "未知的错误");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
