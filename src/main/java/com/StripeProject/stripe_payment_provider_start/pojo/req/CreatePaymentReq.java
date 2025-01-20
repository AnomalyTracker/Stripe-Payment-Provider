package com.StripeProject.stripe_payment_provider_start.pojo.req;

import lombok.Data;

import java.util.List;

@Data
public class CreatePaymentReq {
    private String txnRef;
    private List<LineItems> lineItems;
    private String successUrl;
    private String cancelUrl;

}
