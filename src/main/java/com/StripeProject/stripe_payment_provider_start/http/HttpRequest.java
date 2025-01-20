package com.StripeProject.stripe_payment_provider_start.http;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Data
public class HttpRequest {

    private HttpMethod method;
    private String url;
    private Object requestBody;
    private HttpHeaders headers;
}
