package com.StripeProject.stripe_payment_provider_start.stripe;

import lombok.Data;

@Data
public class Session {
    private String id;
    private String url;
    private String status;
    private String  payment_status;
}
