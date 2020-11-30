package com.exce;

import com.utils.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 统一异常类
 */
public class BusinessException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    protected String errorCode;
    protected String[] errorMessageArguments;
    protected Res res;

    protected BusinessException() {
        this("");
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = "fail";
        this.errorMessageArguments = new String[0];
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "fail";
        this.errorMessageArguments = new String[0];
    }

    public static BusinessException withErrorCode(String errorCode) {
        BusinessException businessException = new BusinessException();
        businessException.errorCode = errorCode;
        return businessException;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String[] getErrorMessageArguments() {
        return this.errorMessageArguments = errorMessageArguments;
    }

    public BusinessException withErrorMessageArguments(String... errorMessageArguments) {
        if(errorMessageArguments != null) {
            this.errorMessageArguments = errorMessageArguments;
        }

        return this;
    }

    public Res response() {
        if (this.res != null) {
            return this.res;
        } else {
            this.res = Res.widthCode(this.getErrorCode());
            if (this.getErrorMessageArguments() != null && this.getErrorMessageArguments().length > 0) {
                try {
                    this.res.setMsg(MessageFormat.format(this.res.getMsg(),this.getErrorMessageArguments()));
                } catch (Exception var2) {
                    LOGGER.error(var2.getMessage());
                }
            }
            return this.res;
        }
    }
}
