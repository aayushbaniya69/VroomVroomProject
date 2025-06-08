/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;

import vroomproject1.view.Reset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPasswordController {
    Reset view;

    public ResetPasswordController(Reset view) {
        this.view = view;

        // Attach action listener to the reset password button
        this.view.resetUser(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle reset password action
                String newPassword = new String(view.getNewPasswordField().getPassword());
                String confirmPassword = new String(view.getConfirmPasswordField().getPassword());
                String email = view.getEmailTextField().getText();

                if (!newPassword.equals(confirmPassword)) {
                    System.out.println("Passwords do not match!");
                    return;
                }

                if (newPassword.length() < 8) {
                    System.out.println("Password must be at least 8 characters.");
                    return;
                }

                // Continue with password reset logic
                System.out.println("Password reset for: " + email);
                }
            }
        );
    }
}

