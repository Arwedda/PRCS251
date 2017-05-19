/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.globals;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author drscott
 */
public class Globals {
    public static String API_ENDPOINT = "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/PRCS251A/api";
    //public static String API_ENDPOINT = "http://localhost:52777/api/";
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static int API_CACHE_INTERVAL = 10000;
    public static Image getPizzaImage() {
        URL url;
        try {
            Toolkit kit = Toolkit.getDefaultToolkit();
            return kit.createImage(new Globals().getClass().getClassLoader().getResource("com/pizzaheaven/images/icon.png"));
        } catch (Exception e) {
            
        }
        return null;
    }
}
