/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

/**
 *
 * @author Dell
 */
public class ResetData {
    private String newPassword;
    private String confirmPassword;
    private String email;
    private String securityQuestion;
    private String securityAnswer;

    public ResetData(String newPassword, String confirmPassword, String email, String securityQuestion, String securityAnswer) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
//getter and setter
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    


//    public boolean resetData(String email, String password) {
//        // Implement password reset in your database
//        // Returning true to indicate success
//        return true;
//    }
}
