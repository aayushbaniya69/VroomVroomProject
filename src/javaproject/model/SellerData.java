/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

/**
 *
 * @author ACER
 */
public class SellerData {
    // private attribute
    private String sellerId;
    private String fullName;
    private String location;
    private String email;
    private String password;
    private String rePassword;
    private String contactNumber;
    private String panNumber;
    //Constructor
    public SellerData(String fullName, String email,String location, String password, String rePassword, String panNumber,String contactNumber){
        this.fullName=fullName;
        this.location=location;
        this.email=email;
        this.password=password;
        this.rePassword=rePassword;
        this.contactNumber=contactNumber;
        this.panNumber= panNumber;
    }
    public SellerData(String sellerId,String fullName,String email,String password){
        this.sellerId=sellerId;
        this.fullName=fullName;
        this.email=email;
        this.password=password;
    }
    //setters
    public void setUserId(String sellerId){
        this.sellerId=sellerId;
    }
    public void setFirstName(String fullName){
        this.fullName=fullName;
    }
    public void setAddress(String location){
        this.location=location;
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
    public void setSecurityAnswer(String panNumber){
        this.panNumber=panNumber;
    }
   
    //getters
    public String getSellerId(){
        return this.sellerId;
    }
    public String getFullName(){
        return this.fullName;
    }
    public String getLocation(){
        return this.location;
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
    public String getPanNumber(){
        return this.panNumber;
    }
}
