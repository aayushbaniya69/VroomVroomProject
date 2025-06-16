/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import vroomproject1.Controller.StripePaymentController;

/**
 *
 * @author Dell
 */


public class StripePaymentView extends JFrame {

    private final JButton payWithStripeBtn;

    public StripePaymentView {
        setTitle("Stripe Payment UI");

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        payWithStripeBtn = new JButton("Pay with Stripe");

        payWithStripeBtn.setFont(new Font(" Arial", Font.BOLD, 16));

        payWithStripeBtn.setBackground(Color.GREEN);
        payWithStripeBtn.setForeground(Color.WHITE);
        payWithStripeBtn.setBorderPainted(false);
        payWithStripeBtn.setFocusPainted(false);
        payWithStripeBtn.setPreferredSize(new Dimension(200, 50));

        payWithStripeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                StripePaymentController controller = new StripePaymentController();
                controller.payWithStripe();
            }
        });

        add(payWithStripeBtn, BorderLayout.CENTER);
    }
}
