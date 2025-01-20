package com.StripeProject.stripe_payment_provider_start.dto.reqDto;

import com.StripeProject.stripe_payment_provider_start.pojo.req.LineItems;
import lombok.Data;

import java.util.List;

@Data
public class CreatePaymentReqDTO {
    private String txnRef;
    private List<LineItemsDTO> lineItems;
    private String successUrl;
    private String cancelUrl;

}
