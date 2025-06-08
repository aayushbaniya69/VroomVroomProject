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
    public UserData(String newPassword, String confirmPassword) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public static UserData withNewPassword(String newPassword) {
        return new UserData(newPassword, null);
    }

    public static UserData withConfirmPassword(String confirmPassword) {
        return new UserData(null, confirmPassword);
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
}
