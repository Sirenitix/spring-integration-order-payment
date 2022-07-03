package com.nurs.rabbitmq.handler;

import com.nurs.rabbitmq.exceptions.OrderAlreadyPaid;
import com.nurs.rabbitmq.exceptions.OrderNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badCredentials() {
        return "Bad request incorrect inputs";
    }

    @ResponseBody
    @ExceptionHandler(OrderAlreadyPaid.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String orderAlreadyPaid() {
        return "Order already paid";
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFound() {
        return "Order not found";
    }
}

