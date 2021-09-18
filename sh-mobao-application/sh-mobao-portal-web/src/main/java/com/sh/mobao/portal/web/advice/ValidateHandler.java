package com.sh.mobao.portal.web.advice;

import com.sh.mobao.base.result.ResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ValidateHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            String s = fieldError.getDefaultMessage();
            stringBuilder.append(" "+s);
            break;
        }
        return new ResponseEntity(ResultWrapper.builder().code(102).msg(stringBuilder.toString()).build(),HttpStatus.OK);
    }
}
