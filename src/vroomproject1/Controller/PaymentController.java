/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;


import vroomproject1.Model.StripePaymentModel;
import com.stripe.exception.StripeException;

import javax.swing.*;
import vroomproject1.view.PaymentView;

/**
 *
 * @author Dell
 */
public class PaymentController {
     private PaymentView view;
    private StripePaymentModel paymentModel;

    public PaymentController(PaymentView view) {
        this.view = view;
        this.paymentModel = new StripePaymentModel();
    }

    public void payWithCreditDebit() {
        String cardNumber = view.CardNumTxtField.getText();
        String expiryDate = view.ExpiryDateTxtField.getText();
        String cvc = view.CVCTxtField.getText();
        String name = view.NameTxtField.getText();

        // Basic validation
        if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvc.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all card details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // For demo, you would generate token from card details via Stripe Elements or Stripe SDK (not included here)
        String fakeToken = "tok_visa"; // Replace with real token in production

        try {
            paymentModel.createCharge(fakeToken, 2000, "usd", "Vehicle rental payment");
            JOptionPane.showMessageDialog(view, "Payment Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (StripeException e) {
            JOptionPane.showMessageDialog(view, "Payment failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void payWithESewa() {
        JOptionPane.showMessageDialog(view, "eSewa payment integration not implemented yet.");
    }

    public void payWithBank() {
        JOptionPane.showMessageDialog(view, "Bank payment integration not implemented yet.");
    }
}
