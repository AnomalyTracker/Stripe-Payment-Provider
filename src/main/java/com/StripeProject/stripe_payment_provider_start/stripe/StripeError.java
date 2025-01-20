package com.StripeProject.stripe_payment_provider_start.stripe;

import lombok.Data;

@Data
public class StripeError {

    private String code;
    private String param;
    private String message;
    private String decline_code;
    private String type;
}
