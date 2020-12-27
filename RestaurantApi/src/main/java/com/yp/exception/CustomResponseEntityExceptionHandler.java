package com.yp.exception;

import com.yp.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContentNotFoundException.class)
    public final ResponseEntity<ErrorResponseDto> handleContentNotFoundExcpetion(ContentNotFoundException e,
                                                                                 WebRequest request){
        ErrorResponseDto response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public final ResponseEntity<ErrorResponseDto> handleBusinessRuleException(BusinessRuleException e,
                                                                              WebRequest request){
        ErrorResponseDto response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponseDto> handleGeneralException(Exception e, WebRequest request){
        ErrorResponseDto response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ErrorResponseDto prepareResponseModel(String message, WebRequest request){
        return new ErrorResponseDto(new Date(), message, request.getDescription(true));
    }

}
