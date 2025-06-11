/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

/**
 *
 * @author Dell
 */


public class PaymentData {
    //attributes
    private String address;
    private String name;
    private String city;
    private String state;
    private String cardNumber;
    private String expiryDate;
    private String cvc;
    private String country;
    
    //constructor

    public PaymentData(String address, String name, String city, String state, String cardNumber, String expiryDate, String cvc, String country) {
        this.address = address;
        this.name = name;
        this.city = city;
        this.state = state;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvc = cvc;
        this.country = country;
    }
    
    //getter and setter
    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvc() {
        return cvc;
    }

    public String getCountry() {
        return country;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

