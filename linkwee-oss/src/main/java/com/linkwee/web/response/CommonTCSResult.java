package com.linkwee.web.response;

/**
 *
 * @Author Libin
 * @Date 2016/6/2
 */
public class CommonTCSResult {
    private int code;
    private String message = "";
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
