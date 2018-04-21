package com.yogurt.model.vo;

/**
 * 响应结果码定义
 */
public enum ResultCode {
    SUCCESS(0, "成功");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
