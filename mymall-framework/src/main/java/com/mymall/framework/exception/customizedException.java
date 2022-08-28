package com.mymall.framework.exception;

public final class customizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code=500;

    private String msg;

    public customizedException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public customizedException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public customizedException(String msg, Integer code) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public customizedException(String msg, Integer code, Throwable e) {
        super(msg,e);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
