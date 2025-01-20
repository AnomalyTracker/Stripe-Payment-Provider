package com.StripeProject.stripe_payment_provider_start.service.interfaces;

import com.StripeProject.stripe_payment_provider_start.dto.reqDto.CreatePaymentReqDTO;
import com.StripeProject.stripe_payment_provider_start.dto.respDto.CreatePaymentResDTO;

public interface PaymentService {
    CreatePaymentResDTO createPayment(CreatePaymentReqDTO createPaymentReqDTO);
}
