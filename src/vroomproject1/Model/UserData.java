/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

/**
 *
 * @author Dell
 */
public class UserData {
    private String newPassword;
    private String confirmPassword;
    private String email;
    public UserData(String newPassword, String confirmPassword, String email) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.email=email;
    }

    public void setPassword(String newPassword) {
        this.newPassword = newPassword;
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
}
