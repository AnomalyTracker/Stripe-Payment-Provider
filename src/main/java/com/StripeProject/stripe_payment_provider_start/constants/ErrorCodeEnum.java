package com.StripeProject.stripe_payment_provider_start.constants;

import lombok.Getter;

public enum ErrorCodeEnum {

    GENERIC_EXCEPTION("3000","Please try again later"),
    INVALID_SESSION_URL("3001", "Inavlid session url from the STRIPE"),
    UNABLE_CONNECT_WITH_STRIPE("3002","Unable to connect with Stripe, Please try again later");
    @Getter
    public String  errorCode;

    @Getter
    public String errorMessage;

    ErrorCodeEnum(String  errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
