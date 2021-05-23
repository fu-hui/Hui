package com.github.hui.model;

public class ServiceResponse {
    public static final ServiceResponse OK = new ServiceResponse("ok");

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ServiceResponse(String msg) {
        this.msg = msg;
    }
}
