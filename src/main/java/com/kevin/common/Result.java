package com.kevin.common;

/**
 * @author wang
 * @create 2024-01-10-20:10
 */
public class Result<T> {
    private String message;

    private Integer code;

    private T data;

    private String traceId;

    public static Result success(String traceId){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        result.setTraceId(traceId);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
