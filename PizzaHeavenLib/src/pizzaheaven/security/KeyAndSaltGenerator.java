/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * @author Joseph Kellaway + Craig Banyard
 */
public class KeyAndSaltGenerator {
    /**
     * Generates the user's private key and salt using SecureRandom object. Uses SHA1
     * because the key/salt on their own doesn't need to be unbreakable
     * @param size  The required key size
     * @return      Byte array representation of the user's private key
     */
    public static String SHA1(int size) {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[size];
            sr.nextBytes(bytes);
            return toHex(bytes);
        } catch (NoSuchAlgorithmException e){
            return null;
        }
    }
    
    private static String toHex(byte[] array) {
        BigInteger big = new BigInteger(1, array);
        String hex = big.toString(16);
        int padding = (array.length * 2) - hex.length();
        if (padding > 0){
            return String.format("%0" + padding + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}