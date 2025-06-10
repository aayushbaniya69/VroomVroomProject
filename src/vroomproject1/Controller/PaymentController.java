/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;

import javax.swing.JOptionPane;
import vroomproject1.Model.PaymentData;
import vroomproject1.view.PaymentView;

/**
 *
 * @author Dell
 */


public class PaymentController {
    private PaymentView view;

    public PaymentController(PaymentView view) {
        this.view = view;

        view.getPayButton().addActionListener(e -> processPayment());
    }

    private void processPayment() {
        String cardNumber = view.getCardNumTxtField().getText();
        String expDate = view.getExpDateTxtField().getText();
        String cvc = view.getCVCTxtField().getText();
        String name = view.getNameTxtField().getText();
        String address = view.getAddressTxtField().getText();
        String city = view.getCityTxtField().getText();
        String state = view.getStateTxtField().getText();
        String country = view.getCountryTxtField().getText();

        if (cardNumber.isEmpty() || expDate.isEmpty() || cvc.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all required fields.");
            return;
        }

        PaymentData payment = new PaymentData(cardNumber, expDate, cvc, name, address, city, state, country);

        // Here you could save to DB or call a payment API
        JOptionPane.showMessageDialog(view, "Payment processed successfully!");
    }
}

