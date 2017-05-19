/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import pizzaheaven.globals.Globals;
import pizzaheaven.models.Topping;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class ToppingsController extends APIController implements IAPIController{
    Topping[] cachedTopping;
    
    @Override 
    public Topping[] get() {
        return get(false);
    }
    
    public Topping get(String name) {
        for (Topping topping : get()) {
            if (topping.getName().equals(name)){ 
                return topping;
            }
        }
        return null;
    }
    
    @Override 
    public Topping[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Toppings" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedTopping != null) {
                return cachedTopping;
            }
        }
        
        Topping[] toppingList = new Topping[0];
        try {
            toppingList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/Toppings"), Topping[].class);
            cachedTopping = sortAZ(toppingList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return toppingList;
    }
    
    private Topping[] sortAZ(Topping[] toppingList){
        Arrays.sort(toppingList, new Comparator<Topping>() {
            @Override
            public int compare(Topping p1, Topping p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });
        return toppingList;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Topping) {
            Topping topping = (Topping)obj;
            return post(topping, Globals.API_ENDPOINT + "/Toppings/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Topping) {
            Topping topping = (Topping)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/Toppings/" + URLEncoder.encode(topping.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Topping) {
            Topping topping = (Topping)obj;
            try {
                return put(topping, Globals.API_ENDPOINT + "/Toppings/" + URLEncoder.encode(topping.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Topping) {
            Topping topping = (Topping)obj;
            try {
                delete(Globals.API_ENDPOINT + "/Toppings/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(topping, Globals.API_ENDPOINT + "/Toppings/" + URLEncoder.encode(topping.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public Topping[] getCached() {
        return cachedTopping;
    }
}