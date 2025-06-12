/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

/**
 *
 * @author ACER
 */
public class UserData {
       // private attribute
    //public methods
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String rePassword;
    private String contactNumber;
    private String securityAnswer;
    //Constructor
    public UserData(String firstName, String lastName, String address, String email, String password, String rePassword, String contactNumber,String securityAnswer){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.email=email;
        this.password=password;
        this.rePassword=rePassword;
        this.contactNumber=contactNumber;
        this.securityAnswer= securityAnswer;
    }
    public UserData(String firstName, String lastName, String address, String email, String password, String contactNumber,String securityAnswer){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.email=email;
        this.password=password;
        this.contactNumber=contactNumber;
        this.securityAnswer= securityAnswer;
    }
    public UserData(String userId,String firstName,String lastName,String password){
    this.userId=userId;
    this.firstName=firstName;
    this.lastName=lastName;
    this.password=password;
}
    //setters
    public void setUserId(String userId){
        this.userId=userId;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setRePassword(String rePassword){
        this.rePassword=rePassword;
    }
    public void setContactNumber(String contactNumber){
        this.contactNumber=contactNumber;
    }
    public void setSecurityAnswer(String securityAnswer){
        this.securityAnswer=securityAnswer;
    }
   
    //getters
    public String getUserId(){
        return userId;
    }
    public String getFirstName(){
        return this.firstName;
    }
     public String getLastName(){
        return this.lastName;
    }
    public String getAddress(){
        return this.address;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRePassword(){
        return this.rePassword;
    }
    public String getContactNumber(){
        return this.contactNumber;
    }
    public String getSecurityAnswer(){
        return this.securityAnswer;
    }
}
