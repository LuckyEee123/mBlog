package com.mai.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mai.common.enums.AppHttpCodeEnum;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public Result() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        return result.error(code, msg);
    }
    public static Result success() {
        return new Result();
    }
    public static Result success(int code, String msg) {
        Result result = new Result();
        return result.ok(code, null, msg);
    }

    public static Result success(Object data) {
        Result result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static Result error(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }

    public static Result error(AppHttpCodeEnum enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static Result setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return success(enums.getCode(),enums.getMsg());
    }

    private static Result setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg){
        return success(enums.getCode(),msg);
    }

    public Result<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public Result<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public Result<?> ok(T data) {
        this.data = data;
        return this;
    }


}
