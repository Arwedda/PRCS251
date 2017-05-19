 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.security;

import java.security.MessageDigest;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class Hash {

    /**
     * Turns a string (password) into a SHA-256 hashed password. Intended for use
     * after the password has been stretched with the salt and stretching algorithm
     * but before the encryption algorithm.
     * @param toHash    Password to be hashed
     * @return          The SHA-256 hash of the string input into the function
     */
    public static String SHA256(String toHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((toHash + "PizzaHeaven").getBytes("UTF-8")); //Can use UTF-16 if needed
            byte[] digest = md.digest();
            return String.format("%032x", new java.math.BigInteger(1, digest));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
