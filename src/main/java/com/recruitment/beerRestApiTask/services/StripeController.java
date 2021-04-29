package com.recruitment.beerRestApiTask.services;

import com.google.gson.Gson;
import com.recruitment.beerRestApiTask.domain.CreatePayment;
import com.recruitment.beerRestApiTask.domain.CreatePaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/stripe")
public class StripeController {
    private Gson gson = new Gson();

    @GetMapping("/create-checkout-session")
    public String getSession() throws StripeException {
        Stripe.apiKey = "sk_test_51IgBs2EkcMujU5UpKJrOyLd0yB86e738gSrcUF5IZwNYj4EFqYny8pUJiFaU4wmtJzRICmUnfQ8wAKO8IpJmN31100LPAP7sz4";
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:4200/success")
                        .setCancelUrl("http://localhost:4200/cancel")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("usd")
                                                        .setUnitAmount(2000L)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName("Stubborn Attachments")
                                                                        .build())
                                                        .build())
                                        .build())
                        .build();
        Session session = Session.create(params);
        HashMap<String, String> responseData = new HashMap<String, String>();
        responseData.put("id", session.getId());
        return gson.toJson(responseData);
    }

    @PostMapping("/create-payment-intent")
    public String getPaymentIntent(@RequestBody CreatePayment purchase) throws StripeException {
        Stripe.apiKey = "sk_test_51IgBs2EkcMujU5UpKJrOyLd0yB86e738gSrcUF5IZwNYj4EFqYny8pUJiFaU4wmtJzRICmUnfQ8wAKO8IpJmN31100LPAP7sz4";
//        CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
//                .setAmount(new Long(calculateOrderAmount(purchase.getItems())))
                .setAmount(Long.valueOf(purchase.getTotalAmount())*100L)
                .build();
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
        return gson.toJson(paymentResponse);
    }

    private int calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // users from directly manipulating the amount on the client
        return 1400;
    }
}