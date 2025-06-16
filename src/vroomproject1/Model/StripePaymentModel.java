/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import java.util.HashMap;
import java.util.Map;

public class StripePaymentModel {
    private static final String STRIPE_API_KEY = "sk_test_YourSecretKeyHere";

    public StripePaymentModel() {
        Stripe.apiKey = STRIPE_API_KEY;
    }

    public Charge createCharge(String token, int amount, String currency, String description) throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
            .setAmount((long) amount)  // amount in smallest currency unit (e.g. cents)
            .setCurrency(currency)
            .setDescription(description)
            .setSource(token)
            .build();

        return Charge.create(params);
    }
}