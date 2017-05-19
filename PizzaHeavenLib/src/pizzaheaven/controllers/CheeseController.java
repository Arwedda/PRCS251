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
import pizzaheaven.models.Cheese;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class CheeseController extends APIController implements IAPIController{
    Cheese[] cachedCheese;
    
    @Override 
    public Cheese[] get() {
        return get(false);
    }
    
    @Override 
    public Cheese[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/PizzaCheese" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedCheese != null) {
                return cachedCheese;
            }
        }
        
        Cheese[] cheeseList = new Cheese[0];
        try {
            cheeseList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/PizzaCheese"), Cheese[].class);
            cachedCheese = Sort(cheeseList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return cheeseList;
    }
    
    private Cheese[] Sort(Cheese[] cheeseList){
        for (int i = 0; i < cheeseList.length; i++){
            if (cheeseList[i].getName().equals("Normal")){
                Cheese tempCheese = cheeseList[0];
                cheeseList[0] = cheeseList[i];
                cheeseList[i] = tempCheese;
                break;
            }
        }
        return cheeseList;
    }

    @Override 
    public int add(Object obj) {
        if (obj instanceof Cheese) {
            Cheese cheese = (Cheese)obj;
            return post(cheese, Globals.API_ENDPOINT + "/PizzaCheese/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Cheese) {
            Cheese cheese = (Cheese)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/PizzaCheese/" + URLEncoder.encode(cheese.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Cheese) {
            Cheese cheese = (Cheese)obj;
            try {
                return put(cheese, Globals.API_ENDPOINT + "/PizzaCheese/" + URLEncoder.encode(cheese.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Cheese) {
            Cheese cheese = (Cheese)obj;
            try {
                delete(Globals.API_ENDPOINT + "/PizzaCheese/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(cheese, Globals.API_ENDPOINT + "/PizzaCheese/" + URLEncoder.encode(cheese.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public Cheese[] getCached() {
        return cachedCheese;
    }
}