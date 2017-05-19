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
import pizzaheaven.models.Pizza;

/**
 * 
 * @author dansc
 */
public class PizzaController extends APIController implements IAPIController {
    private Pizza[] cachedPizza;
    
    @Override
    public Pizza[] getCached() {
        return cachedPizza;
    }
    
    @Override 
    public Pizza[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Pizzas" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedPizza != null) {
                return cachedPizza;
            }
        }
        
        Pizza[] pizzaList = new Pizza[0];
        try {
            pizzaList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/Pizzas"), Pizza[].class);
            cachedPizza = sortAZ(pizzaList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pizzaList;
    }
    
     private Pizza[] sortAZ(Pizza[] pizzaList){
        Arrays.sort(pizzaList, new Comparator<Pizza>() {
            @Override
            public int compare(Pizza p1, Pizza p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });
        return pizzaList;
    }
    
    @Override 
    public Pizza[] get() {
        return get(false);
    }
    
    public Pizza get(String name) {
        for (Pizza pizza : get()) {
            if (pizza.getName().equals(name)) {
                return pizza;
            }
        }
        return null;
    }
    
    @Override
    public int update(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza)obj;
            try {
                return put(pizza, Globals.API_ENDPOINT + "/Pizzas/" + URLEncoder.encode(pizza.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza)obj;
            try {
                delete(Globals.API_ENDPOINT + "/Pizzas/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(pizza, Globals.API_ENDPOINT + "/Pizzas/");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int add(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza)obj;
            return post(pizza, Globals.API_ENDPOINT + "/Pizzas/");
        }
        return -1;
    }
    
    @Override
    public int remove(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/Pizzas/" + URLEncoder.encode(pizza.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
}