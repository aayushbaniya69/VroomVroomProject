/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;
import vroomproject1.Model.StripePaymentModel;



public class StripePaymentController {
    static {
        // Your Stripe API key
        com.stripe.Stripe.apiKey = "<your_api_key>";
    }

    // This method will create a checkout and then hopefully launch the browser
    public void payWithStripe(){
        try {
            String checkoutURL = StripePaymentModel.createCheckoutSession();

            // Open the checkout in the browser
            Desktop.getDesktop().browse(new URI(checkoutURL));

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error while processing payments.");
        }
    }
}









