package com.StripeProject.stripe_payment_provider_start.exceptions;

import com.StripeProject.stripe_payment_provider_start.pojo.resp.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class StripeProviderExceptionHandler {

    @ExceptionHandler(StripeProviderException.class)
    public ResponseEntity<ErrorResponse> handleStripeProviderExceptions(StripeProviderException exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), exception.getErrorCode(), exception.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
    }
}
