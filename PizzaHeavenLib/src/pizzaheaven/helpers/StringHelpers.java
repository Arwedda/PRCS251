/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.helpers;

import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author dan
 */
public class StringHelpers {
    public static String formatPence(int pence) {
        if (String.valueOf(pence).length() != 2) {
            if (pence < 10) {
                return "0" + String.valueOf(pence);
            } else {
                return String.valueOf(pence) + "0";
            }
        }
        return String.valueOf(pence);
    }
    
    public static Date orderToDate(String orderString) {
        String[] parts = orderString.split(Pattern.quote("T"));
        String[] dateParts = parts[0].split(Pattern.quote("-"));
        int year = Integer.parseInt(dateParts[0]) - 1900;
        int month = Integer.parseInt(dateParts[1]) - 1;
        int day = Integer.parseInt(dateParts[2]);
        String[] timeParts = parts[1].split(Pattern.quote(":"));
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        return new Date(year, month, day, hour, minute);
    }
}
