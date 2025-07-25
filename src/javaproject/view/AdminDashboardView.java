/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaproject.view;


import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


/**
 *
 * @author ASUS
 */
public class AdminDashboardView extends javax.swing.JFrame {
    private final CardLayout contentCardLayout;
    private final AdminVehiclePanel adminVehiclePanel; // if it's a separate class for admin

    public AdminDashboardView() {
        initComponents();
        contentCardLayout = new CardLayout();
        adminContentPanel.setLayout(contentCardLayout);

// Create and add your panels
        adminVehiclePanel = new AdminVehiclePanel(); // this should be your admin vehicle management panel
        adminContentPanel.add(new JPanel(), "Dummy");  // Optional default panel
        adminContentPanel.add(adminVehiclePanel, "Vehicle");

// Show dummy at start
        contentCardLayout.show(adminContentPanel, "Vehicle");

        
    
    }
    public void showVehiclePanel() {
    contentCardLayout.show(adminContentPanel, "Vehicle");
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adimnBorderPanel = new javax.swing.JPanel();
        adminMainPanel = new javax.swing.JPanel();
        adminMenuBar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        adVehicleButton = new javax.swing.JButton();
        adminProfileButton = new javax.swing.JButton();
        adLogoutButton = new javax.swing.JButton();
        adminContentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        adimnBorderPanel.setBackground(new java.awt.Color(30, 30, 47));
        adimnBorderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        adminMenuBar.setBackground(new java.awt.Color(44, 47, 54));
        adminMenuBar.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/Screenshot (166) (2) (1).png"))); // NOI18N

        adVehicleButton.setBackground(new java.awt.Color(44, 47, 54));
        adVehicleButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        adVehicleButton.setForeground(new java.awt.Color(241, 245, 249));
        adVehicleButton.setText(" Vehicles");
        adVehicleButton.setBorder(null);
        adVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adVehicleButtonActionPerformed(evt);
            }
        });

        adminProfileButton.setBackground(new java.awt.Color(44, 47, 54));
        adminProfileButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        adminProfileButton.setForeground(new java.awt.Color(241, 245, 249));
        adminProfileButton.setText(" AdminProfile");
        adminProfileButton.setBorder(null);

        adLogoutButton.setBackground(new java.awt.Color(44, 47, 54));
        adLogoutButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        adLogoutButton.setForeground(new java.awt.Color(241, 245, 249));
        adLogoutButton.setText("Logout");
        adLogoutButton.setBorder(null);
        adLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adLogoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminMenuBarLayout = new javax.swing.GroupLayout(adminMenuBar);
        adminMenuBar.setLayout(adminMenuBarLayout);
        adminMenuBarLayout.setHorizontalGroup(
            adminMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMenuBarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(adminMenuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adVehicleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminMenuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(adminMenuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adLogoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        adminMenuBarLayout.setVerticalGroup(
            adminMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMenuBarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(adVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(adminProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151)
                .addComponent(adLogoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        adminContentPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout adminMainPanelLayout = new javax.swing.GroupLayout(adminMainPanel);
        adminMainPanel.setLayout(adminMainPanelLayout);
        adminMainPanelLayout.setHorizontalGroup(
            adminMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMainPanelLayout.createSequentialGroup()
                .addComponent(adminMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminContentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adminMainPanelLayout.setVerticalGroup(
            adminMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .addComponent(adminMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        adimnBorderPanel.add(adminMainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 20, 1490, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(adimnBorderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(adimnBorderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adLogoutButtonActionPerformed
        contentCardLayout.show(adminContentPanel, "Vehicle");

    }//GEN-LAST:event_adLogoutButtonActionPerformed

    private void adVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adVehicleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adVehicleButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AdminDashboardView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adLogoutButton;
    private javax.swing.JButton adVehicleButton;
    private javax.swing.JPanel adimnBorderPanel;
    private javax.swing.JPanel adminContentPanel;
    private javax.swing.JPanel adminMainPanel;
    private javax.swing.JPanel adminMenuBar;
    private javax.swing.JButton adminProfileButton;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

 // For external access to buttons (still optional if using setter methods)
public javax.swing.JButton getBackToLogin() {
    return adLogoutButton;
}

public javax.swing.JButton getVehicleButton() {
    return adVehicleButton;
}

public javax.swing.JButton getAdminProfileButton() {
    return adminProfileButton;
}

// Recommended: consistent naming convention using `setXListener(...)`
public void setBackToLoginListener(MouseListener listener) {
    adLogoutButton.addMouseListener(listener);
}

public void setVehicleButtonListener(MouseListener listener) {
    adVehicleButton.addMouseListener(listener);
}

public void setAdminProfileListener(MouseListener listener) {
    adminProfileButton.addMouseListener(listener);
}



}
