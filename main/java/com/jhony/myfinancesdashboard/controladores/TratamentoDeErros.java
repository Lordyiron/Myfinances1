package com.jhony.myfinancesdashboard.controladores;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(RuntimeException.class)
    public String tratarRuntimeException(RuntimeException ex){
        return ex.getMessage();
    }
}
