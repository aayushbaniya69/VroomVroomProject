/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vroomproject1;

//import javax.swing.JFrame;
////import vroomproject1.Controller.FilterController;
//import vroomproject1.Controller.ResetPasswordController;
////import vroomproject1.Controller.StripePaymentController;
////import vroomproject1.Controller.PaymentController;
////import vroomproject1.Model.StripePaymentModel;
//import vroomproject1.view.Filter;
////import vroomproject1.view.PaymentView;
//import vroomproject1.view.PaymentView;

import javaproject.controller.RegistrationController;
import javaproject.view.RegistrationView;
import vroomproject1.Controller.ResetPasswordController;
import vroomproject1.view.ResetView;

/**
 *
 * @author Dell
 */
public class VroomProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create JFrame
//            JFrame frame = new JFrame("Filter Form");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            // Create the Filter panel
//            Filter view = new Filter();
//            
//            // Add panel to frame
//            frame.setContentPane(view);
//            frame.pack(); // Fit size to preferred size of components
//            frame.setLocationRelativeTo(null); // Center on screen
//            frame.setVisible(true);

            // Connect controller
//            FilterController controller = new FilterController(view);
//   ResetView resetView = new ResetView();
//                ResetPasswordController controller= new ResetPasswordController(resetView);
//                resetView.setVisible(true);
//        });
        
        RegistrationView registrationView = new RegistrationView();
                RegistrationController controller= new RegistrationController(registrationView);
                registrationView.setVisible(true);
        });
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PaymentView().setVisible(true);
//            }
//        });
        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Reset resetView = new Reset();
//                ResetPasswordController controller= new ResetPasswordController(resetView);
//                resetView.setVisible(true);
//            }
            
//        });
        
    
    
      
//        PaymentView paymentView = new PaymentView();
//        PaymentController controller = new PaymentController(paymentView);
//        paymentView.setVisible(true);
//
//    }
    
    }
}



