/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dansc
 */
public class Staff {
    @JsonProperty("StaffID")
    private String staffID;
    @JsonProperty("Firstname") // +
    private String firstName;
    @JsonProperty("Surname") // +
    private String surname;
    @JsonProperty("dob") 
    private String dob;
    @JsonProperty("PhoneNumber") // +
    private String phoneNumber;
    @JsonProperty("Email") //+
    private String email;
    @JsonProperty("CompanyPosition") // +
    private String companyPosition;
    @JsonProperty("StartDate")
    private String startDate;
    @JsonProperty("AddressLineOne") // +
    private String addressLineOne;
    @JsonProperty("AddressLineTwo") // +
    private String addressLineTwo;
    @JsonProperty("City") // +
    private String city;
    @JsonProperty("County") // +
    private String county;
    @JsonProperty("PostCode") // +
    private String postCode;
    @JsonProperty("StaffPassword") // +
    private String password;
    @JsonProperty("Salt") // + 
    private String salt;
    @JsonProperty("PrivateKey") 
    private String privateKey;
    @JsonProperty("Employed")
    private String employed;

    public String getEmployed() {
        return employed;
    }

    public void setEmployed(String employed) {
        this.employed = employed;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     *
     */
    public Staff(){
        this.staffID = "Unknown";
        this.firstName = "Unknown";
        this.surname = "Unknown";
        this.dob = "Unknown";
        this.phoneNumber = "Unknown";
        this.email = "Unknown";
        this.companyPosition = "Unknown";
        this.startDate = "Unknown";
        this.addressLineOne = "Unknown";
        this.addressLineTwo = "Unknown";
        this.city = "Unknown";
        this.county = "Unknown";
        this.postCode = "Unknown";
        this.password = "Unknown";
        this.setPrivateKey("Unknown");
        this.setSalt("Unknown");
        this.setEmployed("1");
    }
    
    /**
     *
     * @param staffID
     * @param firstName
     * @param surname
     * @param dob
     * @param phoneNumber
     * @param email
     * @param companyPosition
     * @param startDate
     * @param addressLineOne
     * @param addressLineTwo
     * @param city
     * @param county
     * @param postCode
     * @param password
     */
    public Staff(String staffID, String firstName, String surname, String dob, String phoneNumber, String email, String companyPosition, String startDate, String addressLineOne, String addressLineTwo, String city, String county, String postCode, String password, String salt, String privateKey) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.surname = surname;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyPosition = companyPosition;
        this.startDate = startDate;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
        this.password = password;
        this.salt = salt;
        this.privateKey = privateKey;
        this.employed = "1";
    }
    
    /**
     *
     * @param staffID
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    
    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getStaffID() {
        return staffID;
    }
    
    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getPassword() {
        return password;
    }
    
    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getCompanyPosition() {
        return companyPosition;
    }

    /**
     *
     * @param companyPosition
     */
    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getAddressLineOne() {
        return addressLineOne;
    }

    /**
     *
     * @param addressLineOne
     */
    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    /**
     *
     * @param addressLineTwo
     */
    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getCounty() {
        return county;
    }

    /**
     *
     * @param county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     *
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return this.staffID + " "  + this.firstName + " " + this.surname;
    }
}