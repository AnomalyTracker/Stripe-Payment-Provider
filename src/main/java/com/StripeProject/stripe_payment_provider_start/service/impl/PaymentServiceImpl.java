package com.StripeProject.stripe_payment_provider_start.service.impl;

import com.StripeProject.stripe_payment_provider_start.constants.ErrorCodeEnum;
import com.StripeProject.stripe_payment_provider_start.dto.reqDto.CreatePaymentReqDTO;
import com.StripeProject.stripe_payment_provider_start.dto.reqDto.LineItemsDTO;
import com.StripeProject.stripe_payment_provider_start.dto.respDto.CreatePaymentResDTO;
import com.StripeProject.stripe_payment_provider_start.dto.respDto.SessionDto;
import com.StripeProject.stripe_payment_provider_start.exceptions.StripeProviderException;
import com.StripeProject.stripe_payment_provider_start.http.HttpRequest;
import com.StripeProject.stripe_payment_provider_start.http.HttpServiceEngine;
import com.StripeProject.stripe_payment_provider_start.pojo.resp.CreatePaymentRes;
import com.StripeProject.stripe_payment_provider_start.service.interfaces.PaymentService;

import com.StripeProject.stripe_payment_provider_start.stripe.Session;
import com.StripeProject.stripe_payment_provider_start.stripe.StripeErrorWrapper;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServiceEngine httpServiceEngine;

    @Autowired
    private Gson gson;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${stripe.createsession.url}")
    private String stripeCreateSessionurl;

    @Value("${stripe.secretekey}")
    private String stripeSecreteKey;

    @Override
    public CreatePaymentResDTO createPayment(CreatePaymentReqDTO createPaymentReqDTO) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(stripeSecreteKey,"");

        MultiValueMap<String, String> request = getStringStringMultiValueMap(createPaymentReqDTO);

        HttpRequest httpRequest = new HttpRequest();

        httpRequest.setMethod(HttpMethod.POST);
        httpRequest.setHeaders(httpHeaders);
        httpRequest.setRequestBody(request);
        httpRequest.setUrl(stripeCreateSessionurl);
        System.out.println("httpRequest = " + httpRequest);

        ResponseEntity<String> response = httpServiceEngine.makeHttpRequest(httpRequest);
        System.out.println("response = " + response);
        System.out.println("response.getBody() = " + response.getBody());
        System.out.println("response.getBody() = " + response.getStatusCode());
        // Handle the response
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + response.getBody());

            Session session = gson.fromJson(response.getBody(), Session.class);
            //To test
            //session.setUrl(null);
            if (session.getUrl()!= null && !session.getUrl().trim().isEmpty()){
                System.out.println("received valid url from stripe");
                CreatePaymentResDTO createPaymentResDTO = modelMapper.map(session, CreatePaymentResDTO.class);
                System.out.println("createPaymentResDTO = " + createPaymentResDTO);
                return createPaymentResDTO;
            }
            System.out.println("Failed to get URL");

            throw new StripeProviderException(ErrorCodeEnum.INVALID_SESSION_URL.getErrorCode(), ErrorCodeEnum.INVALID_SESSION_URL.getErrorMessage(), HttpStatus.BAD_GATEWAY);
            
        }
        if (response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
            System.out.println("response.getBody() = " + response.getBody());
            throw new StripeProviderException(ErrorCodeEnum.UNABLE_CONNECT_WITH_STRIPE.getErrorCode(), response.getBody(),HttpStatus.BAD_GATEWAY);
        }

        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            System.out.println("response.getBody() = " + response.getBody());
            throw new StripeProviderException(ErrorCodeEnum.GENERIC_EXCEPTION.getErrorCode(), response.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("response: "+response);
        StripeErrorWrapper stripeErrorWrapper = gson.fromJson(response.getBody(), StripeErrorWrapper.class);
        System.out.println("Got error response from stripe errorResponse = " + stripeErrorWrapper);
        System.out.println("errorResponse.getStripeError() : "+stripeErrorWrapper.getError());
        throw new StripeProviderException(stripeErrorWrapper.getError().getType(),stripeErrorWrapper.getError().getMessage(), HttpStatus.BAD_REQUEST);
        }


    private MultiValueMap<String, String> getStringStringMultiValueMap(CreatePaymentReqDTO createPaymentReqDTO) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();

        request.add("success_url", createPaymentReqDTO.getSuccessUrl());
        request.add("cancel_url", createPaymentReqDTO.getCancelUrl());
        request.add("mode","payment");
        for (int i = 0; i < createPaymentReqDTO.getLineItems().size() ; i++) {
            LineItemsDTO lineItems = createPaymentReqDTO.getLineItems().get(i);
            request.add("line_items["+i+"][quantity]", String.valueOf(lineItems.getQuantity()));
            request.add("line_items["+i+"][price_data][currency]", lineItems.getCurrency());
            request.add("line_items["+i+"][price_data][product_data][name]", lineItems.getProductName());
            int unitAmountInCents = Integer.valueOf((int) (lineItems.getUnitAmount() * 100));
            request.add("line_items["+i+"][price_data][unit_amount]", String.valueOf(unitAmountInCents));
        }
        return request;
    }
}
