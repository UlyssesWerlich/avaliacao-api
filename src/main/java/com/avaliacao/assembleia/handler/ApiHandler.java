package com.avaliacao.assembleia.handler;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandler {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        ProblemDetail p = ProblemDetail.forStatus(ex.getHttpStatus());
        p.setDetail(ex.getMessage());
        p.setTitle(ex.getMessage());
        return p;
    }

}
