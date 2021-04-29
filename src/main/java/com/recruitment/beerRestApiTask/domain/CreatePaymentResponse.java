package com.recruitment.beerRestApiTask.domain;

public class CreatePaymentResponse {
    private String clientSecret;
    public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
