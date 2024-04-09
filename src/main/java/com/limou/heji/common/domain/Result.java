package com.limou.heji.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.limou.heji.common.enums.ReturnCode;
import lombok.Data;

/**
 * @author limoum0u
 * @date 23/11/9 9:48
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private String alert;
    private String status;
    private String message;
    private T data;
    private long timestamp;

    public Result() {
        this.alert = "0";
        this.status = ReturnCode.SUCCESS.getStatus();
        this.message = ReturnCode.SUCCESS.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> ofSuccess() {
        return new Result<>();
    }

    public static <T> Result<T> ofSuccess(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ofSuccess(T data, String message) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ofFail(ReturnCode returnCode) {
        Result<T> result = new Result<>();
        result.setAlert("1");
        result.setStatus(returnCode.getStatus());
        result.setMessage(returnCode.getMessage());
        return result;
    }

    public static <T> Result<T> ofFail(String code, String message) {
        Result<T> result = new Result<>();
        result.setAlert("1");
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }
}
