/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

/**
 *
 * @author Dell
 */
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
    private String confirmPassword;
    public ResetPasswordRequest(String email,String newPassword, String confirmPassword){
        this.email=email;
        this.newPassword=newPassword;
        this.confirmPassword=confirmPassword;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setNewPassword(String newPassword){
        this.newPassword=newPassword;
    }
    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword=confirmPassword;
    }
    public String getEmail(){
        return email;
    }
    public String getConfirmPassword(){
        return confirmPassword;
    }
    public String getNewPassword(){
        return newPassword;
    }   
}
