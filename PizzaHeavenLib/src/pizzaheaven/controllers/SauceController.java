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
import pizzaheaven.models.Sauce;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class SauceController extends APIController implements IAPIController{
    Sauce[] cachedSauce;
    
    @Override 
    public Sauce[] get() {
        return get(false);
    }
    
    @Override 
    public Sauce[] get(Boolean forceUpdate) {
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedSauce != null) {
                return cachedSauce;
            }
        }
        
        Sauce[] sauceList = new Sauce[0];
        try {
            sauceList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/PizzaSauces"), Sauce[].class);
            cachedSauce = Sort(sauceList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return sauceList;
    }
    
    private Sauce[] Sort(Sauce[] sauceList){
        for (int i = 0; i < sauceList.length; i++){
            if (sauceList[i].getName().equals("Tomato")){
                Sauce tempSauce = sauceList[0];
                sauceList[0] = sauceList[i];
                sauceList[i] = tempSauce;
                break;
            }
        }
        return sauceList;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Sauce) {
            Sauce sauce = (Sauce)obj;
            return post(sauce, Globals.API_ENDPOINT + "/PizzaSauces/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Sauce) {
            Sauce sauce = (Sauce)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/PizzaSauces/" + URLEncoder.encode(sauce.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Sauce) {
            Sauce sauce = (Sauce)obj;
            try {
                return put(sauce, Globals.API_ENDPOINT + "/PizzaSauces/" + URLEncoder.encode(sauce.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Sauce) {
            Sauce sauce = (Sauce)obj;
            try {
                delete(Globals.API_ENDPOINT + "/PizzaSauces/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(sauce, Globals.API_ENDPOINT + "/PizzaSauces/" + URLEncoder.encode(sauce.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public Sauce[] getCached() {
        return cachedSauce;
    }
}