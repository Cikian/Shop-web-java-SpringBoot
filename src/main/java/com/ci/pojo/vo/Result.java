package com.ci.pojo.vo;

import lombok.Getter;

@Getter
public class Result {
    private Integer code;
    private Object data;
    private String msg;
    private String path;


    public Result() {
    }

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg, String path) {
        this.data = data;
        this.msg = msg;
        this.code = code;
        this.path = path;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
