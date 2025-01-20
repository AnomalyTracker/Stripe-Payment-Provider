package com.StripeProject.stripe_payment_provider_start.pojo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String path;
    private String errorCode;
    private String errorMessage;

}
