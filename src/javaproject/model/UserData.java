package javaproject.model;

public class UserData {
    private String registrationId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String rePassword;
    private String contactNumber;
    private String securityAnswer;

    // Full constructor
    public UserData(String firstName, String lastName, String address, String email, String contactNumber, String password, String rePassword, String securityAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.contactNumber = contactNumber;
        this.securityAnswer = securityAnswer;
    }

    // Constructor for profile data
    public UserData(String firstName, String lastName, String email, String address, String contactNumber) {
        this(firstName, lastName, address, email, contactNumber, null, null, null);
    }

    // FIX: Modified constructor for login - now includes email
    public UserData(String registrationId, String firstName, String email, String password) {
        this.registrationId = registrationId;
        this.firstName = firstName;
        this.email = email;  // FIX: Make sure email is set
        this.password = password;
    }

    // Constructor for firstName, lastName, password
    public UserData(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    // Getters and Setters
    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}