package com.github.mybatis.entity;

import com.github.mybatis.enums.ResultCode;

/**
 * 通用接口返回
 *
 * @author czs
 * @since 2018/7/12
 */
public class Result<T> {

    /**
     * 结果代码
     */
    private Integer retCode;
    /**
     * 结果信息
     */
    private String retMsg;

    /**
     * 接口返回时间戳
     */
    private long timestamp;

    private T data;

    public Result() {
    }

    private Result(Integer retCode, String retMsg, T data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return getResponse(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.message(), data);
    }

    public static  Result error(ResultCode resultCode){
        return  getResponse(resultCode.code(),resultCode.message(),null);
    }

    public static Result error() {
        return getResponse(ResultCode.Error.code(), ResultCode.Error.message(), null);
    }

    public static Result error(String message) {
        return getResponse(ResultCode.Error.code(), message, null);
    }

    public static Result getResponse(ResultCode resultCode,Object data){
        return new Result(resultCode.code(),resultCode.message(),data);
    }

    public static Result getResponse(Integer retCode, String retMsg, Object data) {
        return new Result(retCode, retMsg, data);
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result1{");
        sb.append("retCode=").append(retCode);
        sb.append(", retMsg='").append(retMsg).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}



