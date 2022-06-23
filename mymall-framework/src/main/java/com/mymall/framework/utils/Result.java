package com.mymall.framework.utils;


import cn.hutool.http.HttpStatus;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
    }

    public Result(int code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    public Result(int code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (data != null) {
            super.put("data", data);
        }
    }


    public static Result success() {
        return Result.success("success");
    }

    public static Result success(Object data) {
        return Result.success("success", data);
    }

    public static Result success(String msg) {
        return Result.success(msg, null);
    }

    public static Result success(String msg, Object data) {
        return new Result(HttpStatus.HTTP_OK, msg, data);
    }


    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error(String msg, Object data) {
        return new Result(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }

    public static Result error(String msg) {
        return Result.error(msg, null);
    }

    public static Result error() {
        return Result.error("error");
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }


}