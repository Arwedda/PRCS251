/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.helpers;

import javax.swing.text.JTextComponent;

/**
 *
 * @author dansc
 */
public class FormValidation {
    public Boolean textFieldsNotEmpty(JTextComponent[] componentList) {
        Boolean fieldEmpty = false;
        for (JTextComponent component : componentList) {
            if (component.getText().equals("") && component.getText().isEmpty()) {
                
            }
        }
        return fieldEmpty;
    }
}
