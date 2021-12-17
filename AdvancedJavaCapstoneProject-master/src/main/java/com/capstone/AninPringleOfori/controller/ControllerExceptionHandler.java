package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.model.error.CustomErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> handleException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        for(FieldError fieldError: fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(fieldError.getDefaultMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorResponseList.add(errorResponse);
        }

        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);

        return responseEntity;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleException(IllegalArgumentException e) {

        CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

        return responseEntity;
    }
}