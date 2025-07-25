
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class ResetView extends javax.swing.JFrame {

    /**
     * Creates new form ResetView
     */
    public ResetView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CreateNewPasswordLabel = new javax.swing.JLabel();
        DifferentLabel = new javax.swing.JLabel();
        EmailTextField = new javax.swing.JTextField();
        EmailLabel = new javax.swing.JLabel();
        ForOtpLabel = new javax.swing.JLabel();
        SecurityQuestionLabel = new javax.swing.JLabel();
        SecurityQuestionComboBox = new javax.swing.JComboBox<>();
        SerucityAnswer = new javax.swing.JLabel();
        OTPlabel = new javax.swing.JLabel();
        OTPTextField = new javax.swing.JTextField();
        EnterOtpLabel = new javax.swing.JLabel();
        NewPasswordLabel = new javax.swing.JLabel();
        NewPasswordField = new javax.swing.JPasswordField();
        CharacterLabel = new javax.swing.JLabel();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        ConfirmPasswordField = new javax.swing.JPasswordField();
        SameLabel = new javax.swing.JLabel();
        ResetPasswordButton = new javax.swing.JButton();
        BackToLoginButton = new javax.swing.JButton();
        NPshow = new javax.swing.JButton();
        CPshow = new javax.swing.JButton();
        EnterButton = new javax.swing.JButton();
        OtpsendButton = new javax.swing.JButton();
        SecurityAnswerTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 0, 153));

        jPanel1.setBackground(new java.awt.Color(196, 173, 221));

        CreateNewPasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CreateNewPasswordLabel.setText("Create New Password");

        DifferentLabel.setText("Your password must be different from previous");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EmailLabel.setText("Email");

        ForOtpLabel.setText("Enter Your Email for otp.");

        SecurityQuestionLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SecurityQuestionLabel.setText("Security Questions");

        SecurityQuestionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        SerucityAnswer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SerucityAnswer.setText("Security Answers");

        OTPlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        OTPlabel.setText("OTP");

        EnterOtpLabel.setText("Enter you OTP.");

        NewPasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NewPasswordLabel.setText("New Password");

        NewPasswordField.setText("jPasswordField1");

        CharacterLabel.setText("Password must be atleast 8 character. ");

        ConfirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ConfirmPasswordLabel.setText("Confirm Password");

        ConfirmPasswordField.setText("jPasswordField2");

        SameLabel.setText("Both password must be same.");

        ResetPasswordButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ResetPasswordButton.setText("Reset Password");

        BackToLoginButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BackToLoginButton.setText("Back To Login");

        NPshow.setText("Show");

        CPshow.setText("Show");

        EnterButton.setText("Enter");

        OtpsendButton.setText("Send");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SameLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ConfirmPasswordLabel)
                            .addComponent(CharacterLabel)
                            .addComponent(OTPlabel)
                            .addComponent(SerucityAnswer)
                            .addComponent(SecurityQuestionLabel)
                            .addComponent(ForOtpLabel)
                            .addComponent(EmailLabel)
                            .addComponent(EmailTextField)
                            .addComponent(SecurityQuestionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(OTPTextField)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(NewPasswordLabel)
                                .addComponent(EnterOtpLabel))
                            .addComponent(NewPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(ConfirmPasswordField)
                            .addComponent(SecurityAnswerTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NPshow)
                            .addComponent(CPshow)
                            .addComponent(EnterButton)
                            .addComponent(OtpsendButton))))
                .addGap(0, 36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(CreateNewPasswordLabel)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(DifferentLabel)
                        .addGap(141, 141, 141))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BackToLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResetPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(183, 183, 183))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CreateNewPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DifferentLabel)
                .addGap(18, 18, 18)
                .addComponent(EmailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ForOtpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SecurityQuestionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SecurityQuestionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SerucityAnswer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OtpsendButton)
                    .addComponent(SecurityAnswerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OTPlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OTPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnterButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnterOtpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NewPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NPshow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CharacterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConfirmPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CPshow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SameLabel)
                .addGap(18, 18, 18)
                .addComponent(ResetPasswordButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackToLoginButton)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ResetView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResetView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResetView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResetView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResetView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToLoginButton;
    private javax.swing.JButton CPshow;
    private javax.swing.JLabel CharacterLabel;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JLabel CreateNewPasswordLabel;
    private javax.swing.JLabel DifferentLabel;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JButton EnterButton;
    private javax.swing.JLabel EnterOtpLabel;
    private javax.swing.JLabel ForOtpLabel;
    private javax.swing.JButton NPshow;
    private javax.swing.JPasswordField NewPasswordField;
    private javax.swing.JLabel NewPasswordLabel;
    private javax.swing.JTextField OTPTextField;
    private javax.swing.JLabel OTPlabel;
    private javax.swing.JButton OtpsendButton;
    private javax.swing.JButton ResetPasswordButton;
    private javax.swing.JLabel SameLabel;
    private javax.swing.JTextField SecurityAnswerTextField;
    private javax.swing.JComboBox<String> SecurityQuestionComboBox;
    private javax.swing.JLabel SecurityQuestionLabel;
    private javax.swing.JLabel SerucityAnswer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

        public javax.swing.JTextField getEmailTextField(){
        return  EmailTextField;
    }
    public javax.swing.JPasswordField getNewPasswordField(){
        return NewPasswordField;
    }
    public javax.swing.JPasswordField getConfirmPasswordField(){
        return ConfirmPasswordField;
    }
    public javax.swing.JTextField getOTPTextField(){
        return OTPTextField;
    }
    public javax.swing.JTextField getSecurityAnswerTextField(){
        return SecurityAnswerTextField;
    }
    public javax.swing.JComboBox<String> getSecurityQuestionComboBox(){
        return SecurityQuestionComboBox;
    }
    public javax.swing.JButton getOtpsendButton(){
        return OtpsendButton;
    }
    public javax.swing.JButton getEnterButton(){
        return EnterButton;
    }
    public javax.swing. JButton getNPshow(){
        return NPshow;
    }
    public javax.swing.JButton getCPshow(){
        return CPshow;
    }
    public javax.swing.JButton getResetPasswordButton(){
        return ResetPasswordButton;
    }
    
    public void addPasswordToggleListeners(ActionListener npListener, ActionListener cpListener) {
        NPshow.addActionListener(npListener);
        CPshow.addActionListener(cpListener);
    }
    public void ResetPassword(ActionListener listener){
        ResetPasswordButton.addActionListener(listener);
    }
    public void sendOtpListener(ActionListener listener){
        OtpsendButton.addActionListener(listener);
    }
    public void VerifyOtpListener(ActionListener listener){
        EnterButton.addActionListener(listener);
    }
    public void resetUser(ActionListener listener){
        ResetPasswordButton.addActionListener(listener);
    }
    public void loginBack(ActionListener listener){
        BackToLoginButton.addActionListener(listener);
    }
    public void enableOTPInputs(){
        OTPTextField.setEnabled(true);
        EnterButton.setEnabled(true);
    }
    public void enablePasswordInputs(){
        ConfirmPasswordField.setEnabled(true);
        NewPasswordField.setEnabled(true);
        ResetPasswordButton.setEnabled(true);      
    }
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(EnterButton, msg);
    }
}


