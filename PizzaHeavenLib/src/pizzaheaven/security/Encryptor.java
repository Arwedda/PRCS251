/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import pizzaheaven.models.Customer;
import java.util.Base64;
import pizzaheaven.models.Staff;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class Encryptor {

    public static Object encrypt(Object obj){
        if (obj instanceof Customer) {
            Customer customer = (Customer)obj;
            String privateKey = customer.getPrivateKey();
            String firstName = customer.getFirstName();
            String surname = customer.getSurname();
            String phoneNumber = customer.getPhoneNumber();
            String deliveryLineOne = customer.getDeliveryLineOne();
            String deliveryLineTwo = customer.getDeliveryLineTwo();
            String deliveryCity = customer.getDeliveryCity();
            String deliveryCounty = customer.getDeliveryCounty();
            String deliveryPostCode = customer.getDeliveryPostCode();
            String email = customer.getEmail();
            String password = customer.getCustomerPassword();
            String salt = customer.getSalt();

            customer.setFirstName(encryption(privateKey, firstName));
            customer.setSurname(encryption(privateKey, surname));
            customer.setPhoneNumber(encryption(privateKey, phoneNumber));
            customer.setDeliveryLineOne(encryption(privateKey, deliveryLineOne));
            customer.setDeliveryLineTwo(encryption(privateKey, deliveryLineTwo));
            customer.setDeliveryCity(encryption(privateKey, deliveryCity));
            customer.setDeliveryCounty(encryption(privateKey, deliveryCounty));
            customer.setDeliveryPostCode(encryption(privateKey, deliveryPostCode));
            customer.setEmail(encryption(privateKey, email));
            customer.setCustomerPassword(encryption(privateKey, password));
            customer.setSalt(encryption(privateKey, salt));
            return customer;
        } else if (obj instanceof Staff) {
            Staff staff = (Staff)obj;
            String privateKey = staff.getPrivateKey();
            String firstName = staff.getFirstName();
            String surname = staff.getSurname();
            String phoneNumber = staff.getPhoneNumber();
            String addressLineOne = staff.getAddressLineOne();
            String addressLineTwo = staff.getAddressLineTwo();
            String city = staff.getCity();
            String county = staff.getCounty();
            String postCode = staff.getPostCode();
            String compPosition = staff.getCompanyPosition();
            String email = staff.getEmail();
            String password = staff.getPassword();
            String salt = staff.getSalt();
            String employed = staff.getEmployed();
            
            staff.setFirstName(encryption(privateKey, firstName));
            staff.setSurname(encryption(privateKey, surname));
            staff.setPhoneNumber(encryption(privateKey, phoneNumber));
            staff.setAddressLineOne(encryption(privateKey, addressLineOne));
            staff.setAddressLineTwo(encryption(privateKey, addressLineTwo));
            staff.setCity(encryption(privateKey, city));
            staff.setCounty(encryption(privateKey, county));
            staff.setPostCode(encryption(privateKey, postCode));
            staff.setEmail(encryption(privateKey, email));
            staff.setPassword(encryption(privateKey, password));
            staff.setCompanyPosition(encryption(privateKey, compPosition));
            staff.setSalt(encryption(privateKey, salt));
            staff.setEmployed(encryption(privateKey, employed));
            return staff;
        }
        return null;
    }
    
    /**
     * Decrypts a customer object from AES256 (2x 128-bit keys) Encryption.
     * @param customer  Encrypted Customer object to be decrypted
     * @return          Decrypted Customer object
     */
    public static Object decrypt(Object obj){
        if (obj instanceof Customer) { 
            Customer customer = (Customer)obj;
            String privateKey = customer.getPrivateKey();
            String firstName = customer.getFirstName();
            String surname = customer.getSurname();
            String phoneNumber = customer.getPhoneNumber();
            String deliveryLineOne = customer.getDeliveryLineOne();
            String deliveryLineTwo = customer.getDeliveryLineTwo();
            String deliveryCity = customer.getDeliveryCity();
            String deliveryCounty = customer.getDeliveryCounty();
            String deliveryPostCode = customer.getDeliveryPostCode();

            String email = customer.getEmail();
            String password = customer.getCustomerPassword();
            String salt = customer.getSalt();

            customer.setFirstName(decryption(privateKey, firstName));
            customer.setSurname(decryption(privateKey, surname));
            customer.setPhoneNumber(decryption(privateKey, phoneNumber));
            customer.setDeliveryLineOne(decryption(privateKey, deliveryLineOne));
            customer.setDeliveryLineTwo(decryption(privateKey, deliveryLineTwo));
            customer.setDeliveryCity(decryption(privateKey, deliveryCity));
            customer.setDeliveryCounty(decryption(privateKey, deliveryCounty));
            customer.setDeliveryPostCode(decryption(privateKey, deliveryPostCode));
            customer.setEmail(decryption(privateKey, email));
            customer.setCustomerPassword(decryption(privateKey, password));
            customer.setSalt(decryption(privateKey, salt));

            return customer;
        } else if (obj instanceof Staff) {
            Staff staff = (Staff)obj;
            String privateKey = staff.getPrivateKey();
            String firstName = staff.getFirstName();
            String surname = staff.getSurname();
            String phoneNumber = staff.getPhoneNumber();
            String addressLineOne = staff.getAddressLineOne();
            String addressLineTwo = staff.getAddressLineTwo();
            String city = staff.getCity();
            String county = staff.getCounty();
            String postCode = staff.getPostCode();
            String compPosition = staff.getCompanyPosition();
            String email = staff.getEmail();
            String password = staff.getPassword();
            String salt = staff.getSalt();
            String employed = staff.getEmployed();
            
            staff.setFirstName(decryption(privateKey, firstName));
            staff.setSurname(decryption(privateKey, surname));
            staff.setPhoneNumber(decryption(privateKey, phoneNumber));
            staff.setAddressLineOne(decryption(privateKey, addressLineOne));
            staff.setAddressLineTwo(decryption(privateKey, addressLineTwo));
            staff.setCity(decryption(privateKey, city));
            staff.setCounty(decryption(privateKey, county));
            staff.setPostCode(decryption(privateKey, postCode));
            staff.setEmail(decryption(privateKey, email));
            staff.setPassword(decryption(privateKey, password));
            staff.setCompanyPosition(decryption(privateKey, compPosition));
            staff.setEmployed(decryption(privateKey, employed));
            staff.setSalt(decryption(privateKey, salt));
            return staff;
        }
        return null;
    }
    
    /**
     * Encrypts strings using the public key "PizzaHeavenCDJMZ" and a per-user
     * private key.
     * @param priKey    Private key
     * @param value     Plaintext string to be encrypted
     * @return          Encrypted string to be stored
     */
    public static String encryption(String priKey, String value) { 
        try {           
            //128 bit key = 16x 8 bit characters
            String pubKey = "PizzaHeavenCDJMZ";
            SecretKeySpec sKey = new SecretKeySpec(pubKey.getBytes("UTF-8"),
                    "AES");
            IvParameterSpec iv = new IvParameterSpec(priKey.getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKey, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Decrypts strings using the public key "PizzaHeavenCDJMZ" and a per-user
     * private key.
     * @param priKey    Private key
     * @param base64    Encrypted string to be decrypted
     * @return          Decrypted string to be used
     */
    public static String decryption(String priKey, String base64) {
        try {         
            //128 bit key
            String pubKey = "PizzaHeavenCDJMZ";
            SecretKeySpec sKey = new SecretKeySpec(pubKey.getBytes("UTF-8"),
                    "AES");
            IvParameterSpec iv = new IvParameterSpec(priKey.getBytes("UTF-8"));
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKey, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(base64));

            return new String(original);
        } catch (Exception e) {

        }
        return null;
    }
}