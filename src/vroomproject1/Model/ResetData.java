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
    public ResetData(String newPassword, String confirmPassword, String email) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.email=email;
    }

    public ResetData() {
    }

    

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    } 
    public String getEmail(){
        return this.email;
    }

    public boolean resetData(String email, String password) {
        // Implement password reset in your database
        // Returning true to indicate success
        return true;
    }
}
