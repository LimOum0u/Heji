package com.limou.heji.common.exception;

import com.limou.heji.common.enums.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author limoum0u
 * @date 23/11/5 10:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误消息
     */
    private String message;

    public BaseException(ReturnCode returnCode) {
        this.code = returnCode.getStatus();
        this.message = returnCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}