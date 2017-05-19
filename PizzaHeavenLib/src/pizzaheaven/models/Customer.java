/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class Customer {
    /**
     * The ID of the Customer object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("CustomerID")
    private String customerID;
    /**
     * The first name of the Customer object.
     */
    @JsonProperty("Firstname")
    private String firstName;
    /**
     * The second name of the Customer object.
     */
    @JsonProperty("Surname")
    private String surname;
    /**
     * The date of birth of the Customer object.
     */
    @JsonProperty("dob")
    private String dob;
    /**
     * The phone number of the Customer object.
     */
    @JsonProperty("PhoneNumber")
    private String phoneNumber;
    /**
     * The email address of the Customer object.
     * Used to verify that someone is authorised to use a particular
     * customer account.
     */
    @JsonProperty("Email")
    private String email;
    /**
     * The password of the Customer object.
     * Used to verify that someone is authorised to use a particular
     * customer account.
     */
    @JsonProperty("CustomerPassword")
    private String customerPassword;
    /**
     * The house number/name of the Customer object.
     * Used to direct the delivery driver to the customer.
     */
    @JsonProperty("DeliveryAddressLineOne")
    private String deliveryLineOne;
    /**
     * The street name of the Customer object.
     */
    @JsonProperty("DeliveryAddressLineTwo")
    private String deliveryLineTwo;
    /**
     * The city of the Customer object.
     */
    @JsonProperty("DeliveryCity")
    private String deliveryCity;
    /**
     * The county of the Customer object.
     */
    @JsonProperty("DeliveryCounty")
    private String deliveryCounty;
    /**
     * The post code of the Customer object.
     */
    @JsonProperty("DeliveryPostCode")
    private String deliveryPostCode;
    /**
     * The salt value of the Customer object.
     * Used in KeyStretch class to turn the user's password into a much more
     * secure string.
     */
    @JsonProperty("Salt")
    private String salt;
    /**
     * The private key value of the Customer object.
     * Used by the Encryptor class to encrypt data before storage and decrypt
     * it again before use.
     */
    @JsonProperty("PrivateKey")
    private String privateKey;

    /**
     * Default constructor.
     */
    public Customer(){
        this.customerID = "0";
        this.firstName = "Unknown";
        this.surname = "Unknown";
        this.dob = "Unknown";
        this.phoneNumber = "Unknown";
        this.email = "Unknown";
        this.customerPassword = "Unknown";
        this.deliveryLineOne = "Unknown";
        this.deliveryLineTwo = "Unknown";
        this.deliveryCity = "Unknown";
        this.deliveryCounty = "Unknown";
        this.deliveryPostCode = "Unknown";
        this.salt = "Unknown";
        this.privateKey = "Unknown";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Customer#} on creation
     * @param firstName                 Default value of {@link Customer#firstName}
     * @param surname                   Default value of {@link Customer#surname}
     * @param dob                       Default value of {@link Customer#dob}
     * @param phoneNumber               Default value of {@link Customer#phoneNumber}
     * @param email                     Default value of {@link Customer#email}
     * @param customerPassword          Default value of {@link Customer#customerPassword}
     * @param deliveryAddressLineOne    Default value of {@link Customer#deliveryAddressLineOne}
     * @param deliveryAddressLineTwo    Default value of {@link Customer#deliveryAddressLineTwo}
     * @param deliveryCity              Default value of {@link Customer#deliveryCity}
     * @param deliveryCounty            Default value of {@link Customer#deliveryCounty}
     * @param deliveryPostCode          Default value of {@link Customer#deliveryPostCode}
     * @param salt                      Default value of {@link Customer#salt}
     * @param privateKey                Default value of {@link Customer#privateKey}
     */
    public Customer(String firstName, String surname, String dob, String phoneNumber, String email,
            String customerPassword, String deliveryAddressLineOne, String deliveryAddressLineTwo, 
            String deliveryCity, String deliveryCounty, String deliveryPostCode, String salt,
            String privateKey){
        this.customerID = "0";
        this.firstName = firstName;
        this.surname = surname;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerPassword = customerPassword;
        this.deliveryLineOne = deliveryAddressLineOne;
        this.deliveryLineTwo = deliveryAddressLineTwo;
        this.deliveryCity = deliveryCity;
        this.deliveryCounty = deliveryCounty;
        this.deliveryPostCode = deliveryPostCode;
        this.salt = salt;
        this.privateKey = privateKey;
    }
    
    /**
     * @return  Current value of {@link Customer#customerID}
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets value of {@link Customer#customerID}
     * @param customerID New value of {@link Customer#customerID}
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * @return  Current value of {@link Customer#firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets value of {@link Customer#firstName}
     * @param firstName New value of {@link Customer#firstName}
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return  Current value of {@link Customer#surname}
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets value of {@link Customer#surname}
     * @param surname New value of {@link Customer#surname}
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return  Current value of {@link Customer#dob}
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets value of {@link Customer#dob}
     * @param dob New value of {@link Customer#dob}
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return  Current value of {@link Customer#phoneNumber}
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets value of {@link Customer#phoneNumber}
     * @param phoneNumber New value of {@link Customer#phoneNumber}
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return  Current value of {@link Customer#email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets value of {@link Customer#email}
     * @param email New value of {@link Customer#email}
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return  Current value of {@link Customer#customerPassword}
     */
    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * Sets value of {@link Customer#customerPassword}
     * @param customerPassword New value of {@link Customer#customerPassword}
     */
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    /**
     * @return  Current value of {@link Customer#deliveryLineOne}
     */
    public String getDeliveryLineOne() {
        return deliveryLineOne;
    }

    /**
     * Sets value of {@link Customer#deliveryLineOne}
     * @param deliveryLineOne New value of {@link Customer#deliveryLineOne}
     */
    public void setDeliveryLineOne(String deliveryLineOne) {
        this.deliveryLineOne = deliveryLineOne;
    }

    /**
     * @return  Current value of {@link Customer#deliveryLineTwo}
     */
    public String getDeliveryLineTwo() {
        return deliveryLineTwo;
    }

    /**
     * Sets value of {@link Customer#deliveryLineTwo}
     * @param deliveryLineTwo New value of {@link Customer#deliveryLineTwo}
     */
    public void setDeliveryLineTwo(String deliveryLineTwo) {
        this.deliveryLineTwo = deliveryLineTwo;
    }

    /**
     * @return  Current value of {@link Customer#deliveryCity}
     */
    public String getDeliveryCity() {
        return deliveryCity;
    }

    /**
     * Sets value of {@link Customer#deliveryCity}
     * @param deliveryCity New value of {@link Customer#deliveryCity}
     */
    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    /**
     * @return  Current value of {@link Customer#deliveryCounty}
     */
    public String getDeliveryCounty() {
        return deliveryCounty;
    }

    /**
     * Sets value of {@link Customer#deliveryCounty}
     * @param deliveryCounty New value of {@link Customer#deliveryCounty}
     */
    public void setDeliveryCounty(String deliveryCounty) {
        this.deliveryCounty = deliveryCounty;
    }

    /**
     * @return  Current value of {@link Customer#deliveryPostCode}
     */
    public String getDeliveryPostCode() {
        return deliveryPostCode;
    }

    /**
     * Sets value of {@link Customer#deliveryPostCode}
     * @param deliveryPostCode New value of {@link Customer#deliveryPostCode}
     */
    public void setDeliveryPostCode(String deliveryPostCode) {
        this.deliveryPostCode = deliveryPostCode;
    }

    /**
     * @return  Current value of {@link Customer#salt}
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets value of {@link Customer#salt}
     * @param salt New value of {@link Customer#salt}
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return  Current value of {@link Customer#privateKey}
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets value of {@link Customer#privateKey}
     * @param privateKey New value of {@link Customer#privateKey}
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Overloaded constructor. Sets all attributes of {@link Customer#} that can
     * be edited from the website's account editing page
     * @param firstName         Updated value of {@link Customer#firstName}
     * @param surname           Updated value of {@link Customer#surname}
     * @param dob               Updated value of {@link Customer#dob}
     * @param phoneNumber       Updated value of {@link Customer#phoneNumber}
     * @param deliveryLineOne   Updated value of {@link Customer#deliveryLineOne}
     * @param deliveryLineTwo   Updated value of {@link Customer#deliveryLineTwo}
     * @param deliveryCity      Updated value of {@link Customer#deliveryCity}
     * @param deliveryCounty    Updated value of {@link Customer#deliveryCounty}
     * @param deliveryPostCode  Updated value of {@link Customer#deliveryPostCode}
     */
    public void updateAccount(String firstName, String surname, String dob, String phoneNumber, 
            String deliveryLineOne, String deliveryLineTwo, String deliveryCity, 
            String deliveryCounty, String deliveryPostCode) {
        this.firstName = firstName;
        this.surname = surname;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.deliveryLineOne = deliveryLineOne;
        this.deliveryLineTwo = deliveryLineTwo;
        this.deliveryCity = deliveryCity;
        this.deliveryCounty = deliveryCounty;
        this.deliveryPostCode = deliveryPostCode;
    }
}