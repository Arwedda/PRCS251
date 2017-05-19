/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import pizzaheaven.globals.Globals;
import pizzaheaven.models.CustomPizza;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class CustomPizzaController extends APIController implements IAPIController{
    CustomPizza[] cachedCustomPizza;
    
    @Override 
    public CustomPizza[] get() {
        return get(false);
    }
    
    @Override 
    public CustomPizza[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/CustomPizzas" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedCustomPizza != null) {
                return cachedCustomPizza;
            }
        }
        
        CustomPizza[] customPizzaList = new CustomPizza[0];
        try {
            customPizzaList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/CustomPizzas"), CustomPizza[].class);
            cachedCustomPizza = customPizzaList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return customPizzaList;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof CustomPizza) {
            CustomPizza customPizza = (CustomPizza)obj;
            return post(customPizza, Globals.API_ENDPOINT + "/CustomPizzas/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof CustomPizza) {
            CustomPizza customPizza = (CustomPizza)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/CustomPizzas/" + URLEncoder.encode(customPizza.getCustomPizzaName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof CustomPizza) {
            CustomPizza customPizza = (CustomPizza)obj;
            try {
                return put(customPizza, Globals.API_ENDPOINT + "/CustomPizzas/" + URLEncoder.encode(customPizza.getCustomPizzaName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof CustomPizza) {
            CustomPizza customPizza = (CustomPizza)obj;
            try {
                delete(Globals.API_ENDPOINT + "/CustomPizzas/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(customPizza, Globals.API_ENDPOINT + "/CustomPizzas/" + URLEncoder.encode(customPizza.getCustomPizzaName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public CustomPizza[] getCached() {
        return cachedCustomPizza;
    }
}