package javaproject.model;

public class SellerData {
    private String fullName;
    private String email;
    private String location;
    private String contactNumber;
    private String password;
    private String rePassword;
    private String panNumber;
    
    // Empty constructor
    public SellerData() {
    }
    
    // Constructor with 4 parameters (for profile updates - no password)
    public SellerData(String fullName, String email, String location, String contactNumber) {
        this.fullName = fullName;
        this.email = email;
        this.location = location;
        this.contactNumber = contactNumber;
    }
    
    // Constructor with 5 parameters (for login and password operations)
    public SellerData(String fullName, String email, String location, String contactNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.location = location;
        this.contactNumber = contactNumber;
        this.password = password;
    }
    
    // Constructor with 7 parameters (for seller registration and complete data)
    public SellerData(String fullName, String email, String location, String contactNumber, String password, String rePassword, String panNumber) {
        this.fullName = fullName;
        this.email = email;
        this.location = location;
        this.contactNumber = contactNumber;
        this.password = password;
        this.rePassword = rePassword;
        this.panNumber = panNumber;
    }
    
    // Getters
    public String getFullName() {
        return fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRePassword() {
        return rePassword;
    }
    
    public String getPanNumber() {
        return panNumber;
    }
    
    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}