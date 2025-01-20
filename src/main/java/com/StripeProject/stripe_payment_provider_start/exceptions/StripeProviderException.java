package com.StripeProject.stripe_payment_provider_start.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class StripeProviderException extends RuntimeException {

    private String  errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

}
