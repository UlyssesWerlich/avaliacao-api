package com.avaliacao.assembleia.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiHandler {

    private final MessageSource messageSource;

    private static final String HANDLER_BUSINESS_EXCEPTION_TITLE = "handler.business_exception.title";
    private static final String HANDLER_EXCEPTION_TITLE = "handler.exception.title";
    private static final String HANDLER_EXCEPTION_DETAILS = "handler.exception.details";

    // CRIADO UMA CLASSE HANDLER DEFAULT PARA TRATAMENTO DE EXCEÇÃO, COM RETORNO PADRONIZADO.
    // É UTILIZADO UM MESSAGE SOURCE PARA CENTRALIZAR AS MENSAGENS DE RETORNO DA API.
    // É POSSÍVEL IMPLEMENTAR MAIS EXCEPTIONS PARA TRATAMENTOS ESPECÍFICOS


    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        ProblemDetail p = ProblemDetail.forStatus(ex.getHttpStatus());
        p.setTitle(getMessage(HANDLER_BUSINESS_EXCEPTION_TITLE));
        p.setDetail(getMessage(ex.getErrorCode().valor, ex.getArgs()));
        return p;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        log.error(ex.getMessage(), ex);

        ProblemDetail p = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        p.setTitle(getMessage(HANDLER_EXCEPTION_TITLE));
        p.setDetail(getMessage(HANDLER_EXCEPTION_DETAILS));
        return p;
    }

    public String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }
}
