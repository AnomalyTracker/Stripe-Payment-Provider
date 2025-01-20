package com.StripeProject.stripe_payment_provider_start.dto.respDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentResDTO {
    private String id;
    private String url;
}
