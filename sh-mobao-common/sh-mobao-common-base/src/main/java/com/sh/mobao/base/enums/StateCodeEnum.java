package com.sh.mobao.base.enums;


/**
 *
 * 100 - 199 用户业务的
 * 200 - 299 支付业务的
 *
 * 依次类推
 *
 */


public enum StateCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(200,"请求成功"),

    /**
     * 请求失败
     */
    FAILURE(500,"请求失败");


    private int code;
    private String msg;

    StateCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
