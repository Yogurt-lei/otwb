package com.yogurt.web.response;

/**
 * 响应结果码定义
 */
public enum ResultCode {
    SUCCESS(0, "成功"),
    FAILED(-1, "失败"),

    METHOD_ARGUMENT_NOT_VALID(101, "数据校验异常"),
    BUSINESS_ERROR(102, "业务异常"),
    DAO_ERROR(103, "数据库操作异常");

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
