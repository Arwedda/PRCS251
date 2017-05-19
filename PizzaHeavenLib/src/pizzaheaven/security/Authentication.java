/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.security;

import javax.swing.JOptionPane;
import pizzaheaven.controllers.StaffController;
import pizzaheaven.models.Session;
import pizzaheaven.models.Staff;
import static pizzaheaven.security.KeyStretch.keyStretch;

/**
 *
 * @author drscott
 */
public class Authentication {

    /**
     * Attempts to match an input email and password combination to a staff
     * object pulled from the database
     * @param email         Email address input
     * @param password      Password input
     * @return              The correct Staff object... or null
     */
    public static Staff tryAuthenticate(String email, String password) {
        Staff[] staffList = ((StaffController)Session.get().getController("StaffController")).get();    
        String tempEmail;
        email = email.toLowerCase();
        if (staffList == null) return null;
        for (Staff staffMember : staffList) {
            tempEmail = Encryptor.encryption(staffMember.getPrivateKey(), email);
            if (staffMember.getEmail() != null && staffMember.getEmail().equals(tempEmail)) {
                String stretch = keyStretch(password, Encryptor.decryption(staffMember.getPrivateKey(), staffMember.getSalt()));
                String hash = Hash.SHA256(stretch);
                String encrypted = Encryptor.encryption(staffMember.getPrivateKey(), hash);
                
                if (encrypted.equals(staffMember.getPassword())) {
                    Session.get().setStaff((Staff)Encryptor.decrypt(staffMember));
                    return staffMember;
                }
            }
        }
        return null;
    }
}
