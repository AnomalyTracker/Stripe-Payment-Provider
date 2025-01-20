package com.StripeProject.stripe_payment_provider_start.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;


@Component
public class HttpServiceEngine {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> makeHttpRequest(HttpRequest httpRequest){

        try{
            HttpHeaders httpHeaders = httpRequest.getHeaders();
            System.out.println("httpHeaders = " + httpHeaders);
            if (httpHeaders == null){
                httpHeaders = new HttpHeaders();
            }
            HttpEntity<Object> requestEntity = new HttpEntity<>(httpRequest.getRequestBody(), httpHeaders);
            System.out.println("requestEntity = " + requestEntity);
            System.out.println("url = "+httpRequest.getUrl());
            ResponseEntity<String> httpResponse = restTemplate.exchange(httpRequest.getUrl(), httpRequest.getMethod(),requestEntity, String.class);
            System.out.println("httpResponse = " + httpResponse);
            return httpResponse;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("HTTP Request failed: " + e.getMessage());
            e.printStackTrace();
            // Return a ResponseEntity indicating failure
            
            ResponseEntity<String> responseExcpitomn = ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            System.out.println("responseExcpitomn = " + responseExcpitomn);
            return responseExcpitomn;
        }
        catch (ResourceAccessException e) {
            System.out.println("Got Timeout while making http call: "+ e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
        }catch (Exception e) {
            System.out.println("Exception making http call: "+ e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
