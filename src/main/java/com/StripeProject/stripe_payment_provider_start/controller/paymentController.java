package com.StripeProject.stripe_payment_provider_start.controller;

import com.StripeProject.stripe_payment_provider_start.constant.Endpoints;
import com.StripeProject.stripe_payment_provider_start.dto.reqDto.CreatePaymentReqDTO;
import com.StripeProject.stripe_payment_provider_start.dto.respDto.CreatePaymentResDTO;
import com.StripeProject.stripe_payment_provider_start.pojo.req.CreatePaymentReq;
import com.StripeProject.stripe_payment_provider_start.pojo.resp.CreatePaymentRes;
import com.StripeProject.stripe_payment_provider_start.service.interfaces.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.V1_PAYMENTS)
public class paymentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<CreatePaymentRes> createPayment(@RequestBody CreatePaymentReq createPaymentReq){
        CreatePaymentReqDTO createPaymentReqDTO = modelMapper.map(createPaymentReq, CreatePaymentReqDTO.class);
        CreatePaymentResDTO createPaymentResDTO = paymentService.createPayment(createPaymentReqDTO);
        CreatePaymentRes createPaymentRes = modelMapper.map(createPaymentResDTO, CreatePaymentRes.class);
        return new ResponseEntity<CreatePaymentRes>(createPaymentRes,HttpStatus.CREATED);
    }
}
