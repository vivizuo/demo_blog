package com.example.demo.model;

public class Response<T> {

    private String subCode;

    private String subMsg;

    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }

    public static Response success() {
        return new Response();
    }

    public static <T> Response<T> failed(String subCode, String subMsg) {
        Response<T> response = new Response<>();
        response.setSubCode(subCode);
        response.setSubMsg(subMsg);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public boolean isSuccess() {
        return subCode == null && subMsg == null;
    }
}
