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
import pizzaheaven.models.Drink;


/**
 * 
 * @author dansc
 */
public class DrinksController extends APIController implements IAPIController{
    Drink[] cachedDrink;
    
    @Override 
    public Drink[] get() {
        return get(false);
    }
    
    @Override 
    public Drink[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Drinks" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedDrink != null) {
                return cachedDrink;
            }
        }
        
        Drink[] drinkList = new Drink[0];
        try {
            drinkList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/Drinks"), Drink[].class);
            cachedDrink = sortAZ(drinkList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return drinkList;
    }
    
    private Drink[] sortAZ(Drink[] pizzaList){
        Arrays.sort(pizzaList, new Comparator<Drink>() {
            @Override
            public int compare(Drink d1, Drink d2) {
                return d1.getName().compareToIgnoreCase(d2.getName());
            }
        });
        return pizzaList;
    }
    
    public Drink get(String name) {
        for (Drink drink : get()) {
            if (drink.getName().equals(name)) {
                return drink;
            }
        }
        return null;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Drink) {
            Drink drink = (Drink)obj;
            return post(drink, Globals.API_ENDPOINT + "/Drinks/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Drink) {
            Drink drink = (Drink)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/Drinks/" + URLEncoder.encode(drink.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Drink) {
            Drink drink = (Drink)obj;
            try {
                return put(drink, Globals.API_ENDPOINT + "/Drinks/" + URLEncoder.encode(drink.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Drink) {
            Drink drink = (Drink)obj;
            try {
                delete(Globals.API_ENDPOINT + "/Drinks/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(drink, Globals.API_ENDPOINT + "/Drinks/" + URLEncoder.encode(drink.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public Drink[] getCached() {
        return cachedDrink;
    }
}