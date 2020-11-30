
 package com.utils;

public class Res<T> {

    private static final String CODE_SUCCESS = "success";
    
    private static final String CODE_FAIL = "fail";
    
    private String code;
    private T data;
    private String msg;
    
    public Res() {
    
    }
    
    public Res(String code) {
        this.code = code;
    }
    
    public Res(String code, T data) {
        this.code = code;
        this.data = data;
    }
    
    public Res(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public static Res success() {
        return new Res(CODE_SUCCESS);
    }
    
    public static Res success(Object data) {
        return new Res(CODE_SUCCESS, data);
    }
    
    public static Res fail(String msg) {
        return new Res(CODE_FAIL,msg);
    }
    
    public static Res widthCode(String errorCode) {
        return new Res(errorCode);
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
