package com.StripeProject.stripe_payment_provider_start.dto.reqDto;

import lombok.Data;

@Data
public class LineItemsDTO {
    private int quantity;
    private String currency;
    private String productName;
    private double unitAmount;
}
