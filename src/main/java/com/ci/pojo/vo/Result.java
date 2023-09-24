package com.ci.pojo.vo;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
