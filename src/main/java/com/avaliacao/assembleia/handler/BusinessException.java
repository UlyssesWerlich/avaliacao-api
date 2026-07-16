package com.avaliacao.assembleia.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorCodeEnum errorCode;
    private Object[] args;


    public BusinessException(HttpStatus httpStatus, ErrorCodeEnum errorCode) {
        super(errorCode.getValor());
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public BusinessException(HttpStatus httpStatus, ErrorCodeEnum errorCode, Object... args) {
        super(errorCode.getValor());
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.args = args;
    }
}
