package com.StripeProject.stripe_payment_provider_start.pojo.req;

import lombok.Data;

@Data
public class LineItems {
    private int quantity;
    private String currency;
    private String productName;
    private double unitAmount;
}
