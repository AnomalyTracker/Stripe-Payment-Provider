package com.StripeProject.stripe_payment_provider_start.pojo.resp;

import lombok.Data;

@Data
public class CreatePaymentRes {
    private String id;
    private String url;
}
