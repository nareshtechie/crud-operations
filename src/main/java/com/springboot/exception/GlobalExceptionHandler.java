package com.springboot.exception;

import com.springboot.util.CryptoResponse;
import com.springboot.util.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CryptoResponse<Object>> handleCustomException(CustomException ex) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode("USER_NOT_FOUND");
        errorInfo.setErrorDescription(ex.getMessage());

        CryptoResponse<Object> errorResponse = new CryptoResponse<>();
        errorResponse.setStatus("404");
        errorResponse.setTimestamp(new Date());
        errorResponse.setData(null);
        errorResponse.setError(errorInfo);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
