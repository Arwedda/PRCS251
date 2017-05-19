/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.security;

import pizzaheaven.controllers.CustomerController;
import pizzaheaven.models.Customer;
import static pizzaheaven.security.KeyStretch.keyStretch;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class Authenticator {

    /**
     * Attempts to match an input email field to a customer object pulled from
     * the database (prevents a single email account having 2 PizzaHeaven
     * accounts)
     * @param email         Email address input
     * @return              true if exists, false if doesn't
     */
    public static Boolean checkEmail(String email){
        CustomerController customerController = new CustomerController();        
        Customer[] customerList = customerController.get();
        String tempEmail;
        email = email.toLowerCase();
        
        for (Customer customer : customerList){
            tempEmail = Encryptor.encryption(customer.getPrivateKey(), email);
            if (customer.getEmail() != null && customer.getEmail().equals(tempEmail)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Attempts to match an input email and password combination to a customer
     * object pulled from the database
     * @param email         Email address input
     * @param password      Password input
     * @return              The correct customer object... or null
     */
    public static Customer authenticate(String email, String password){
        CustomerController customerController = new CustomerController();        
        Customer[] customerList = customerController.get();
        String tempEmail;
        email = email.toLowerCase();
        
        for (Customer customer : customerList){
            tempEmail = Encryptor.encryption(customer.getPrivateKey(), email);
            if (customer.getEmail() != null && customer.getEmail().equals(tempEmail)){
                String stretch = keyStretch(password, Encryptor.decryption(customer.getPrivateKey(), customer.getSalt()));
                String hash = Hash.SHA256(stretch);
                String encrypted = Encryptor.encryption(customer.getPrivateKey(), hash);
                
                if (customer.getCustomerPassword().equals(encrypted)){
                    return (Customer)Encryptor.decrypt(customer);
                }
            }
        }
        return null;
    }
}