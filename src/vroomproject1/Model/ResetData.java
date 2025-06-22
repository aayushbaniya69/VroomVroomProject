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
    private String securityAnswer;
    private String securityQuestion;
    
    //constructors

    public ResetData(String newPassword, String confirmPassword, String securityAnswer, String securityQuestion) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.securityAnswer = securityAnswer;
        this.securityQuestion = securityQuestion;
    }
    
// getter and setter
    
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

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    
    
}
