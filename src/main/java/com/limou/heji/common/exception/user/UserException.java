package com.limou.heji.common.exception.user;


import com.limou.heji.common.enums.ReturnCode;
import com.limou.heji.common.exception.BaseException;

import java.io.Serial;

public class UserException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserException(String code, String message) {
        super(code, message);
    }

    public UserException(ReturnCode returnCode) {
        super(returnCode);
    }
}
